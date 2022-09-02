package com.devisefutures.signaturevalidator.bsl.protocols.requestelems;

import lombok.Data;
import lombok.NonNull;

@Data
public class NsPrefixMappingType {

    @NonNull
    private String uri;

    private String pre;
}
