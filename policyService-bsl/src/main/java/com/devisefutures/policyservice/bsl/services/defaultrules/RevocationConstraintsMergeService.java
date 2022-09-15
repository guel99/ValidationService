package com.devisefutures.policyservice.bsl.services.defaultrules;

import eu.europa.esig.dss.policy.jaxb.RevocationConstraints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RevocationConstraintsMergeService {

    @Autowired
    private BasicSignatureConstraintsMergeService basicSignatureConstraintsMergeService;

    public RevocationConstraints merge(RevocationConstraints revocationConstraints, RevocationConstraints baseRevocConstraints){
        if(revocationConstraints == null)
            return baseRevocConstraints;

        if(revocationConstraints.getUnknownStatus() != null) baseRevocConstraints.setUnknownStatus(revocationConstraints.getUnknownStatus());
        if(revocationConstraints.getOCSPCertHashPresent() != null) baseRevocConstraints.setOCSPCertHashPresent(revocationConstraints.getOCSPCertHashPresent());
        if(revocationConstraints.getOCSPCertHashMatch() != null) baseRevocConstraints.setOCSPCertHashMatch(revocationConstraints.getOCSPCertHashMatch());
        if(revocationConstraints.getSelfIssuedOCSP() != null) baseRevocConstraints.setSelfIssuedOCSP(revocationConstraints.getSelfIssuedOCSP());
        if(revocationConstraints.getBasicSignatureConstraints() != null)
            baseRevocConstraints.setBasicSignatureConstraints(
                    basicSignatureConstraintsMergeService.merge(
                            revocationConstraints.getBasicSignatureConstraints(),
                            baseRevocConstraints.getBasicSignatureConstraints()
                            )
            );

        return baseRevocConstraints;
    }
}
