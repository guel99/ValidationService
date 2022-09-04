package com.devisefutures.signaturevalidator.bsl.protocols.requestelems.optinp;

import com.devisefutures.signaturevalidator.bsl.protocols.requestelems.indocs.DocumentType;
import lombok.Data;
import lombok.NonNull;

/**
 * The TransformedDocument component contains a document corresponding to the specified
 * <ds:Reference>, after all the transforms in the reference have been applied.
 */
@Data
public class TransformedDocumentType {

    @NonNull
    private DocumentType doc;

    @NonNull
    private int whichRef;
}
