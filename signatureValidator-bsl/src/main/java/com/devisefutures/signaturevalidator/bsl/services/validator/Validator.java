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

/**
 * Element that performs the signature validation
 */
@Service
public class Validator {

    @Autowired
    private TSLHandler tslHandler;

    /**
     * Performs the validation of one dssdocument
     * @param toValidate The document to validate
     * @param certificateSource The certificates used in the signature validation process
     * @return Reports
     */
    public Reports validate(DocumentValidator toValidate, CertificateSource certificateSource){
        CertificateVerifier certificateVerifier = getCustomCertificateVerifier();
        certificateVerifier.setTrustedCertSources(certificateSource);

        toValidate.setCertificateVerifier(certificateVerifier);
        return toValidate.validateDocument();
    }

    /**
     * Performs the validation of one dssdocument against the
     * certificates presents in the European List of trusted lists
     * @param toValidate The document to validate
     * @return
     */
    public Reports validate(DocumentValidator toValidate){
        CertificateVerifier certificateVerifier = getCustomCertificateVerifier();
        CertificateSource certificateSource = tslHandler.buildTrustedListsCertificateSource(TSLHandler.EUROPEAN_LOTL_URL);
        certificateVerifier.setTrustedCertSources(certificateSource);

        toValidate.setCertificateVerifier(certificateVerifier);
        return toValidate.validateDocument();
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
