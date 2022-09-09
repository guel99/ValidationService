package com.devisefutures.policyservice.bsl.protocols.requestelems;

import com.devisefutures.signaturevalidator.common.annotations.EnumValidator;
import eu.europa.esig.dss.policy.jaxb.Level;
import lombok.Data;

/**
 * Group of checks related to Trusted Lists
 */
@Data
public class EIDASDTO {

    /**
     * Checks the Trusted List freshness to ensure the validation uses an up-to-date version
     */
    private TimeConstraintDTO tlFreshness;

    /**
     * Checks if the Trusted List is not expired (NextUpdate)
     */
    @EnumValidator(enumClazz = Level.class)
    private String tlNotExpired;

    /**
     * Checks if the signature of the Trusted List is valid
     */
    @EnumValidator(enumClazz = Level.class)
    private String tlWellSigned;

    /**
     * Checks if the version (TSLVersionIdentifier) of the
     * Trusted List is equals to the given value
     */
    private ValueConstraintDTO tlVersion;
}
