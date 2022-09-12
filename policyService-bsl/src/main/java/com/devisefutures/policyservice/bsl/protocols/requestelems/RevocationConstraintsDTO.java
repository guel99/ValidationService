package com.devisefutures.policyservice.bsl.protocols.requestelems;

import com.devisefutures.signaturevalidator.common.annotations.EnumValidator;
import eu.europa.esig.dss.policy.jaxb.Level;
import lombok.Data;

/**
 * Group of constraints on the revocation data
 */
@Data
public class RevocationConstraintsDTO {

    /**
     * hecks the returned status is not unknown
     */
    @EnumValidator(enumClazz = Level.class)
    private String unknownStatus;

    /**
     * Checks if an OCSP response has certHash attribute
     */
    @EnumValidator(enumClazz = Level.class)
    private String ocspCertHashPresent;

    /**
     * Checks if a value of certHash attribute matches
     * the signing certificate
     */
    @EnumValidator(enumClazz = Level.class)
    private String ocspCertHashMatch;

    /**
     * Checks if a checked certificate appears in the
     * OCSP Responder's certificate path
     */
    @EnumValidator(enumClazz = Level.class)
    private String selfIssuedOCSP;

    /**
     * Group of common checks on this token signature
     */
    private BasicSignatureConstraintsDTO basicSignatureConstraints;


    @EnumValidator(enumClazz = Level.class)
    private String level;
}
