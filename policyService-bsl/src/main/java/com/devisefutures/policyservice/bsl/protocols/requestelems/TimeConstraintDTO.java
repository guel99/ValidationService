package com.devisefutures.policyservice.bsl.protocols.requestelems;

import com.devisefutures.signaturevalidator.common.annotations.EnumValidator;
import eu.europa.esig.dss.policy.jaxb.Level;
import eu.europa.esig.dss.policy.jaxb.TimeUnit;
import lombok.Data;

@Data
public class TimeConstraintDTO {

    @EnumValidator(enumClazz = TimeUnit.class)
    private String timeUnit;

    private int value;

    @EnumValidator(enumClazz = Level.class)
    private String level;
}
