package com.devisefutures.policyservice.bsl.services.defaultrules;

import eu.europa.esig.dss.policy.jaxb.SignedAttributesConstraints;
import org.springframework.stereotype.Service;

@Service
public class SignedAttributesConstraintsMergeService {

    public SignedAttributesConstraints merge(SignedAttributesConstraints signedAttributesConstraints, SignedAttributesConstraints baseSigAttrConstraints){
        if(signedAttributesConstraints == null)
            return baseSigAttrConstraints;

        if(signedAttributesConstraints.getSigningCertificatePresent() != null) baseSigAttrConstraints.setSigningCertificatePresent(signedAttributesConstraints.getSigningCertificatePresent());
        if(signedAttributesConstraints.getUnicitySigningCertificate() != null) baseSigAttrConstraints.setUnicitySigningCertificate(signedAttributesConstraints.getUnicitySigningCertificate());
        if(signedAttributesConstraints.getSigningCertificateRefersCertificateChain() != null) baseSigAttrConstraints.setSigningCertificateRefersCertificateChain(signedAttributesConstraints.getSigningCertificateRefersCertificateChain());
        if(signedAttributesConstraints.getReferencesToAllCertificateChainPresent() != null) baseSigAttrConstraints.setReferencesToAllCertificateChainPresent(signedAttributesConstraints.getReferencesToAllCertificateChainPresent());
        if(signedAttributesConstraints.getSigningCertificateDigestAlgorithm() != null) baseSigAttrConstraints.setSigningCertificateDigestAlgorithm(signedAttributesConstraints.getSigningCertificateDigestAlgorithm());
        if(signedAttributesConstraints.getCertDigestPresent() != null) baseSigAttrConstraints.setCertDigestPresent(signedAttributesConstraints.getCertDigestPresent());
        if(signedAttributesConstraints.getCertDigestMatch() != null) baseSigAttrConstraints.setCertDigestMatch(signedAttributesConstraints.getCertDigestMatch());
        if(signedAttributesConstraints.getIssuerSerialMatch() != null) baseSigAttrConstraints.setIssuerSerialMatch(signedAttributesConstraints.getIssuerSerialMatch());
        if(signedAttributesConstraints.getKeyIdentifierPresent() != null) baseSigAttrConstraints.setKeyIdentifierPresent(signedAttributesConstraints.getKeyIdentifierPresent());
        if(signedAttributesConstraints.getKeyIdentifierMatch() != null) baseSigAttrConstraints.setKeyIdentifierMatch(signedAttributesConstraints.getKeyIdentifierMatch());
        if(signedAttributesConstraints.getSigningTime() != null) baseSigAttrConstraints.setSigningTime(signedAttributesConstraints.getSigningTime());
        if(signedAttributesConstraints.getContentType() != null) baseSigAttrConstraints.setContentType(signedAttributesConstraints.getContentType());
        if(signedAttributesConstraints.getContentHints() != null) baseSigAttrConstraints.setContentHints(signedAttributesConstraints.getContentHints());
        if(signedAttributesConstraints.getContentIdentifier() != null) baseSigAttrConstraints.setContentIdentifier(signedAttributesConstraints.getContentIdentifier());
        if(signedAttributesConstraints.getMessageDigestOrSignedPropertiesPresent() != null) baseSigAttrConstraints.setMessageDigestOrSignedPropertiesPresent(signedAttributesConstraints.getMessageDigestOrSignedPropertiesPresent());
        if(signedAttributesConstraints.getCommitmentTypeIndication() != null) baseSigAttrConstraints.setCommitmentTypeIndication(signedAttributesConstraints.getCommitmentTypeIndication());
        if(signedAttributesConstraints.getSignerLocation() != null) baseSigAttrConstraints.setSignerLocation(signedAttributesConstraints.getSignerLocation());
        if(signedAttributesConstraints.getClaimedRoles() != null) baseSigAttrConstraints.setClaimedRoles(signedAttributesConstraints.getClaimedRoles());
        if(signedAttributesConstraints.getCertifiedRoles() != null) baseSigAttrConstraints.setCertifiedRoles(signedAttributesConstraints.getCertifiedRoles());
        if(signedAttributesConstraints.getContentTimeStamp() != null) baseSigAttrConstraints.setContentTimeStamp(signedAttributesConstraints.getContentTimeStamp());
        if(signedAttributesConstraints.getContentTimeStampMessageImprint() != null) baseSigAttrConstraints.setContentTimeStampMessageImprint(signedAttributesConstraints.getContentTimeStampMessageImprint());

        return baseSigAttrConstraints;
    }
}
