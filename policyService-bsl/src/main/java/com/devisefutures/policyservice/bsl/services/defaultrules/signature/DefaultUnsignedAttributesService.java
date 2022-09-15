package com.devisefutures.policyservice.bsl.services.defaultrules.signature;

import eu.europa.esig.dss.policy.jaxb.Level;
import eu.europa.esig.dss.policy.jaxb.LevelConstraint;
import eu.europa.esig.dss.policy.jaxb.UnsignedAttributesConstraints;
import org.springframework.stereotype.Service;

@Service
public class DefaultUnsignedAttributesService {

    /**
     * Default value for counter signature constraint
     */
    private static final Level DEFAULT_COUNTER_SIGNATURE = Level.IGNORE;

    /**
     * Sets the default values to empty/non-editable fields related
     * with signature unsigned attributes constraints
     * @param unsignedAttributesConstraints The unsigned attributes constraints
     * @return The modified unsigned attributes constraints with the
     * empty/non-editable fields assigned to its defaults values
     */
    public UnsignedAttributesConstraints setDefaultRules(UnsignedAttributesConstraints unsignedAttributesConstraints){
        if(unsignedAttributesConstraints == null)
            unsignedAttributesConstraints = new UnsignedAttributesConstraints();
        setDefaultCounterSignature(unsignedAttributesConstraints);

        return unsignedAttributesConstraints;
    }

    /**
     * Sets the default value to the counter signature constraint
     * @param unsignedAttributesConstraints The unsigned attributes constraints
     */
    public void setDefaultCounterSignature(UnsignedAttributesConstraints unsignedAttributesConstraints){
        LevelConstraint counterSignature = unsignedAttributesConstraints.getCounterSignature();
        if(counterSignature == null){
            counterSignature = new LevelConstraint();
            counterSignature.setLevel(DEFAULT_COUNTER_SIGNATURE);
            unsignedAttributesConstraints.setCounterSignature(counterSignature);
        }
    }
}
