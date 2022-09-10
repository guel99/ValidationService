package com.devisefutures.policyservice.bsl.mappers;

import com.devisefutures.policyservice.bsl.protocols.requestelems.TimeConstraintDTO;
import eu.europa.esig.dss.policy.jaxb.TimeConstraint;
import eu.europa.esig.dss.policy.jaxb.TimeUnit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = TimeUnit.class, uses = LevelConstraintMapper.class)
public interface TimeConstraintMapper {

    @Mapping(target = "unit", expression = "java(TimeUnit.fromValue(timeConstraintDTO.getTimeUnit()))")
    TimeConstraint toTimeConstraint(TimeConstraintDTO timeConstraintDTO);
}
