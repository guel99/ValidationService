package com.devisefutures.signaturevalidator.bsl.services;

import com.devisefutures.signaturevalidator.bsl.exceptions.MalformedRequestException;
import com.devisefutures.signaturevalidator.bsl.protocols.ValidationRequest;
import com.devisefutures.signaturevalidator.bsl.protocols.ValidationResponse;
import com.devisefutures.signaturevalidator.bsl.services.validator.Validator;
import eu.europa.esig.dss.spi.x509.CertificateSource;
import eu.europa.esig.dss.validation.DocumentValidator;
import eu.europa.esig.dss.validation.reports.Reports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.bind.ValidationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

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

        return responseAssemblerService.buildResponse(request.getReqID(), validationResult);
    }
}
