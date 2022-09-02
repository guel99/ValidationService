package com.devisefutures.signaturevalidator.bsl.protocols.requestelems.indocs;

import com.devisefutures.signaturevalidator.bsl.protocols.requestelems.TransformsType;
import com.devisefutures.signaturevalidator.bsl.protocols.requestelems.DigestInfoType;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class DocumentHashType {

    private String id;

    private String refURI;

    private String refType;

    private List<DocumentType> schemaRefs;

    /**
     * This specifies transforms that the client has
     * already applied to the input document before hashing it
     */
    private TransformsType transforms;

    /**
     * This element MAY contain more than one DigestInfo
     * sub-component to represent the digest values calculated with different digest algorithms. This
     * may be useful when a requestor doesn’t know upfront which digest algorithms are supported /
     * accepted by the server for signing
     */
    @NonNull
    private List<DigestInfoType> di;

    /**
     * This
     * element hence offers a way to clearly identify the <ds:Reference> when URI and RefType
     * match multiple components. The corresponding <ds:Reference> is indicated by this zero-based
     * WhichReference element (0 means the first <ds:Reference> in the signature, 1 means the
     * second, and so on)
     */
    private int whichRef;

    private String pAdESSFieldName;
}
