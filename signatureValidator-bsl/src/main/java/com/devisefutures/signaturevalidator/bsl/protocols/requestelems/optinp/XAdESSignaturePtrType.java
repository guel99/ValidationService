package com.devisefutures.signaturevalidator.bsl.protocols.requestelems.optinp;

import com.devisefutures.signaturevalidator.bsl.protocols.requestelems.NsPrefixMappingType;
import lombok.Data;
import lombok.NonNull;

import java.util.List;


/**
 * The SignaturePtr component is used to point to a
 * signature in an input (for a verify request) or output
 * (for a sign response) document in which a signature is enveloped
 */
@Data
public class XAdESSignaturePtrType {

    /**
     * Identifies the signature element being pointed at within the selected document.
     * The XPath expression is evaluated from the root node (see section 5.1 of [XPATH]) of the document
     * identified by WhichDocument
     */
    private String xPath;

    /**
     * The NsPrefixMapping component defines the mapping of namespace URIs to namespace prefixes.
     * This is required to evaluate XPath expression when using transport syntaxes that don’t support
     * namespace
     */
    private List<NsPrefixMappingType> nsDecl;

    /**
     * MUST contain one instance of a unique identifier
     * reference. This element identifies the input document being pointed at
     */
    @NonNull
    private DocumentBaseType whichDoc;
}
