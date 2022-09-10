package com.devisefutures.policyservice.bsl.mappers;

import com.devisefutures.policyservice.bsl.protocols.requestelems.MultiValuesConstraintDTO;
import eu.europa.esig.dss.policy.jaxb.MultiValuesConstraint;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = LevelConstraintMapper.class)
public interface MultiValuesConstraintMapper {

    @Mapping(source = "acceptedValues", target="id")
    MultiValuesConstraint toMultiValuesConstraint(MultiValuesConstraintDTO multiValuesConstraintDTO);
}
