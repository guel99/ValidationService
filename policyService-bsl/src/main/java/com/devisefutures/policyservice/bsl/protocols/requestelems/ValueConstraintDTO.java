package com.devisefutures.policyservice.bsl.protocols.requestelems;

import com.devisefutures.signaturevalidator.common.annotations.EnumValidator;
import eu.europa.esig.dss.policy.jaxb.Level;
import lombok.Data;

/**
 * Value check : follows the specified level behavior if the
 * checked element is not equals to the specified value
 */
@Data
public class ValueConstraintDTO {

    @EnumValidator(enumClazz = Level.class)
    private String level;

    private String value;
}
