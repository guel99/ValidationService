package com.devisefutures.signaturevalidator.bsl.protocols.requestelems.sigobj;

import com.devisefutures.signaturevalidator.bsl.protocols.requestelems.NsPrefixMappingType;
import com.devisefutures.signaturevalidator.bsl.protocols.requestelems.optinp.DocumentBaseType;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class SignaturePtrType {

    /**
     * This
     * element identifies the signature element being pointed at within the selected document. The
     * XPath expression is evaluated from the root node (see section 5.1 of [XPATH]) of the document
     * identified by WhichDocument
     */
    private String xPath;

    /**
     * Defines the mapping of namespace URIs to namespace prefixes.
     * This is required to evaluate XPath expression when using transport syntaxes that don’t support
     * namespace.
     */
    private List<NsPrefixMappingType> nsDecl;

    /**
     * This element identifies the input document being pointed at.
     */
    @NonNull
    private DocumentBaseType whichDoc;
}
