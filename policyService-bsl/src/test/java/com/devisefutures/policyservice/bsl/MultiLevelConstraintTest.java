package com.devisefutures.policyservice.bsl;

import com.devisefutures.policyservice.bsl.mappers.MultiValuesConstraintMapperImpl;
import com.devisefutures.policyservice.bsl.protocols.requestelems.MultiValuesConstraintDTO;
import eu.europa.esig.dss.policy.jaxb.MultiValuesConstraint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;

class MultiLevelConstraintTest {

    @InjectMocks
    private MultiValuesConstraintMapperImpl multiValuesConstraintMapper;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test(){
        MultiValuesConstraintDTO multiValuesConstraintDTO = new MultiValuesConstraintDTO();
        multiValuesConstraintDTO.setAcceptedValues(List.of("dasdawd","dsdawafrg"));
        multiValuesConstraintDTO.setLevel("WARN");
        MultiValuesConstraint multiValuesConstraint = multiValuesConstraintMapper.toMultiValuesConstraint(multiValuesConstraintDTO);
        Assertions.assertEquals("WARN", multiValuesConstraint.getLevel().value());
        Assertions.assertTrue(multiValuesConstraint.getId().contains("dasdawd") && multiValuesConstraint.getId().contains("dsdawafrg"));
    }
}
