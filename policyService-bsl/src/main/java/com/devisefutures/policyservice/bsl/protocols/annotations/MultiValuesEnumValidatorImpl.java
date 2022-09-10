package com.devisefutures.policyservice.bsl.protocols.annotations;

import com.devisefutures.policyservice.bsl.protocols.requestelems.MultiValuesConstraintDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class MultiValuesEnumValidatorImpl implements ConstraintValidator<MultiValuesEnumValidator, MultiValuesConstraintDTO> {

    private List<String> acceptableValues = new ArrayList<>();

    @Override
    public boolean isValid(MultiValuesConstraintDTO value, ConstraintValidatorContext context) {
        boolean valid = true;
        for(String elem : value.getAcceptedValues()){
            elem = elem.replaceAll("[-_]","");
            valid = acceptableValues.contains(elem);
        }
        return valid;
    }

    @Override
    public void initialize(MultiValuesEnumValidator constraintAnnotation) {
        Class<? extends Enum<?>> enumClass = constraintAnnotation.enumClazz();

        @SuppressWarnings("rawtypes")
        Enum[] enumValArr = enumClass.getEnumConstants();

        for (@SuppressWarnings("rawtypes") Enum enumVal : enumValArr) {
            String changedName = enumVal.toString().replace("_", "");
            acceptableValues.add(changedName.toUpperCase());
        }
    }
}
