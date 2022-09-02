package com.devisefutures.signaturevalidator.bsl.protocols;

import com.devisefutures.signaturevalidator.bsl.protocols.requestelems.indocs.InputDocuments;
import com.devisefutures.signaturevalidator.bsl.protocols.requestelems.optinp.OptionalInputs;
import com.devisefutures.signaturevalidator.bsl.protocols.requestelems.sigobj.SignatureObject;
import lombok.Data;

import java.util.List;

@Data
public class ValidationRequest {

    /**
     * MAY occur zero or more times containing a URI. This
     * element lists the set of DSS profile applied by the server. This set MAY include the set of
     * profiles requested by the client. But the server MAY use more comprehensive set of profiles and
     * add additional profiles not requested by the client
     */
    private List<String> profile;

    /**
     * The RequestID element is used to correlate
     * this response with its request.
     */
    private String reqID;

    /**
     * Stores the signed documents, or its
     * representation, to validate its signatures
     */
    private InputDocuments inDocs;

    private OptionalInputs optInp;

    /**
     * The SignatureObject element contains a signature or timestamp, or else
     * contains a <SignaturePtr> that points to an XML signature in one
     * of the input documents
     */
    private SignatureObject sigObj;
}
