package com.devisefutures.signaturevalidator.bsl.protocols.requestelems.indocs;

import com.devisefutures.signaturevalidator.bsl.protocols.requestelems.Base64DataType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
public class DocumentType {

    /**
     * This identifier gives the input document a unique label within a particular request message.
     * Through this identifier, an optional input can refer to a single input document. Using this
     * identifier and the IdRef element it is possible to avoid redundant content
     */
    private String id;

    /**
     * This specifies the value for a <ds:Reference> element’s URI attribute when referring to this input
     * document. The RefURI element SHOULD be specified. Not more than one RefURI element
     * may be omitted in a single signing request
     */
    private String refURI;

    /**
     * This specifies the value for a <ds:Reference> element’s Type attribute when referring to this input
     * document
     */
    private String refType;

    /**
     * MUST contain one instance of a unique identifier
     * reference. The identified schemas are to be used to process the Id attributes
     * during parsing and for XPath evaluation
     */
    private List<DocumentType> schemaRefs;

    @NonNull
    private Base64DataType b64Data;
}
