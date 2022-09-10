package com.devisefutures.policyservice.bsl.protocols.requestelems;

import com.devisefutures.signaturevalidator.common.annotations.EnumValidator;
import eu.europa.esig.dss.policy.jaxb.Algo;
import eu.europa.esig.dss.policy.jaxb.Level;
import lombok.Data;

import java.util.List;

/**
 * Group of constraints related to cryptographic usage
 */
@Data
public class CryptographicConstraintDTO {

    /**
     * Checks if the key length for the encryption is acceptable
     */
    private List<Algo> acceptableEncryptionAlgo;

    /**
     * Checks if the digest algorithm is in the allowed values
     */
    private List<Algo> acceptableDigestAlgo;

    /**
     * Checks if the digest algorithm is in the allowed values
     */
    private List<Algo> miniPublicKeySize;

    /**
     * Date format for expiration dates
     */
    private AlgoExpirationDateDTO algoExpirationDate;

    @EnumValidator(enumClazz = Level.class)
    private String level;
}
