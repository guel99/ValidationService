package com.devisefutures.policyservice.bsl.services.defaultrules;

import eu.europa.esig.dss.policy.jaxb.UnsignedAttributesConstraints;
import org.springframework.stereotype.Service;

@Service
public class UnsignedAttributesConstraintsMergeService {

    /**
     * Merges the first specified UnsignedAttributesConstraints object into the second one.
     * If the second one has some values already attributed, they will be changed
     * to the same values specified in the first one
     * @param unsignedAttributesConstraints The source UnsignedAttributesConstraints
     * @param baseUnsignedAttrConstraints The target UnsignedAttributesConstraints where they were merged to
     * @return The unsigned attributes constraints
     */
    public UnsignedAttributesConstraints merge(UnsignedAttributesConstraints unsignedAttributesConstraints, UnsignedAttributesConstraints baseUnsignedAttrConstraints){
        if(unsignedAttributesConstraints == null)
            return baseUnsignedAttrConstraints;

        if(unsignedAttributesConstraints.getCounterSignature() != null) baseUnsignedAttrConstraints.setCounterSignature(unsignedAttributesConstraints.getCounterSignature());

        return baseUnsignedAttrConstraints;
    }
}
