package com.devisefutures.signaturevalidator.bsl.protocols.requestelems;

import lombok.Data;

@Data
public class DigestInfoType {

    private String alg;

    private String value;
}
