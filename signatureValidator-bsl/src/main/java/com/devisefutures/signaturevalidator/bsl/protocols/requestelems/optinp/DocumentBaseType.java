package com.devisefutures.signaturevalidator.bsl.protocols.requestelems.optinp;

import com.devisefutures.signaturevalidator.bsl.protocols.requestelems.indocs.DocumentType;
import lombok.Data;

import java.util.List;

@Data
public class DocumentBaseType {

    /**
     * This identifier gives the input document a unique label within a particular request message.
     * Through this identifier, an optional input can refer to a single input document. Using this
     * identifier and the IdRef element it is possible to avoid redundant content
     */
    private String id;

    /**
     * This
     * specifies the value for a <ds:Reference> element’s URI attribute when referring to this input
     * document. The RefURI element SHOULD be specified
     */
    private String refURI;

    /**
     * This
     * specifies the value for a <ds:Reference> element’s Type attribute when referring to this input
     * document
     */
    private String refType;

    /**
     * The identified schemas are to be used to process the Id attributes
     * during parsing and for XPath evaluation.
     */
    private List<DocumentType> schemaRefs;
}
