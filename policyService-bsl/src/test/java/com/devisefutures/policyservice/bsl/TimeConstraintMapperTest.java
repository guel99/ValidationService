package com.devisefutures.policyservice.bsl;

import com.devisefutures.policyservice.bsl.mappers.TimeConstraintMapperImpl;
import com.devisefutures.policyservice.bsl.protocols.requestelems.TimeConstraintDTO;
import eu.europa.esig.dss.policy.jaxb.TimeConstraint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class TimeConstraintMapperTest{

    @InjectMocks
    private TimeConstraintMapperImpl timeConstraintMapper;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test(){
        TimeConstraintDTO timeConstraintDTO = new TimeConstraintDTO();
        timeConstraintDTO.setTimeUnit("DAYS");
        timeConstraintDTO.setValue(2);
        timeConstraintDTO.setLevel("WARN");
        TimeConstraint timeConstraint = timeConstraintMapper.toTimeConstraint(timeConstraintDTO);
        Assertions.assertEquals("DAYS", timeConstraint.getUnit().value());
        Assertions.assertEquals(2, timeConstraint.getValue());
        Assertions.assertEquals("WARN", timeConstraint.getLevel().value());
    }
}
