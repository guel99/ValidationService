package com.devisefutures.signaturevalidator.bsl.protocols.responseelems;

import com.devisefutures.signaturevalidator.bsl.protocols.requestelems.Base64DataType;
import lombok.Data;

@Data
public class OtherReport {

    private Base64DataType content;

    /**
     * Shall be an URI identifying the specification where the validation report
     */
    private String specId;

    /**
     * Shall indicate the encoding used for the validation report
     */
    private String encoding;
}
