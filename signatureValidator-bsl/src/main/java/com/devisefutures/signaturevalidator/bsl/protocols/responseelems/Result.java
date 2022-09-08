package com.devisefutures.signaturevalidator.bsl.protocols.responseelems;

import com.devisefutures.signaturevalidator.common.annotations.EnumValidator;
import lombok.Data;
import lombok.NonNull;

/**
 * The Result element is returned with every response message
 */
@Data
public class Result {

    /**
     * The ResultMajor element describes the most significant component of the result code. The
     * set values MAY be extended
     */
    @NonNull
    @EnumValidator(enumClazz = ResultMajor.class)
    private String maj;

    /**
     * The optional ResultMinor element MUST contain a URI
     */
    private String min;

    /**
     * It represents a message which MAY be returned to an operator, logged
     * by the client, used for debugging, etc
     */
    private InternationalStringType msg;

    /**
     * In the
     * case of processing problems, the server MAY give a reference to processing details (e.g. for
     * debugging purposes) but does not want to disclose sensitive information, this element can be
     * used. It MAY contain a random string that links the current request to internal logs, processing
     * protocols or crash dumps
     */
    private String pRef;
}
