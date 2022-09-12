package com.devisefutures.policyservice.bsl.mappers;

import com.devisefutures.policyservice.bsl.protocols.requestelems.SignedAttributesConstraintsDTO;
import eu.europa.esig.dss.policy.jaxb.SignedAttributesConstraints;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        LevelConstraintMapper.class,
        ValueConstraintMapper.class,
        MultiValuesConstraintMapper.class
})
public interface SignedAttributesConstraintsMapper {

    SignedAttributesConstraints toSignedAttributesConstraints(SignedAttributesConstraintsDTO signedAttributesConstraintsDTO);
}
