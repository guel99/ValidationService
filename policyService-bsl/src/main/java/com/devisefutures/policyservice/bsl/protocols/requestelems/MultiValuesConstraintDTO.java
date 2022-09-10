package com.devisefutures.policyservice.bsl.protocols.requestelems;

import com.devisefutures.signaturevalidator.common.annotations.EnumValidator;
import eu.europa.esig.dss.policy.jaxb.Level;
import lombok.Data;

import java.util.List;

@Data
public class MultiValuesConstraintDTO {

    @EnumValidator(enumClazz = Level.class)
    private String level;

    private List<String> acceptedValues;
}
