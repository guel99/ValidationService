package com.devisefutures.signaturevalidator.bsl.protocols;

import com.devisefutures.signaturevalidator.bsl.protocols.responseelems.OptionalOutputsVerify;
import com.devisefutures.signaturevalidator.bsl.protocols.responseelems.Result;
import lombok.Data;

import java.util.List;

@Data
public class ValidationResponse {

    /**
     * This component shall contain a major result, which shall report whether the server has been able to perform its task,
     * regardless the results obtained. This component may also contain a minor result providing additional information on the
     * task performed by the server.
     */
    private Result result;

    /**
     * MAY occur zero or more times containing a URI. This
     * element lists the set of DSS profile applied by the server. This set MAY include the set of
     * profiles requested by the client. But the server MAY use more comprehensive set of profiles and
     * add additional profiles not requested by the client
     */
    private List<String> profile;

    /**
     * The RequestID element is used to correlate this response with its request
     */
    private String reqId;

    /**
     * The ResponseID element
     */
    private String respId;

    private OptionalOutputsVerify optOutp;
}
