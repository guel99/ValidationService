package com.devisefutures.policyservice.bsl.services.defaultrules;

import eu.europa.esig.dss.policy.jaxb.Level;
import eu.europa.esig.dss.policy.jaxb.LevelConstraint;
import eu.europa.esig.dss.policy.jaxb.MultiValuesConstraint;
import eu.europa.esig.dss.policy.jaxb.SignatureConstraints;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultSignatureConstraintsService {

    /**
     * The default value for structural validation constraint
     */
    private static final Level DEFAULT_STRUCTURAL_VALIDATION = Level.FAIL;

    /**
     * Default value for acceptable policies constraint
     */
    private static final List<String> DEFAULT_ACCEPTABLE_POLICIES = List.of("ANY_POLICY", "NO_POLICY");
    private static final Level DEFAULT_ACCEPTABLE_POLICIES_LEVEL = Level.FAIL;

    /**
     * Default value for policy available constraint
     */
    private static final Level DEFAULT_POLICY_AVAILABLE = Level.FAIL;

    /**
     * Default value for policy hash match constraint
     */
    private static final Level DEFAULT_POLICY_HASH_MATCH = Level.FAIL;

    /**
     * Applies the default rules for non-editable/empty fields
     * related with SignatureConstraints objects
     * @param signatureConstraints The signature constraints object
     * @return The container constraints with the default parameters
     */
    public SignatureConstraints setDefaultRules(SignatureConstraints signatureConstraints){
        if(signatureConstraints == null){
            signatureConstraints = new SignatureConstraints();
        }
        setDefaultStructuralValidation(signatureConstraints);
        setDefaultAcceptablePolicies(signatureConstraints);
        setDefaultPolicyAvailable(signatureConstraints);
        setDefaultPolicyHashMatch(signatureConstraints);

        return signatureConstraints;
    }

    /**
     * Sets the default value to structural validation
     * @param signatureConstraints The signature constraints
     */
    private void setDefaultStructuralValidation(SignatureConstraints signatureConstraints){
        LevelConstraint structuralValidation = signatureConstraints.getStructuralValidation();
        if(structuralValidation == null){
            structuralValidation = new LevelConstraint();
            structuralValidation.setLevel(DEFAULT_STRUCTURAL_VALIDATION);
            signatureConstraints.setStructuralValidation(structuralValidation);
        }
    }

    /**
     * Sets the default value to acceptable policies
     * @param signatureConstraints The signature constraints
     */
    private void setDefaultAcceptablePolicies(SignatureConstraints signatureConstraints){
        MultiValuesConstraint acceptablePolicies = signatureConstraints.getAcceptablePolicies();
        if(acceptablePolicies == null){
            acceptablePolicies = new MultiValuesConstraint();
            acceptablePolicies.getId().addAll(DEFAULT_ACCEPTABLE_POLICIES);
            acceptablePolicies.setLevel(DEFAULT_ACCEPTABLE_POLICIES_LEVEL);
            signatureConstraints.setAcceptablePolicies(acceptablePolicies);
        }
    }

    /**
     * Sets the default value to policy available constraint
     * @param signatureConstraints The signature constraints
     */
    private void setDefaultPolicyAvailable(SignatureConstraints signatureConstraints){
        LevelConstraint policyAvailable = signatureConstraints.getPolicyAvailable();
        if(policyAvailable == null){
            policyAvailable = new LevelConstraint();
            policyAvailable.setLevel(DEFAULT_POLICY_AVAILABLE);
            signatureConstraints.setPolicyAvailable(policyAvailable);
        }
    }

    /**
     * Sets the default value to policy hash match constraint
     * @param signatureConstraints The signature constraints
     */
    private void setDefaultPolicyHashMatch(SignatureConstraints signatureConstraints){
        LevelConstraint policyHashMatch = signatureConstraints.getPolicyHashMatch();
        if(policyHashMatch == null){
            policyHashMatch = new LevelConstraint();
            policyHashMatch.setLevel(DEFAULT_POLICY_HASH_MATCH);
            signatureConstraints.setPolicyHashMatch(policyHashMatch);
        }
    }
}
