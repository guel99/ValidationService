package com.devisefutures.signaturevalidator.bsl.protocols.requestelems.optinp;

import com.devisefutures.signaturevalidator.bsl.protocols.requestelems.indocs.DocumentType;
import lombok.Data;

/**
 * The DocumentWithSignature component contains document with the signature inserted as requested
 * with the SignaturePlacement component
 */
@Data
public class DocumentWithSignatureType {

    private DocumentType doc;
}
