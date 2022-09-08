package com.devisefutures.signaturevalidator.bsl.protocols.responseelems;

import com.devisefutures.signaturevalidator.common.annotations.EnumValidator;
import lombok.Data;
import lombok.NonNull;

@Data
public class AdditionalTimeInfoType {

    enum Type {
        SIGNATURE_TIMESTAMP,
        SIGNATURE_TIMEMARK,
        SIGNED_OBJECT_TIMESTAMP,
        CLAIMED_SIGNING_TIME
    }

    /**
     * MUST contain one instance of a date/time value
     */
    private long value;

    /**
     * The Type attribute qualifies the kind of time
     * information included in the response
     */
    @NonNull
    @EnumValidator(enumClazz = Type.class)
    private String type;

    /**
     * It allows to establish references to the source of the time information and SHOULD be used when there
     * is a need to disambiguate several AdditionalTimeInfo components with the same Type attribute
     */
    private String ref;
}
