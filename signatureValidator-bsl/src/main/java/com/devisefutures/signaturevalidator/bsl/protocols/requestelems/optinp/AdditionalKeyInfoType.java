package com.devisefutures.signaturevalidator.bsl.protocols.requestelems.optinp;

import lombok.Data;

import java.util.List;

/**
 * The AdditionalKeyInfo component provides the server with additional data (such as certificates and
 * CRLs) which it can use to validate the signature
 */
@Data
public class AdditionalKeyInfoType {

    public static final int X509_DIGEST = 1000;
    public static final int X509_SUBJECT_NAME = 1001;
    public static final int X509_SKI = 1002;
    public static final int X509_CERTIFICATE = 1003;
    public static final int KEY_NAME = 1004;
    public static final int X509_CRL = 1005;


    private X509DigestType x509Digest;

    /**
     * The optional X509SubjectName element MUST
     * contain one instance of a string
     */
    private String sub;

    /**
     * The optional X509SKI element MUST contain one
     * instance of base64 encoded binary
     * data
     */
    private String ski;

    /**
     * The optional X509Certificate element MUST
     * contain one instance of base64 encoded
     * binary data
     */
    private String cert;

    /**
     * The optional KeyName element MUST contain one instance of a string
     */
    private String name;

    /**
     * The optional X509CRL element MUST contain one instance of base64 encoded binary
     * data
     */
    private List<Integer> crl;
}
