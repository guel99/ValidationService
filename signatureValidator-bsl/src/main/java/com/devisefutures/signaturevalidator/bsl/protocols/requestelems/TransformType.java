package com.devisefutures.signaturevalidator.bsl.protocols.requestelems;

import lombok.Data;

import java.util.List;

@Data
public class TransformType {

    private List<String> xPath;

    private String val;

    private String b64Content;

    private List<NsPrefixMappingType> nsDecl;

    private String alg;
}
