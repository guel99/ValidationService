package com.devisefutures.signaturevalidator.bsl.protocols.requestelems.optinp;

import lombok.Data;

/**
 * The ReturnTransformedDocument component instructs the server to return an input document to
 * which the XML signature transforms specified by a particular <ds:Reference> have been applied. The
 * <ds:Reference> is indicated by the zero-based WhichReference attribute (0 means the first
 * <ds:Reference> in the signature, 1 means the second, and so on). Multiple occurrences of this
 * optional input can be present in a single verify request message. Each occurrence will generate a
 * corresponding optional output.
 */
@Data
public class ReturnTransformedDocumentType {

    private int whichRef;
}
