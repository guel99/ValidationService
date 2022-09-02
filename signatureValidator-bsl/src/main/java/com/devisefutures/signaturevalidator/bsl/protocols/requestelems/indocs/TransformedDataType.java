package com.devisefutures.signaturevalidator.bsl.protocols.requestelems.indocs;

import com.devisefutures.signaturevalidator.bsl.protocols.requestelems.Base64DataType;
import com.devisefutures.signaturevalidator.bsl.protocols.requestelems.TransformsType;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class TransformedDataType {

    private String id;

    private String refURI;

    private String refType;

    private List<DocumentType> schemaRefs;

    private TransformsType transforms;

    @NonNull
    private Base64DataType b64Data;

    private int whichRef;
}
