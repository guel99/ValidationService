package com.devisefutures.policyservice.bsl.mappers;

import eu.europa.esig.dss.policy.jaxb.Level;
import eu.europa.esig.dss.policy.jaxb.LevelConstraint;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, imports = Level.class)
public interface LevelConstraintMapper {

    LevelConstraintMapper INSTANCE = Mappers.getMapper(LevelConstraintMapper.class);

    @Mapping(target = "level", expression = "java(Level.fromValue(level))")
    LevelConstraint toLevelConstraint(String level);
}
