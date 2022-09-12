package com.devisefutures.policyservice.bsl.mappers;

import com.devisefutures.policyservice.bsl.protocols.requestelems.TimeStampConstraintsDTO;
import eu.europa.esig.dss.policy.jaxb.TimestampConstraints;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        LevelConstraintMapper.class,
        TimeConstraintMapper.class,
        BasicSignatureConstraintsMapper.class,
        SignedAttributesConstraintsMapper.class
})
public interface TimeStampConstraintsMapper {

    @Mapping(source = "signedAttributesConstraints", target = "signedAttributes")
    @Mapping(source = "tsaGeneralNamePresent", target = "TSAGeneralNamePresent")
    @Mapping(source = "tsaGeneralNameContentMatch", target = "TSAGeneralNameContentMatch")
    @Mapping(source = "tsaGeneralNameOrderMatch", target = "TSAGeneralNameOrderMatch")
    TimestampConstraints toTimeStampConstraints(TimeStampConstraintsDTO timeStampConstraintsDTO);
}
