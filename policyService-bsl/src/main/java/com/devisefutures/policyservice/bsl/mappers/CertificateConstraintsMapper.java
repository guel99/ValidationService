package com.devisefutures.policyservice.bsl.mappers;

import com.devisefutures.policyservice.bsl.protocols.requestelems.CertificateConstraintsDTO;
import eu.europa.esig.dss.policy.jaxb.CertificateConstraints;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = { TimeConstraintMapper.class,
                CryptographicConstraintsMapper.class,
                MultiValuesConstraintMapper.class,
                LevelConstraintMapper.class
})
public interface CertificateConstraintsMapper {

    CertificateConstraints toCertificateConstraints(CertificateConstraintsDTO certificateConstraintsDTO);
}
