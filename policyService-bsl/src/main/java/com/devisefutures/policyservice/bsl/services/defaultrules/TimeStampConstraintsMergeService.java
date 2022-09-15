package com.devisefutures.policyservice.bsl.services.defaultrules;

import eu.europa.esig.dss.policy.jaxb.TimestampConstraints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeStampConstraintsMergeService {

    @Autowired
    private BasicSignatureConstraintsMergeService basicSignatureConstraintsMergeService;

    @Autowired
    private SignedAttributesConstraintsMergeService signedAttributesConstraintsMergeService;

    public TimestampConstraints merge(TimestampConstraints timestampConstraints, TimestampConstraints baseTimeStConstraints){
        if(timestampConstraints == null)
            return baseTimeStConstraints;

        if(timestampConstraints.getTimestampDelay() != null) baseTimeStConstraints.setTimestampDelay(timestampConstraints.getTimestampDelay());
        if(timestampConstraints.getRevocationTimeAgainstBestSignatureTime() != null)
            baseTimeStConstraints.setRevocationTimeAgainstBestSignatureTime(timestampConstraints.getRevocationTimeAgainstBestSignatureTime());
        if(timestampConstraints.getBestSignatureTimeBeforeExpirationDateOfSigningCertificate() != null)
            baseTimeStConstraints.setBestSignatureTimeBeforeExpirationDateOfSigningCertificate(timestampConstraints.getBestSignatureTimeBeforeExpirationDateOfSigningCertificate());
        if(timestampConstraints.getCoherence() != null) baseTimeStConstraints.setCoherence(timestampConstraints.getCoherence());
        if(timestampConstraints.getTSAGeneralNamePresent() != null) baseTimeStConstraints.setTSAGeneralNamePresent(timestampConstraints.getTSAGeneralNamePresent());
        if(timestampConstraints.getTSAGeneralNameContentMatch() != null) baseTimeStConstraints.setTSAGeneralNameContentMatch(timestampConstraints.getTSAGeneralNameContentMatch());
        if(timestampConstraints.getTSAGeneralNameOrderMatch() != null) baseTimeStConstraints.setTSAGeneralNameOrderMatch(timestampConstraints.getTSAGeneralNameOrderMatch());
        if(timestampConstraints.getBasicSignatureConstraints() != null)
            baseTimeStConstraints.setBasicSignatureConstraints(
                    basicSignatureConstraintsMergeService.merge(
                            timestampConstraints.getBasicSignatureConstraints(),
                            baseTimeStConstraints.getBasicSignatureConstraints()
                    )
            );
        if(timestampConstraints.getSignedAttributes() != null)
            baseTimeStConstraints.setSignedAttributes(
                    signedAttributesConstraintsMergeService.merge(
                            timestampConstraints.getSignedAttributes(),
                            baseTimeStConstraints.getSignedAttributes()
                    )
            );
        return baseTimeStConstraints;
    }
}
