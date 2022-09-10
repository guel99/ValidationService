package com.devisefutures.policyservice.bsl.protocols.requestelems;

import com.devisefutures.signaturevalidator.common.annotations.EnumValidator;
import eu.europa.esig.dss.policy.jaxb.Level;
import lombok.Data;

import java.util.List;

/**
 * Group of constraints for the signature(s) (signed/unsigned properties, coverage,...)
 */
@Data
public class SignatureConstraintsDTO {

    /**
     * Checks the structural content (mainly XAdES against its XML Schema)
     */
    @EnumValidator(enumClazz = Level.class)
    private String structuralValidation;

    /**
     * Checks if used signature policy is in the
     * acceptable values list
     */
    private MultiValuesConstraintDTO acceptablePolicies;

    /**
     * Checks the availability of the signature policy (PDF resource,...)
     */
    @EnumValidator(enumClazz = Level.class)
    private String policyAvailable;

    /**
     * Checks the presence of a SignaturePolicyStore as an
     * unsigned attribute, containing a policy content
     */
    @EnumValidator(enumClazz = Level.class)
    private String signaturePolicyStorePresent;

    /**
     * Checks the hash value for the given signature policy
     */
    @EnumValidator(enumClazz = Level.class)
    private String policyHashMatch;

    /**
     * Checks if signature format (XAdES-BASELINE-B,...)
     * is in the acceptable values list
     */
    private MultiValuesConstraintDTO acceptableFormats;

    /**
     * Checks if the signature cover the complete document
     */
    @EnumValidator(enumClazz = Level.class)
    private String fullScope;

    /**
     * Group of common checks
     */
    private BasicSignatureConstraintsDTO basicSignatureConstraints;

    /**
     * Group of checks on the signed attributes/properties
     */
    private SignedAttributesConstraintsDTO signedAttributesConstraints;

    /**
     * Group of checks on the unsigned attributes/properties
     */
    private UnsignedAttributesConstraintsDTO unsignedAttributesConstraints;
}
