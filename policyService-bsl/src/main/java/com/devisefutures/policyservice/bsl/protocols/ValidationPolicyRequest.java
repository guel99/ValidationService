package com.devisefutures.policyservice.bsl.protocols;

import com.devisefutures.policyservice.bsl.protocols.requestelems.*;
import eu.europa.esig.dss.policy.jaxb.ConstraintsParameters;
import lombok.Data;

@Data
public class ValidationPolicyRequest {

    /**
     * The validation policy description
     */
    private String description;

    /**
     * ASiC specific constraints
     */
    private ContainerConstraintsDTO containerConstraints;

    /**
     * Constraints on the signature (signed/unsigned properties, coverage,...)
     */
    private SignatureConstraintsDTO signatureConstraints;

    /**
     * Constraints related to the counter-signatures
     */
    private SignatureConstraintsDTO counterSignatureConstraints;

    /**
     * Constraints on the timestamps
     */
    private TimeStampConstraintsDTO timeStampConstraints;

    /**
     * Constraints related to the revocation data
     */
    private RevocationConstraintsDTO revocationConstraints;

    /**
     * Global constraints about cryptographic usage (encryption, digest, key length, algorithm deprecation,...).
     * If no specific constraints is added in Signature/Timestamp/... elements, this configuration will be used.
     */
    private CryptographicConstraintDTO cryptographicConstraint;

    /**
     * Checks / Defines the validation model shell, chain or hybrid
     */
    private ModelConstraintDTO modelConstraint;

    /**
     * Constraints related to the European context (trusted list validity,...)
     */
    private EIDASDTO eIDAS;

    /**
     * The policy Name
     */
    private String name;
}
