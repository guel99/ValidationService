package com.devisefutures.signaturevalidator.bsl.protocols.requestelems.optinp;

import lombok.Data;

import java.util.List;

/**
 * Element that shall request the server to validate the signature(s)
 * against a certain signature validation policy
 */
@Data
public class SigValPolicyType {

    /**
     * Has the unique identifier of the signature validation policy as
     * an URI (or if OID, the value shall be an URN indication the value
     * of the aforementioned OID as specified in IETS RFC 3061)
     */
    private String sigValPolId;

    /**
     * Shall have as value one location where the signature validation policy document can
     * be accessed, as an URI value
     */
    private List<String> sigValPolLocs;
}
