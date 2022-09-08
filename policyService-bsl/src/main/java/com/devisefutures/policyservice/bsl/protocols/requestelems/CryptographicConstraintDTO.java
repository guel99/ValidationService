package com.devisefutures.policyservice.bsl.protocols.requestelems;

import eu.europa.esig.dss.policy.jaxb.Algo;
import lombok.Data;

import java.util.List;

/**
 * Group of constraints related to cryptographic usage
 */
@Data
public class CryptographicConstraintDTO {

    /**
     * Checks if the encryption algorithm is in the allowed values
     * Checks if the key length for the encryption is acceptable
     */
    private List<Algo> acceptableEncryptionAlgo;

    /**
     * Checks if the digest algorithm is in the allowed values
     * Checks if the key length for the encryption is acceptable
     */
    private List<Algo> acceptableDigestAlgo;

    /**
     * Date format for expiration dates
     */
    private String dateFormat;
}
