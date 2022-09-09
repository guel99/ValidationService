package com.devisefutures.policyservice.bsl.protocols.requestelems;

import com.devisefutures.signaturevalidator.common.annotations.EnumValidator;
import eu.europa.esig.dss.policy.jaxb.Level;
import lombok.Data;

/**
 * Group of constraints on the unsigned attributes/properties
 */
@Data
public class UnsignedAttributesConstraintsDTO {

    /**
     * Checks the presence of a counter-signature attribute
     */
    @EnumValidator(enumClazz = Level.class)
    private String counterSignature;
}
