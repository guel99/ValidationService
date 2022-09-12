package com.devisefutures.policyservice.bsl.mappers;

import com.devisefutures.policyservice.bsl.protocols.requestelems.EIDASDTO;
import eu.europa.esig.dss.policy.jaxb.EIDAS;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        LevelConstraintMapper.class,
        TimeConstraintMapper.class,
        ValueConstraintMapper.class
})
public interface EIDASMapper {

    @Mapping(source = "tlFreshness", target = "TLFreshness")
    @Mapping(source = "tlNotExpired", target = "TLNotExpired")
    @Mapping(source = "tlWellSigned", target = "TLWellSigned")
    @Mapping(source = "tlVersion", target = "TLVersion")
    EIDAS toEIDAS(EIDASDTO eidasdto);
}
