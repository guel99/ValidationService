package com.devisefutures.signaturevalidator.bsl.protocols.responseelems;

import lombok.Data;
import lombok.NonNull;

/**
 * This element attaches an element to a human-readable
 * string to specify the string’s language
 */
@Data
public class InternationalStringType {

    /**
     * The human
     * readable string
     */
    private String value;

    /**
     * This element identifies the language of the value element.
     */
    @NonNull
    private String lang;
}
