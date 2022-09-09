package com.devisefutures.policyservice.bsl.protocols.requestelems;

import com.devisefutures.signaturevalidator.common.annotations.EnumValidator;
import eu.europa.esig.dss.policy.jaxb.Model;
import lombok.Data;

/**
 * Boolean check : follows the specified validation model
 */
@Data
public class ModelConstraintDTO {

    /**
     * The validation model
     */
    @EnumValidator(enumClazz = Model.class)
    private String value;
}
