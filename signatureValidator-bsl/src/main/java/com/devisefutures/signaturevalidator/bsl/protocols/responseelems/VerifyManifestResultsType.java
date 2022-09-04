package com.devisefutures.signaturevalidator.bsl.protocols.responseelems;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

/**
 * The results of verifying individual <ds:Reference>'s
 * within a <ds:Manifest> are returned in the
 * VerifyManifestResults component
 */
@Data
public class VerifyManifestResultsType {

    @NonNull
    private List<ManifestResultType> signedRef;
}
