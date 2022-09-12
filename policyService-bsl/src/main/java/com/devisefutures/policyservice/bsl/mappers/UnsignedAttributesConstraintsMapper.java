package com.devisefutures.policyservice.bsl.mappers;

import com.devisefutures.policyservice.bsl.protocols.requestelems.UnsignedAttributesConstraintsDTO;
import eu.europa.esig.dss.policy.jaxb.UnsignedAttributesConstraints;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = LevelConstraintMapper.class)
public interface UnsignedAttributesConstraintsMapper {

    UnsignedAttributesConstraints toUnsignedAttributesConstraints(UnsignedAttributesConstraintsDTO unsignedAttributesConstraintsDTO);
}
