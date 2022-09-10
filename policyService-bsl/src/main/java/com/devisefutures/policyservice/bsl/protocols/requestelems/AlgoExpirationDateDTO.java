package com.devisefutures.policyservice.bsl.protocols.requestelems;

import eu.europa.esig.dss.policy.jaxb.Algo;
import lombok.Data;

import java.util.List;

@Data
public class AlgoExpirationDateDTO {

    private List<Algo> algos;

    private String format;
}
