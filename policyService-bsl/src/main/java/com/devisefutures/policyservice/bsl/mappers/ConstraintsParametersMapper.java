package com.devisefutures.policyservice.bsl.mappers;

import com.devisefutures.policyservice.bsl.mappers.enums.AdditionalDataType;
import com.devisefutures.policyservice.bsl.protocols.ValidationPolicyRequest;
import eu.europa.esig.dss.policy.jaxb.ConstraintsParameters;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring", uses = {
        ContainerConstraintsMapper.class,
        SignatureConstraintsMapper.class,
        TimeStampConstraintsMapper.class,
        RevocationConstraintsMapper.class,
        CryptographicConstraintsMapper.class,
        ModelConstraintMapper.class,
        EIDASMapper.class
})
public interface ConstraintsParametersMapper {

    @Mapping(source = "timeStampConstraints", target = "timestamp")
    @Mapping(source = "revocationConstraints", target = "revocation")
    @Mapping(source = "cryptographicConstraint", target = "cryptographic")
    @Mapping(source = "modelConstraint", target = "model")
    ConstraintsParameters toConstraintsParameters(ValidationPolicyRequest validationPolicyRequest);
}
