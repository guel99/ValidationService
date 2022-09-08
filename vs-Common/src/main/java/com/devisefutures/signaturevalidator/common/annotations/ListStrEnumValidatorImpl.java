package com.devisefutures.signaturevalidator.common.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ListStrEnumValidatorImpl implements ConstraintValidator<ListStrEnumValidator, List<String>> {

    private List<String> valueList = null;

    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext context) {
        boolean valid = true;
        for(String str : value){
            valid = valueList.contains(str.toUpperCase().replaceAll("[_-]","")); // for ASiC-S e ASiC-E
        }
        return valid;
    }

    @Override
    public void initialize(ListStrEnumValidator constraintAnnotation) {
        valueList = new ArrayList<>();
        Class<? extends Enum<?>> enumClass = constraintAnnotation.enumClazz();

        @SuppressWarnings("rawtypes")
        Enum[] enumValArr = enumClass.getEnumConstants();

        for (@SuppressWarnings("rawtypes") Enum enumVal : enumValArr) {
            String changedName = enumVal.toString().replace("_", "");
            valueList.add(changedName.toUpperCase());
        }
    }
}
