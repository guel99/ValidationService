package com.devisefutures.policyservice.bsl.protocols;

import com.devisefutures.policyservice.bsl.protocols.requestelems.ContainerConstraintsDTO;
import com.devisefutures.policyservice.bsl.protocols.requestelems.SignatureConstraintsDTO;
import com.devisefutures.policyservice.bsl.protocols.requestelems.TimeStampConstraintsDTO;
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
     * Checks / Defines the validation model shell, chain or hybrid
     */
    private ModelConstraintDTO modelConstraint;

    /**
     * Constraints related to the European context (trusted list validity,...)
     */
    private EIDASDTO eIDAS;
}
