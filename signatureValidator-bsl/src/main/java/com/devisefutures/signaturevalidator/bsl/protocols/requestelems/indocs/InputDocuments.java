package com.devisefutures.signaturevalidator.bsl.protocols.requestelems.indocs;
import lombok.Data;

import java.util.List;

@Data
public class InputDocuments {

    private List<DocumentType> doc;

    private List<TransformedDataType> transformed;

    private List<DocumentHashType> docHash;

    public int getNumDoc(){
        return doc == null ? 0 : doc.size();
    }

    public int getNumTransformed(){
        return transformed == null ? 0 : transformed.size();
    }

    public int getNumDocHash(){
        return docHash == null ? 0 : transformed.size();
    }
}
