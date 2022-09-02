package com.devisefutures.signaturevalidator.bsl.protocols.requestelems.optinp;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

/**
 * Request message's mechanism for allowing the client to
 * enumerate to the server the signatures that the client requests to process
 */
@Data
public class SigsRefType {

    /**
     * Shall be the base-64 encoded value of the digest of the referenced digital
     * signature computed using the digest algorithm identified in DigestMethod
     */
    @NonNull
    private DigRefsType digRefs;

    /**
     * The value of each item within the pAdESFieldNames array shall
     * be the name of the PDF field where the referenced
     * PAdES signature is present within the PDF signed document
     */
    private List<String> padesFieldNames;

    private List<XAdESSignaturePtrType> xadesSigPtrs;
}
