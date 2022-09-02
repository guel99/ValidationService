package com.devisefutures.signaturevalidator.bsl.protocols.requestelems.optinp;

import lombok.Data;
import lombok.NonNull;

/**
 * This element indicates the identity of the client who is making a request
 */
@Data
public class ClaimedIdentityType {

    @NonNull
    private NameIDType name;

    private Object suppInfo;
}
