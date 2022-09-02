package com.devisefutures.signaturevalidator.bsl.protocols.requestelems;

import lombok.Data;

@Data
public class Base64DataType {

    /**
     * This identifier gives the binary data a unique label within a particular message. Using this
     * identifier and the IdRef element it is possible to avoid redundant content
     */
    private String id;

    /**
     * The base64 data
     */
    private String val;

    /**
     * Specifies the media type of the val content
     */
    private String mimeType;

    /*
     * Reference to the data
     */
    //private AttachmentRefType attRef;

    //private String idRef;
}
