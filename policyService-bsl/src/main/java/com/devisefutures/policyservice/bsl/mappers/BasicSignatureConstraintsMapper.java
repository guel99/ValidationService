package com.devisefutures.policyservice.bsl.mappers;

import com.devisefutures.policyservice.bsl.protocols.requestelems.BasicSignatureConstraintsDTO;
import eu.europa.esig.dss.policy.jaxb.BasicSignatureConstraints;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = { LevelConstraintMapper.class,
                MultiValuesConstraintMapper.class,
                CertificateConstraintsMapper.class,
                CryptographicConstraintsMapper.class
})
public interface BasicSignatureConstraintsMapper {

    @Mapping(source = "signingCertificateConstraints", target = "signingCertificate")
    @Mapping(source = "caCertificateConstraints", target = "CACertificate")
    @Mapping(source = "cryptographicConstraints", target = "cryptographic")
    BasicSignatureConstraints toBasicSignatureConstraints(BasicSignatureConstraintsDTO basicSignatureConstraintsDTO);
}
