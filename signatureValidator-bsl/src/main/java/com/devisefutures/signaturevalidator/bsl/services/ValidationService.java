package com.devisefutures.signaturevalidator.bsl.services;

import com.devisefutures.signaturevalidator.bsl.exceptions.MalformedRequestException;
import com.devisefutures.signaturevalidator.bsl.protocols.ValidationRequest;
import com.devisefutures.signaturevalidator.bsl.protocols.ValidationResponse;
import com.devisefutures.signaturevalidator.bsl.services.validator.Validator;
import com.devisefutures.signaturevalidator.common.services.SignerReportService;
import eu.europa.esig.dss.model.DSSDocument;
import eu.europa.esig.dss.model.InMemoryDocument;
import eu.europa.esig.dss.spi.x509.CertificateSource;
import eu.europa.esig.dss.validation.DocumentValidator;
import eu.europa.esig.dss.validation.reports.Reports;
import eu.europa.esig.validationreport.ValidationReportFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.validation.Validation;
import javax.xml.bind.JAXBException;
import javax.xml.bind.ValidationException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@Service
public class ValidationService {

    /**
     * Handles the request messages sent by the client
     */
    @Autowired
    private RequestParserService requestParserService;

    /**
     * Performs AdES validation
     */
    @Autowired
    private Validator validator;

    /**
     * Assembles the response object to be sent to the client
     */
    @Autowired
    private ResponseAssemblerService responseAssemblerService;

    /**
     * Used to sign the validation report if specified
     * in the request message
     */
    @Autowired
    private SignerReportService signerReportService;

    /**
     * Performs the validation process for a
     * specific validation resquest
     * @param request
     */
    public ValidationResponse validate(ValidationRequest request) throws ValidationException, MalformedRequestException {
        // TODO - handle exceptions and not throw them
        DocumentValidator toValidate = requestParserService.getToValidateDocuments(request);
        CertificateSource certificateSource = null;
        if(requestParserService.hasCertificateSourceInfo(request.getOptInp()))
            certificateSource = requestParserService.getCertificateSource(request.getOptInp());

        Reports validationResult = certificateSource == null ?
                validator.validate(toValidate) :
                validator.validate(toValidate, certificateSource);


        try {
            String etsiReportStr = ValidationReportFacade.newFacade().marshall(validationResult.getEtsiValidationReportJaxb());
            DSSDocument toBeSignedReport = new InMemoryDocument(etsiReportStr.getBytes(StandardCharsets.UTF_8));
            DSSDocument signedReportDocument;

            if(requestParserService.signReport(request)) {
                signedReportDocument = signerReportService.signDocument(toBeSignedReport); // TODO - implement the signing of the ETSI report and include it in the next routine
                return responseAssemblerService.buildResponse(request.getReqID(), validationResult, signedReportDocument);
            }
            return responseAssemblerService.buildResponse(request.getReqID(), validationResult);

        } catch (JAXBException | IOException | SAXException e) {
            throw new ValidationException("Error occured marshalling the etsiValidationReport object to a xml string");
        }

    }
}
