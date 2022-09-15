package com.devisefutures.policyservice.bsl.services.defaultrules;

import com.devisefutures.policyservice.bsl.services.defaultrules.signature.DefaultBasicSignatureConstraintsService;
import com.devisefutures.policyservice.bsl.services.defaultrules.signature.DefaultSignedAttributesService;
import com.devisefutures.policyservice.bsl.services.defaultrules.signature.DefaultUnsignedAttributesService;
import eu.europa.esig.dss.policy.jaxb.Level;
import eu.europa.esig.dss.policy.jaxb.LevelConstraint;
import eu.europa.esig.dss.policy.jaxb.MultiValuesConstraint;
import eu.europa.esig.dss.policy.jaxb.SignatureConstraints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
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
     * Default value for acceptable signature formats
     */
    private static final List<String> DEFAULT_ACCEPTABLE_FORMATS = List.of("*");
    private static final Level DEFAULT_ACCEPTABLE_FORMATS_LEVEL = Level.FAIL;

    /**
     * Default value for full scope constraint
     */
    private static final Level DEFAULT_FULL_SCOPE = Level.INFORM;

    /**
     * Provides default values for empty/non-editable constraints
     * related with signature basic signature constraints
     */
    @Autowired
    private DefaultBasicSignatureConstraintsService defaultBasicSignatureConstraintsService;

    /**
     * Provides default values for empty/non-editable constraints
     * related with signature signedAttributes constraints
     */
    @Autowired
    private DefaultSignedAttributesService defaultSignedAttributesService;

    /**
     * Provides default values for empty/non-editable constraints
     * related with signature unsigned attributes
     */
    @Autowired
    private DefaultUnsignedAttributesService defaultUnsignedAttributesService;

    /**
     * Applies the default rules for non-editable/empty fields
     * related with SignatureConstraints objects
     * @param signatureConstraints The signature constraints object
     * @return The signature constraints with the default parameters
     */
    public SignatureConstraints setDefaultRules(SignatureConstraints signatureConstraints) throws XMLStreamException, JAXBException, IOException, SAXException {
        if(signatureConstraints == null){
            signatureConstraints = new SignatureConstraints();
        }
        setDefaultStructuralValidation(signatureConstraints);
        setDefaultAcceptablePolicies(signatureConstraints);
        setDefaultPolicyAvailable(signatureConstraints);
        setDefaultPolicyHashMatch(signatureConstraints);
        setDefaultAcceptableFormats(signatureConstraints);
        setDefaultFullScope(signatureConstraints);

        signatureConstraints.setBasicSignatureConstraints(
                defaultBasicSignatureConstraintsService.setDefaultRules(signatureConstraints.getBasicSignatureConstraints())
        );
        signatureConstraints.setSignedAttributes(
                defaultSignedAttributesService.setDefaultRules(signatureConstraints.getSignedAttributes())
        );
        signatureConstraints.setUnsignedAttributes(
                defaultUnsignedAttributesService.setDefaultRules(signatureConstraints.getUnsignedAttributes())
        );

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

    /**
     * Sets the default value to policy acceptable signature formats constraint
     * @param signatureConstraints The signature constraints
     */
    private void setDefaultAcceptableFormats(SignatureConstraints signatureConstraints){
        MultiValuesConstraint acceptableFormats = signatureConstraints.getAcceptableFormats();
        if(acceptableFormats == null){
            acceptableFormats = new MultiValuesConstraint();
            acceptableFormats.getId().addAll(DEFAULT_ACCEPTABLE_FORMATS);
            acceptableFormats.setLevel(DEFAULT_ACCEPTABLE_FORMATS_LEVEL);
            signatureConstraints.setAcceptableFormats(acceptableFormats);
        }
    }

    /**
     * Sets the default value to policy acceptable full scope constraint
     * @param signatureConstraints The signature constraints
     */
    private void setDefaultFullScope(SignatureConstraints signatureConstraints){
        LevelConstraint fullScope = signatureConstraints.getFullScope();
        if(fullScope == null){
            fullScope = new LevelConstraint();
            fullScope.setLevel(DEFAULT_FULL_SCOPE);
            signatureConstraints.setFullScope(fullScope);
        }
    }

    // TODO - implementar defaults para SignedAttributes e UnsignedAttributes
}
