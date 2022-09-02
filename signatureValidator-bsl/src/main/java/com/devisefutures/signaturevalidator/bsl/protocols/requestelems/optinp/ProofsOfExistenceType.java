package com.devisefutures.signaturevalidator.bsl.protocols.requestelems.optinp;

import lombok.Data;

import java.util.List;

/**
 * This component shall have one or more tuples. Each tuple shall contain two components, namely:
 * 1) One component whose value is a time instant value. This time instant shall be considered by the server as a
 * proof of existence of the signature referenced in the other component of the tuple.
 * 2) One component referencing one signature.
 */
@Data
public class ProofsOfExistenceType {

    private List<ProofOfExistenceType> proofsOfExistence;
}
