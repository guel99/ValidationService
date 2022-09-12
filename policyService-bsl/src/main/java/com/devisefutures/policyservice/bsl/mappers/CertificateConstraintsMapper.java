package com.devisefutures.policyservice.bsl.mappers;

import com.devisefutures.policyservice.bsl.protocols.requestelems.CertificateConstraintsDTO;
import eu.europa.esig.dss.policy.jaxb.CertificateConstraints;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = { TimeConstraintMapper.class,
                CryptographicConstraintsMapper.class,
                MultiValuesConstraintMapper.class,
                LevelConstraintMapper.class
})
public interface CertificateConstraintsMapper {

    @Mapping(source = "cryptographicConstraint", target = "cryptographic")
    @Mapping(source = "ocspNextUpdatePresent", target = "OCSPNextUpdatePresent")
    @Mapping(source = "crlNextUpdatePresent", target = "CRLNextUpdatePresent")
    CertificateConstraints toCertificateConstraints(CertificateConstraintsDTO certificateConstraintsDTO);
}
