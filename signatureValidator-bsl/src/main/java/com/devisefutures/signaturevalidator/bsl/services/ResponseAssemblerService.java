package com.devisefutures.signaturevalidator.bsl.services;

import com.devisefutures.signaturevalidator.bsl.protocols.ValidationResponse;
import com.devisefutures.signaturevalidator.bsl.protocols.requestelems.Base64DataType;
import com.devisefutures.signaturevalidator.bsl.protocols.responseelems.OtherReport;
import com.devisefutures.signaturevalidator.bsl.protocols.responseelems.ResultMajor;
import com.devisefutures.signaturevalidator.bsl.protocols.responseelems.ValReportContainerType;
import eu.europa.esig.dss.detailedreport.DetailedReport;
import eu.europa.esig.dss.detailedreport.DetailedReportFacade;
import eu.europa.esig.dss.diagnostic.DiagnosticData;
import eu.europa.esig.dss.diagnostic.DiagnosticDataFacade;
import eu.europa.esig.dss.simplereport.SimpleReport;
import eu.europa.esig.dss.simplereport.SimpleReportFacade;
import eu.europa.esig.dss.validation.reports.Reports;
import eu.europa.esig.validationreport.ValidationReportFacade;
import eu.europa.esig.validationreport.jaxb.ValidationReportType;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.bind.ValidationException;
import javax.xml.transform.TransformerException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides functionalities to build signature validation response messages
 */
@Service
public class ResponseAssemblerService {

    @Autowired
    private CompressionService compressionService;

    /**
     * Builds a response when the validation request was successfully
     * processed and has returned an ETSI validation report
     * @param reqId The request's id to which the response corresponds
     * @param validationResult Container with all reports produced in
     * result of validation process application
     * @return ValidationResponse object
     * @throws ValidationException
     */
    public ValidationResponse buildResponse(String reqId, Reports validationResult) throws ValidationException {
        ValidationResponse response = new ValidationResponse();
        response.setReqId(reqId);
        response.getResult().setMaj(ResultMajor.SUCCESS.name());
        String reportsZipContent = zipSecundaryReports(validationResult);

        ValidationReportType etsiValidationReport = validationResult.getEtsiValidationReportJaxb();
        try {
            String etsiValidationReportStr = ValidationReportFacade.newFacade().marshall(etsiValidationReport);
            String etsiValidationReportB64 = Base64.toBase64String(etsiValidationReportStr.getBytes());

            ValReportContainerType validationReport = response.getOptOutp().getValidationReport();
            validationReport.setEtsiTS11910202XMLReport(etsiValidationReportB64);

            Base64DataType base64DataType = new Base64DataType();
            base64DataType.setVal(reportsZipContent);
            OtherReport otherReport = new OtherReport();
            otherReport.setContent(base64DataType);
            validationReport.setOther(otherReport);

            validationReport.setSigned(etsiValidationReport.getSignature() != null);

            return response;
        } catch (JAXBException | IOException | SAXException e){
            throw new ValidationException("Error marshalling etsiValReport");
        }
    }

    /**
     * Zips all the secundary reports to a zip base-64 encoded string
     * @param reports Secundary validation output reports
     * @return Base64 encoded string representing all secundary reports
     */
    private String zipSecundaryReports(Reports reports) throws ValidationException {
        Map<String, OutputStream> zipContent = new HashMap<>();
        SimpleReport simpleReport = reports.getSimpleReport();
        DetailedReport detailedReport = reports.getDetailedReport();
        DiagnosticData diagnosticData = reports.getDiagnosticData();
        SimpleReportFacade simpleReportFacade = new SimpleReportFacade();

        try {
            String simpleReportStr = simpleReportFacade.generateHtmlReport(simpleReport.getJaxbModel());
            OutputStream simpleReportStream = stream(simpleReportStr);
            zipContent.put("SimpleReport.html", simpleReportStream);

            String detailedReportStr = DetailedReportFacade.newFacade().generateHtmlReport(detailedReport.getJAXBModel());
            OutputStream detailedReportStream = stream(detailedReportStr);
            zipContent.put("DetailedReport.html", detailedReportStream);

            String diagnosticDataStr = DiagnosticDataFacade.newFacade().generateSVG(diagnosticData.getJaxbModel());
            OutputStream diagnosticDataStream = stream(diagnosticDataStr);
            zipContent.put("DiagnosticData.xml", diagnosticDataStream);

            return compressionService.compress(zipContent);
        } catch (Exception e){
            throw new ValidationException("Error generating secundary reports container");
        }
    }

    /**
     *
     * @param content
     * @return
     */
    private OutputStream stream(String content){
        ByteArrayOutputStream contentStream = new ByteArrayOutputStream();
        PrintWriter out = new PrintWriter(contentStream);
        out.append(content);
        out.flush();
        out.close();
        return contentStream;
    }
}
