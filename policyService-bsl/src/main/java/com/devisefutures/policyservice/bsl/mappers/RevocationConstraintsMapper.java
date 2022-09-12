package com.devisefutures.policyservice.bsl.mappers;

import com.devisefutures.policyservice.bsl.protocols.requestelems.RevocationConstraintsDTO;
import eu.europa.esig.dss.policy.jaxb.RevocationConstraints;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        LevelConstraintMapper.class,
        BasicSignatureConstraintsMapper.class
})
public interface RevocationConstraintsMapper {

    @Mapping(source = "ocspCertHashPresent", target = "OCSPCertHashPresent")
    @Mapping(source = "ocspCertHashMatch", target = "OCSPCertHashMatch")
    RevocationConstraints toRevocationConstraints(RevocationConstraintsDTO revocationConstraintsDTO);
}
