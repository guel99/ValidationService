package com.devisefutures.signaturevalidator.bsl.protocols.requestelems;

import lombok.Data;
import lombok.NonNull;

@Data
public class DigestInfoType {

    @NonNull
    private String alg;

    @NonNull
    private String value;
}
