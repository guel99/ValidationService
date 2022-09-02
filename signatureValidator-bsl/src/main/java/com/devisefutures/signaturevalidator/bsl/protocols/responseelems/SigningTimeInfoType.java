package com.devisefutures.signaturevalidator.bsl.protocols.responseelems;

import lombok.Data;
import lombok.NonNull;

/**
 * Allows the client to obtain the time instant associated to the
 * signature creation
 */
@Data
public class SigningTimeInfoType {

    /**
     * This element returns the time value considered
     * by the server to be the signature creation
     * time
     */
    @NonNull
    private long signingTime;

    /**
     * This element returns the trusted time values
     * considered as lower and upper limits for
     * the signing time
     */
    private SigningTimeBoundaries signingTimeBounds;
}
