package com.devisefutures.policyservice.bsl.protocols;

import lombok.Data;
import lombok.NonNull;

/**
 * Encapsules the data sent to the user when he sends a search request
 */
@Data
public class RemotePolicyDTO {

    /**
     * The id of on of the policies that
     * matches the request token
     */
    @NonNull
    private String id;

    /**
     * The url of the server that emitted the response
     */
    @NonNull
    private String source;
}
