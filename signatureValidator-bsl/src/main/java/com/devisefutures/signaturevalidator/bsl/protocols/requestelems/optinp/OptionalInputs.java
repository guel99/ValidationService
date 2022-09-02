package com.devisefutures.signaturevalidator.bsl.protocols.requestelems.optinp;

import lombok.Data;

import java.util.List;

@Data
public class OptionalInputs {

    private List<String> policy;

    private String lang;

    private ClaimedIdentityType claimedIdentity;

    private SchemasType schemas;

    /**
     * Instructs the server to attempt to determine the
     * signature’s validity at the specified time, instead
     * of a time determined by the server policy
     */
    private UseVerificationTimeType useVerificationTime;

    /**
     * This element cam be used by the client to obtain the time
     * instant used by the server to validate the signature
     */
    private boolean returnVerificationTime = false;

    /**
     * This element provides the server with additional
     * data (such as certificates and CRLs) which it can use
     * to validate the signature
     */
    private List<AdditionalKeyInfoType> addKeyInfo;

    /**
     * This element allows the client to instruct the server to return the time
     * instant associated to the signature creation as a SigningTimeInfo element
     */
    private boolean returnSigningTime = false;

    private boolean returnSigner = false;

    /**
     * The
     * ReturnTransformedDocument element instructs the server to return an input document to which
     * the XML signature transforms specified by a particular <ds:Reference> have been applied. The
     * result of the transformations will be returned as a TransformedDocument element
     */
    private ReturnTransformedDocumentType returnTransformed;

    private boolean verifyManifests = false;

    private SigsRefType processSigs;

    /**
     * The element that shall request the server to validate the signature(s)
     * against a certain signature validation policy shall be
     * the useSigValPol element
     */
    private SigValPolicyType useSigValPol;

    private ReturnValReportType returnValReport;

    private ProofsOfExistenceType proofsOfExist;
}
