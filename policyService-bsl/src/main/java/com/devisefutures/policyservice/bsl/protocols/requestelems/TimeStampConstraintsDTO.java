package com.devisefutures.policyservice.bsl.protocols.requestelems;

import com.devisefutures.signaturevalidator.common.annotations.EnumValidator;
import eu.europa.esig.dss.policy.jaxb.Level;
import lombok.Data;

@Data
public class TimeStampConstraintsDTO {

    /**
     * Checks the delay between the best-signature-time
     * and the claimed signing time
     */
    private TimeConstraintDTO timestampDelay;

    /**
     * Checks if a TSTInfo.tsa field is present for a timestamp
     */
    @EnumValidator(enumClazz = Level.class)
    private String tSAGeneralNamePresent;

    /**
     * Checks if a TSTInfo.tsa field's value matches the timestamp's
     * issuer distinguishing name when present
     */
    @EnumValidator(enumClazz = Level.class)
    private String tSAGeneralNameContentMatch;

    /**
     * Checks if a TSTInfo.tsa field's value and order matches the
     * timestamp's issuer distinguishing name when present
     */
    @EnumValidator(enumClazz = Level.class)
    private String tSAGeneralNameOrderMatch;

    /**
     * Group of common checks on this token signature
     */
    private BasicSignatureConstraintsDTO basicSignatureConstraints;

    /**
     * Group of checks on the signed attributes/properties
     */
    private SignedAttributesConstraintsDTO signedAttributesConstraints;
}
