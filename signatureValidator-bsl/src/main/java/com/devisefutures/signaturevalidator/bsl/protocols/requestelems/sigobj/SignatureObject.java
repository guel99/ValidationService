package com.devisefutures.signaturevalidator.bsl.protocols.requestelems.sigobj;

import com.devisefutures.signaturevalidator.bsl.protocols.requestelems.Base64DataType;
import com.devisefutures.signaturevalidator.bsl.protocols.requestelems.optinp.DocumentBaseType;
import lombok.Data;

import java.util.List;

/**
 * Component that contains a signature or timestamp of some sort
 */
@Data
public class SignatureObject {

    /**
     * A base64 encoding of some arbitrary signature,
     * such as an XML signature [XMLDSIG], PGP [RFC 2440] or CMS [RFC 5652] signature or a
     * RFC 3161 timestamp [RFC 3161]. The type of signature is specified by the MimeType element
     * of the Base64DataType component
     */
    private Base64DataType b64Sig;

    /**
     * This element is used to point to an XML signature in
     * an input (for a verify request) or output (for a sign response) document in which a signature is
     * enveloped.
     */
    private SignaturePtrType sigPtr;

    /**
     * The identified schemas are to be used to process the Id attributes
     * during parsing and for XPath evaluation
     */
    private List<DocumentBaseType> schemaRefs;

    public SignatureObject(Base64DataType b64Sig) {
        this.b64Sig = b64Sig;
    }

    public SignatureObject(SignaturePtrType sigPtr) {
        this.sigPtr = sigPtr;
    }

    public SignatureObject(List<DocumentBaseType> schemaRefs) {
        this.schemaRefs = schemaRefs;
    }
}
