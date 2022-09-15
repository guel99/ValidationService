package com.devisefutures.policyservice.bsl.services.defaultrules;

import eu.europa.esig.dss.policy.jaxb.CryptographicConstraint;
import org.springframework.stereotype.Service;

@Service
public class CryptographicConstraintMergeService {

    /**
     * Merges the first specified CryptographicConstraints object into the second one.
     * If the second one has some values already attributed, they will be changed
     * to the same values specified in the first one
     * @param cryptographicConstraint The source CryptographicConstraint
     * @param baseCryptoConstraint The target CryptographicConstraint where they were merged to
     * @return The merged basic signature constraints
     */
    public CryptographicConstraint merge(CryptographicConstraint cryptographicConstraint, CryptographicConstraint baseCryptoConstraint){
        if(cryptographicConstraint == null)
            return baseCryptoConstraint;

        if(cryptographicConstraint.getAcceptableDigestAlgo() != null) baseCryptoConstraint.setAcceptableDigestAlgo(cryptographicConstraint.getAcceptableDigestAlgo());
        if(cryptographicConstraint.getAcceptableEncryptionAlgo() != null) baseCryptoConstraint.setAcceptableEncryptionAlgo(cryptographicConstraint.getAcceptableEncryptionAlgo());
        if(cryptographicConstraint.getMiniPublicKeySize() != null) baseCryptoConstraint.setMiniPublicKeySize(cryptographicConstraint.getMiniPublicKeySize());
        if(cryptographicConstraint.getAlgoExpirationDate() != null) baseCryptoConstraint.setAlgoExpirationDate(cryptographicConstraint.getAlgoExpirationDate());
        if(cryptographicConstraint.getLevel() != null) baseCryptoConstraint.setLevel(cryptographicConstraint.getLevel());

        return baseCryptoConstraint;
    }
}
