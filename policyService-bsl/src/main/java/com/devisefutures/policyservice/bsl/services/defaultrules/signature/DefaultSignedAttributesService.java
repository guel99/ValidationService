package com.devisefutures.policyservice.bsl.services.defaultrules.signature;

import eu.europa.esig.dss.policy.jaxb.Level;
import eu.europa.esig.dss.policy.jaxb.LevelConstraint;
import eu.europa.esig.dss.policy.jaxb.SignedAttributesConstraints;
import org.springframework.stereotype.Service;

@Service
public class DefaultSignedAttributesService {

    private static final Level DEFAULT_SIGNING_CERTIFICATE_PRESENT = Level.WARN;

    private static final Level DEFAULT_UNICITY_SIGNING_CERTIFICATE = Level.WARN;

    private static final Level DEFAULT_SIGNING_CERTIFICATE_REFERS_CERTIFICATE_CHAIN = Level.WARN;

    private static final Level DEFAULT_CERT_DIGEST_PRESENT = Level.FAIL;

    private static final Level DEFAULT_CERT_DIGEST_MATCH = Level.FAIL;

    private static final Level DEFAULT_ISSUER_SERIAL_MATCH = Level.FAIL;

    private static final Level DEFAULT_SIGNING_TIME = Level.FAIL;

    private static final Level DEFAULT_MESSAGE_DIGEST_OR_SIGNED_PROPERTIES_PRESENT = Level.FAIL;

    /**
     * Applies the default rules for non-editable/empty fields
     * related with basic signature signed attributes constraints
     * @param signedAttributesConstraints The signed attributes constraints object
     * @return The signed attributes constraints constraints with the default parameters
     */
    public SignedAttributesConstraints setDefaultRules(SignedAttributesConstraints signedAttributesConstraints){
        if(signedAttributesConstraints == null){
            signedAttributesConstraints = new SignedAttributesConstraints();
        }
        setDefaultSigningCertificatePresent(signedAttributesConstraints);
        setDefaultUnicitySigningCertificate(signedAttributesConstraints);
        setDefaultSigningCertificateRefersCertificateChain(signedAttributesConstraints);
        setDefaultCertDigestPresent(signedAttributesConstraints);
        setDefaultCertDigestMatch(signedAttributesConstraints);
        setDefaultIssuerSerialMatch(signedAttributesConstraints);
        setDefaultSigningTime(signedAttributesConstraints);
        setDefaultMessageDigestOrSignedPropertiesPresent(signedAttributesConstraints);

        return signedAttributesConstraints;
    }

    /**
     * Sets the default value to signing certificate present constraint
     * @param signedAttributesConstraints The signed attributes constraints
     */
    public void setDefaultSigningCertificatePresent(SignedAttributesConstraints signedAttributesConstraints){
        LevelConstraint signingCertificatePresent = signedAttributesConstraints.getSigningCertificatePresent();
        if(signingCertificatePresent == null){
            signingCertificatePresent = new LevelConstraint();
            signingCertificatePresent.setLevel(DEFAULT_SIGNING_CERTIFICATE_PRESENT);
            signedAttributesConstraints.setSigningCertificatePresent(signingCertificatePresent);
        }
    }

    public void setDefaultUnicitySigningCertificate(SignedAttributesConstraints signedAttributesConstraints){
        LevelConstraint unicitySigningCertificate = signedAttributesConstraints.getUnicitySigningCertificate();
        if(unicitySigningCertificate == null){
            unicitySigningCertificate = new LevelConstraint();
            unicitySigningCertificate.setLevel(DEFAULT_UNICITY_SIGNING_CERTIFICATE);
            signedAttributesConstraints.setUnicitySigningCertificate(unicitySigningCertificate);
        }
    }

    public void setDefaultSigningCertificateRefersCertificateChain(SignedAttributesConstraints signedAttributesConstraints){
        LevelConstraint signingCertificateRefersCertificateChain = signedAttributesConstraints.getSigningCertificateRefersCertificateChain();
        if(signingCertificateRefersCertificateChain == null){
            signingCertificateRefersCertificateChain = new LevelConstraint();
            signingCertificateRefersCertificateChain.setLevel(DEFAULT_SIGNING_CERTIFICATE_REFERS_CERTIFICATE_CHAIN);
            signedAttributesConstraints.setSigningCertificateRefersCertificateChain(signingCertificateRefersCertificateChain);
        }
    }

    public void setDefaultCertDigestPresent(SignedAttributesConstraints signedAttributesConstraints){
        LevelConstraint certDigestPresent = signedAttributesConstraints.getCertDigestPresent();
        if(certDigestPresent == null){
            certDigestPresent = new LevelConstraint();
            certDigestPresent.setLevel(DEFAULT_CERT_DIGEST_PRESENT);
            signedAttributesConstraints.setCertDigestPresent(certDigestPresent);
        }
    }

    public void setDefaultCertDigestMatch(SignedAttributesConstraints signedAttributesConstraints){
        LevelConstraint certDigestMatch = signedAttributesConstraints.getCertDigestMatch();
        if(certDigestMatch == null){
            certDigestMatch = new LevelConstraint();
            certDigestMatch.setLevel(DEFAULT_CERT_DIGEST_MATCH);
            signedAttributesConstraints.setCertDigestMatch(certDigestMatch);
        }
    }

    public void setDefaultIssuerSerialMatch(SignedAttributesConstraints signedAttributesConstraints){
        LevelConstraint issuerSerialMatch = signedAttributesConstraints.getIssuerSerialMatch();
        if(issuerSerialMatch == null){
            issuerSerialMatch = new LevelConstraint();
            issuerSerialMatch.setLevel(DEFAULT_ISSUER_SERIAL_MATCH);
            signedAttributesConstraints.setIssuerSerialMatch(issuerSerialMatch);
        }
    }

    public void setDefaultSigningTime(SignedAttributesConstraints signedAttributesConstraints){
        LevelConstraint signingTime = signedAttributesConstraints.getSigningTime();
        if(signingTime == null){
            signingTime = new LevelConstraint();
            signingTime.setLevel(DEFAULT_SIGNING_TIME);
            signedAttributesConstraints.setSigningTime(signingTime);
        }
    }

    public void setDefaultMessageDigestOrSignedPropertiesPresent(SignedAttributesConstraints signedAttributesConstraints){
        LevelConstraint messageDigestOrSignedPropertiesPresent = signedAttributesConstraints.getMessageDigestOrSignedPropertiesPresent();
        if(messageDigestOrSignedPropertiesPresent == null){
            messageDigestOrSignedPropertiesPresent = new LevelConstraint();
            messageDigestOrSignedPropertiesPresent.setLevel(DEFAULT_MESSAGE_DIGEST_OR_SIGNED_PROPERTIES_PRESENT);
            signedAttributesConstraints.setMessageDigestOrSignedPropertiesPresent(messageDigestOrSignedPropertiesPresent);
        }
    }
}
