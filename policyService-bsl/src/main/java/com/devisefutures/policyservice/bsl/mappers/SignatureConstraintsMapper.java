package com.devisefutures.policyservice.bsl.mappers;

import com.devisefutures.policyservice.bsl.protocols.requestelems.SignatureConstraintsDTO;
import eu.europa.esig.dss.policy.jaxb.SignatureConstraints;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        LevelConstraintMapper.class,
        MultiValuesConstraintMapper.class,
        BasicSignatureConstraintsMapper.class,
        SignedAttributesConstraintsMapper.class,
        UnsignedAttributesConstraintsMapper.class
})
public interface SignatureConstraintsMapper {

    @Mapping(source = "signedAttributesConstraints", target = "signedAttributes")
    @Mapping(source = "unsignedAttributesConstraints", target = "unsignedAttributes")
    SignatureConstraints toSignatureConstraints(SignatureConstraintsDTO signatureConstraintsDTO);
}
