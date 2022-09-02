package com.devisefutures.signaturevalidator.bsl.protocols.responseelems;

import lombok.Data;

/**
 * This element returns the trusted time values
 * considered as lower and upper limits for
 * the signing time
 */
@Data
public class SigningTimeBoundaries {

    /**
     * MUST contain a date/time value
     */
    private long lowerBound;

    /**
     * MUST contain a date/time value
     */
    private long upperBound;
}
