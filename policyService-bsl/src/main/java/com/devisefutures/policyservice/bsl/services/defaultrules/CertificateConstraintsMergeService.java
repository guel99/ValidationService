package com.devisefutures.policyservice.bsl.services.defaultrules;

import eu.europa.esig.dss.policy.jaxb.CertificateConstraints;
import org.springframework.stereotype.Service;

@Service
public class CertificateConstraintsMergeService {

    /**
     * Merges the first specified CertificateConstraints object into the second one.
     * If the second one has some values already attributed, they will be changed
     * to the same values specified in the first one
     * @param certificateConstraints The source CertificateConstraints
     * @param baseCertConstraints The target CertificateConstraints where they were merged to
     * @return The merged certificate constraints
     */
    public CertificateConstraints merge(CertificateConstraints certificateConstraints, CertificateConstraints baseCertConstraints){
        if(certificateConstraints == null)
            return baseCertConstraints;

        if(certificateConstraints.getRecognition() != null) baseCertConstraints.setRecognition(certificateConstraints.getRecognition());
        if(certificateConstraints.getSignature() != null) baseCertConstraints.setSignature(certificateConstraints.getSignature());
        if(certificateConstraints.getNotExpired() != null) baseCertConstraints.setNotExpired(certificateConstraints.getNotExpired());
        if(certificateConstraints.getAuthorityInfoAccessPresent() != null) baseCertConstraints.setAuthorityInfoAccessPresent(certificateConstraints.getAuthorityInfoAccessPresent());
        if(certificateConstraints.getRevocationInfoAccessPresent() != null) baseCertConstraints.setRevocationInfoAccessPresent(certificateConstraints.getRevocationInfoAccessPresent());
        if(certificateConstraints.getRevocationDataAvailable() != null) baseCertConstraints.setRevocationDataAvailable(certificateConstraints.getRevocationDataAvailable());
        if(certificateConstraints.getAcceptableRevocationDataFound() != null) baseCertConstraints.setAcceptableRevocationDataFound(certificateConstraints.getAcceptableRevocationDataFound());
        if(certificateConstraints.getCRLNextUpdatePresent() != null) baseCertConstraints.setCRLNextUpdatePresent(certificateConstraints.getCRLNextUpdatePresent());
        if(certificateConstraints.getOCSPNextUpdatePresent() != null) baseCertConstraints.setOCSPNextUpdatePresent(certificateConstraints.getOCSPNextUpdatePresent());
        if(certificateConstraints.getRevocationFreshness() != null) baseCertConstraints.setRevocationFreshness(certificateConstraints.getRevocationFreshness());
        if(certificateConstraints.getRevocationFreshnessNextUpdate() != null) baseCertConstraints.setRevocationFreshnessNextUpdate(certificateConstraints.getRevocationFreshnessNextUpdate());
        if(certificateConstraints.getKeyUsage() != null) baseCertConstraints.setKeyUsage(certificateConstraints.getKeyUsage());
        if(certificateConstraints.getExtendedKeyUsage() != null) baseCertConstraints.setExtendedKeyUsage(certificateConstraints.getExtendedKeyUsage());
        if(certificateConstraints.getSurname() != null) baseCertConstraints.setSurname(certificateConstraints.getSurname());
        if(certificateConstraints.getGivenName() != null) baseCertConstraints.setGivenName(certificateConstraints.getGivenName());
        if(certificateConstraints.getCommonName() != null) baseCertConstraints.setCommonName(certificateConstraints.getCommonName());
        if(certificateConstraints.getPseudonym() != null) baseCertConstraints.setPseudonym(certificateConstraints.getPseudonym());
        if(certificateConstraints.getOrganizationUnit() != null) baseCertConstraints.setOrganizationUnit(certificateConstraints.getOrganizationUnit());
        if(certificateConstraints.getOrganizationName() != null) baseCertConstraints.setOrganizationName(certificateConstraints.getOrganizationName());
        if(certificateConstraints.getCountry() != null) baseCertConstraints.setCountry(certificateConstraints.getCountry());
        if(certificateConstraints.getSerialNumberPresent() != null) baseCertConstraints.setSerialNumberPresent(certificateConstraints.getSerialNumberPresent());
        if(certificateConstraints.getNotRevoked() != null) baseCertConstraints.setNotRevoked(certificateConstraints.getNotRevoked());
        if(certificateConstraints.getNotOnHold() != null) baseCertConstraints.setNotOnHold(certificateConstraints.getNotOnHold());
        if(certificateConstraints.getRevocationIssuerNotExpired() != null) baseCertConstraints.setRevocationIssuerNotExpired(certificateConstraints.getRevocationIssuerNotExpired());
        if(certificateConstraints.getSelfSigned() != null) baseCertConstraints.setSelfSigned(certificateConstraints.getSelfSigned());
        if(certificateConstraints.getNotSelfSigned() != null) baseCertConstraints.setNotSelfSigned(certificateConstraints.getNotSelfSigned());
        if(certificateConstraints.getPolicyIds() != null) baseCertConstraints.setPolicyIds(certificateConstraints.getPolicyIds());
        if(certificateConstraints.getIssuedToNaturalPerson() != null) baseCertConstraints.setIssuedToNaturalPerson(certificateConstraints.getIssuedToNaturalPerson());
        if(certificateConstraints.getIssuedToLegalPerson() != null) baseCertConstraints.setIssuedToLegalPerson(certificateConstraints.getIssuedToLegalPerson());
        if(certificateConstraints.getSemanticsIdentifier() != null) baseCertConstraints.setSemanticsIdentifier(certificateConstraints.getSemanticsIdentifier());
        if(certificateConstraints.getUsePseudonym() != null) baseCertConstraints.setUsePseudonym(certificateConstraints.getUsePseudonym());
        if(certificateConstraints.getCryptographic() != null) baseCertConstraints.setCryptographic(certificateConstraints.getCryptographic());
        return baseCertConstraints;
    }
}
