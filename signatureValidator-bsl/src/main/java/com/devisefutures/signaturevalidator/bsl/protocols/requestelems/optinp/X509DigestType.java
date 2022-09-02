package com.devisefutures.signaturevalidator.bsl.protocols.requestelems.optinp;

import lombok.Data;
import lombok.NonNull;

/**
 * The X509Digest component contains a base64-encoded digest of a certificate. The digest algorithm
 * URI is identified with a required Algorithm element
 */
@Data
public class X509DigestType {

    private String value;

    @NonNull
    private String alg;
}
