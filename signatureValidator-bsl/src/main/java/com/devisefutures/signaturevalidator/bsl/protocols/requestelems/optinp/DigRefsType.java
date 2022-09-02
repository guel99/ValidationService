package com.devisefutures.signaturevalidator.bsl.protocols.requestelems.optinp;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class DigRefsType {

    /**
     * Shall be the base-64 encoded value of the digest of the referenced digital
     * signature computed using the digest algorithm identified in DigestMethod
     */
    @NonNull
    private List<String> digVals;

    /**
     * Shall be an URI identifying a digest algorithm.
     */
    @NonNull
    private String digAlg;

    /**
     * Shall be an URI identifying a canonicalization algorithm, in case of XAdES
     */
    private String canAlg;
}
