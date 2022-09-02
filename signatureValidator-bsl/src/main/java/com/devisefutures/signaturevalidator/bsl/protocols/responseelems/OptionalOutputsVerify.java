package com.devisefutures.signaturevalidator.bsl.protocols.responseelems;

import com.devisefutures.signaturevalidator.bsl.protocols.requestelems.optinp.TransformedDocumentType;
import com.devisefutures.signaturevalidator.bsl.protocols.requestelems.optinp.NameIDType;
import lombok.Data;

import java.util.List;

/**
 * The OptionalOutputsVerify component defines a set of additional outputs associated with the
 * processing of a verification request
 */
@Data
public class OptionalOutputsVerify {

    /**
     * MAY occur zero or more times containing a URI. This
     * element lists the set of DSS policies used by the server
     */
    private List<String> policy;

    /**
     * The TransformedDocument component contains a document corresponding to the specified
     * <ds:Reference>, after all the transforms in the reference have been applied
     */
    private TransformedDocumentType transformed;

    /**
     * The results of verifying individual <ds:Reference>'s within a <ds:Manifest> are returned in the
     * VerifyManifestResults component
     */
    private VerifyManifestResultsType result;

    /**
     * The SigningTimeInfo element returns the signature’s creation date and
     * time. When there is no way for the server to determine the signing time, the server MUST omit
     * this element
     */
    private SigningTimeInfoType signingTimeInfo;

    /**
     * In addition to the verification time, the server MAY include in the
     * VerificationTimeInfo element any other relevant time instants that may have been used when
     * determining the verification time or that may be useful for its qualification.
     */
    private VerificationTimeInfoType verificationTimeInfo;

    /**
     * The SignerIdentity element contains an indication of who
     * performed the signature
     */
    private NameIDType signerIdentity;


    private ValReportContainerType validationReport;

    private List<ResultsForOneSignatureType> resForEachSignature;

    private String appliedSigValPolicy;

    private List<String> availableSigValPols;

    // private AnyType other;
}
