package com.devisefutures.policyservice.bsl.protocols;

import lombok.Data;

@Data
public class ValidationPolicyGetResponse {

    private String policyId;

    private String policyXmlB64;
}
