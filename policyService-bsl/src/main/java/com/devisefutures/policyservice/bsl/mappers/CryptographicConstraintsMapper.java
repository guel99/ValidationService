package com.devisefutures.policyservice.bsl.mappers;

import com.devisefutures.policyservice.bsl.protocols.requestelems.CryptographicConstraintDTO;
import eu.europa.esig.dss.policy.jaxb.Algo;
import eu.europa.esig.dss.policy.jaxb.CryptographicConstraint;
import eu.europa.esig.dss.policy.jaxb.Level;
import eu.europa.esig.dss.policy.jaxb.ListAlgo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = AlgoExpirationDateMapper.class, imports = Level.class)
public interface CryptographicConstraintsMapper {

    @Mapping(target = "level", expression = "java(Level.fromValue(cryptographicConstraintDTO.getLevel()))")
    CryptographicConstraint toCryptographicConstraint(CryptographicConstraintDTO cryptographicConstraintDTO);

    default ListAlgo toListAlgo(List<Algo> list){
        if(list == null)
            return null;
        ListAlgo listAlgo = new ListAlgo();
        listAlgo.getAlgos().addAll(list);
        return listAlgo;
    }
}
