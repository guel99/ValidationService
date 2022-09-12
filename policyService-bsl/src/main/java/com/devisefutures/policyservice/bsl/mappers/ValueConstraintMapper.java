package com.devisefutures.policyservice.bsl.mappers;

import com.devisefutures.policyservice.bsl.protocols.requestelems.ValueConstraintDTO;
import eu.europa.esig.dss.policy.jaxb.Level;
import eu.europa.esig.dss.policy.jaxb.ValueConstraint;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = Level.class)
public interface ValueConstraintMapper {

    @Mapping(target = "level", expression = "java(Level.fromValue(valueConstraintDTO.getLevel()))")
    ValueConstraint toValueConstraint(ValueConstraintDTO valueConstraintDTO);
}
