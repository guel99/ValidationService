package com.devisefutures.policyservice.bsl;

import com.devisefutures.policyservice.bsl.mappers.LevelConstraintMapper;
import com.devisefutures.policyservice.bsl.mappers.LevelConstraintMapperImpl;
import com.devisefutures.policyservice.bsl.mappers.MultiValuesConstraintMapper;
import com.devisefutures.policyservice.bsl.services.PolicyAssemblerService;
import eu.europa.esig.dss.policy.jaxb.LevelConstraint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

class LevelConstraintTest {

    @InjectMocks
    private LevelConstraintMapperImpl levelConstraintMapper;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test(){
        String level = "WARN";
        LevelConstraint levelConstraint = levelConstraintMapper.toLevelConstraint(level);
        Assertions.assertEquals(level, levelConstraint.getLevel().value());
    }
}
