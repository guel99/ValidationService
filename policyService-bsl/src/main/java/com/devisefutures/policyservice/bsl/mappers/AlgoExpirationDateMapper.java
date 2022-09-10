package com.devisefutures.policyservice.bsl.mappers;

import com.devisefutures.policyservice.bsl.protocols.requestelems.AlgoExpirationDateDTO;
import eu.europa.esig.dss.policy.jaxb.AlgoExpirationDate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlgoExpirationDateMapper {

    AlgoExpirationDate toAlgoExpirationDate(AlgoExpirationDateDTO algoExpirationDateDTO);
}
