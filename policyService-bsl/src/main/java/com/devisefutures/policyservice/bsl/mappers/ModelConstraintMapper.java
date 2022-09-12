package com.devisefutures.policyservice.bsl.mappers;

import com.devisefutures.policyservice.bsl.protocols.requestelems.ModelConstraintDTO;
import eu.europa.esig.dss.policy.jaxb.Model;
import eu.europa.esig.dss.policy.jaxb.ModelConstraint;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = Model.class)
public interface ModelConstraintMapper {

    @Mapping(target = "value", expression = "java(Model.fromValue(modelConstraintDTO.getValue()))")
    ModelConstraint toModelConstraint(ModelConstraintDTO modelConstraintDTO);
}
