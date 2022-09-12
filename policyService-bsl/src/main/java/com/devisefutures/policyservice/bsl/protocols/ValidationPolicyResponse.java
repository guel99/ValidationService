package com.devisefutures.policyservice.bsl.protocols;

import lombok.Data;

/**
 * The response sent to the user in case of success
 */
@Data
public class ValidationPolicyResponse {

    /**
     * The id of generated policy file
     */
    private String policyId;

    /**
     * The timestamp associated with the created policy file
     */
    private String timestamp;
}
