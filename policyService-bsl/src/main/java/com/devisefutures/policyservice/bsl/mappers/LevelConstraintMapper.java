package com.devisefutures.policyservice.bsl.mappers;

import eu.europa.esig.dss.policy.jaxb.LevelConstraint;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface LevelConstraintMapper {

    LevelConstraintMapper INSTANCE = Mappers.getMapper(LevelConstraintMapper.class);

    @Mapping(source = "level", target = "level.value")
    LevelConstraint toLevelConstraint(String level);
}
