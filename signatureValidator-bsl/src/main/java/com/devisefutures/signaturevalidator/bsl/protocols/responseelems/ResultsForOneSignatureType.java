package com.devisefutures.signaturevalidator.bsl.protocols.responseelems;

import com.devisefutures.signaturevalidator.bsl.protocols.requestelems.optinp.NameIDType;
import com.devisefutures.signaturevalidator.bsl.protocols.requestelems.optinp.SigsRefType;
import com.devisefutures.signaturevalidator.bsl.protocols.requestelems.optinp.TransformedDocumentType;
import lombok.Data;
import lombok.NonNull;

/**
 * Shall include elements providing details on the validation of one
 * signature
 */
@Data
public class ResultsForOneSignatureType {

    @NonNull
    private Result result;

    private SigsRefType sigRef;

    private NameIDType signerIdentity;

    private SigningTimeInfoType signingTimeInfo;

    private ValReportContainerType validationReport;

    private VerifyManifestResultsType manifestValResults;

    private TransformedDocumentType transformed;
}
