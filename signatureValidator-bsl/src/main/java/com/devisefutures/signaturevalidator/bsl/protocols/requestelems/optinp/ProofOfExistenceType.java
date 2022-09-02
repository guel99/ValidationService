package com.devisefutures.signaturevalidator.bsl.protocols.requestelems.optinp;

import lombok.Data;

@Data
public class ProofOfExistenceType {

    /**
     * Component whose value is a time instant value. This time instant shall be considered by the server as a
     * proof of existence of the signature referenced in the other component of the tuple
     */
    private String time;

    /**
     * One component referencing ONE signature
     */
    private SigsRefType sigRef;
}
