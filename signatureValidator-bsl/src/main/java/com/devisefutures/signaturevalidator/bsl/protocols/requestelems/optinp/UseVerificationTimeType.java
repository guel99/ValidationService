package com.devisefutures.signaturevalidator.bsl.protocols.requestelems.optinp;

import lombok.Data;
import lombok.Getter;

/**
 * This UseVerificationTime component instructs the server to attempt to determine the signature’s
 * validity at the specified time, instead of a time determined by the server policy.
 */
@Data
@Getter
public class UseVerificationTimeType {

    /**
     * The optional CurrentTime element MUST contain one instance of a
     * boolean. Its default value is 'false'. This element instructs the server to use its current time
     * (normally the time associated with the server-side request processing)
     */
    private boolean currTime = false;

    /**
     * ]The optional SpecificTime element MUST contain one instance of a
     * date/time value. The SpecificTime element allows the client to manage manually the time
     * instant used in the verification process. It SHOULD be expressed as UTC time
     */
    private long specTime;

    /**
     * The optional Base64Content element MUST contain base64 encoded
     * binary data. The Base64Content element allows the provision of additional date/time data
     */
    private String b64Content;

    public UseVerificationTimeType(boolean currTime){
        this.currTime = currTime;
    }

    public UseVerificationTimeType(long specTime){
        this.specTime = specTime;
    }

    public UseVerificationTimeType(String b64Content){
        this.b64Content = b64Content;
    }
}
