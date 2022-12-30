package com.devisefutures.signaturevalidator.bsl.services.validator;

import eu.europa.esig.dss.service.crl.OnlineCRLSource;
import eu.europa.esig.dss.service.ocsp.OnlineOCSPSource;
import eu.europa.esig.dss.spi.x509.CertificateSource;
import eu.europa.esig.dss.spi.x509.aia.DefaultAIASource;
import eu.europa.esig.dss.validation.CertificateVerifier;
import eu.europa.esig.dss.validation.CommonCertificateVerifier;
import eu.europa.esig.dss.validation.DocumentValidator;
import eu.europa.esig.dss.validation.reports.Reports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;

/**
 * Element that performs the signature validation
 */
@Service
public class Validator {

    @Autowired
    private TSLHandler tslHandler;

    /**
     * Performs the validation of a dss document using the certificate sources
     * given as parameter, applying the validation policy passed as parameter.
     * @param toValidate The document to validate
     * @param certificateSource The certificate source used in the X.509 validation building block.
     *                          If the value passed is null, then the European LOTL is applyed.
     * @param validationPolicy The validation policy to be used by the validation process.
     *                         If the value passed in null, the default DSS validation policy is applyed.
     * @return The validation reports
     */
    public Reports validate(DocumentValidator toValidate, CertificateSource certificateSource, InputStream validationPolicy){
        CertificateVerifier certificateVerifier = getCustomCertificateVerifier();
        if(certificateSource == null)
            certificateSource = tslHandler.buildTrustedListsCertificateSource(TSLHandler.EUROPEAN_LOTL_URL);
        certificateVerifier.setTrustedCertSources(certificateSource);

        toValidate.setCertificateVerifier(certificateVerifier);
        return validationPolicy == null ? toValidate.validateDocument() : toValidate.validateDocument(validationPolicy);
    }

    /**
     * Performs the validation of a dss document using the certificate sources
     * given as parameter, applying the validation policy stored in the given url
     * @param toValidate The document to validate
     * @param certificateSource The certificate source used in the X.509 validation building block.
     *                          If the value passed is null, then the European LOTL is applyed.
     * @param policyURL The url where is stored the validation policy to be used by the validation process.
     *                  If the value passed in null, the default DSS validation policy is applyed.
     * @return The validation reports
     */
    public Reports validate(DocumentValidator toValidate, CertificateSource certificateSource, URL policyURL){
        CertificateVerifier certificateVerifier = getCustomCertificateVerifier();
        if(certificateSource == null)
            certificateSource = tslHandler.buildTrustedListsCertificateSource(TSLHandler.EUROPEAN_LOTL_URL);
        certificateVerifier.setTrustedCertSources(certificateSource);

        toValidate.setCertificateVerifier(certificateVerifier);
        return policyURL == null ? toValidate.validateDocument() : toValidate.validateDocument(policyURL);
    }

    /**
     * Performs the validation of one dssdocument
     * @param toValidate The document to validate
     * @param certificateSource The certificates used in the signature validation process
     * @return Reports
     */
    public Reports validate(DocumentValidator toValidate, CertificateSource certificateSource){
        return this.validate(toValidate, certificateSource, (InputStream) null);
    }

    /**
     * Performs the validation of one dssdocument against the
     * certificates presents in the European List of trusted lists
     * @param toValidate The document to validate
     * @return
     */
    public Reports validate(DocumentValidator toValidate){
        return this.validate(toValidate, null);
    }

    /**
     * Creates a custom certificate verifier
     * @return The CertificateVerifier
     */
    public CertificateVerifier getCustomCertificateVerifier(){
        CertificateVerifier certificateVerifier = new CommonCertificateVerifier();
        // Able to download resources from Authority Information Access
        certificateVerifier.setAIASource(new DefaultAIASource());
        // Able to request OCSP Responders
        certificateVerifier.setOcspSource(new OnlineOCSPSource());
        // Able to download CRL
        certificateVerifier.setCrlSource(new OnlineCRLSource());
        return certificateVerifier;
    }
}
