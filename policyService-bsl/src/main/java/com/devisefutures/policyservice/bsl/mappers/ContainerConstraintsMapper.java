package com.devisefutures.policyservice.bsl.mappers;

import com.devisefutures.policyservice.bsl.mappers.enums.AdditionalDataType;
import com.devisefutures.policyservice.bsl.protocols.requestelems.ContainerConstraintsDTO;
import eu.europa.esig.dss.policy.jaxb.ContainerConstraints;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = { LevelConstraintMapper.class, MultiValuesConstraintMapper.class })
public interface ContainerConstraintsMapper {

    ContainerConstraints toContainerConstraints(ContainerConstraintsDTO containerConstraintsDTO);

    default ContainerConstraints toContainerConstraints(ContainerConstraintsDTO containerConstraintsDTO, AdditionalDataType type, List<String> additional){
        ContainerConstraints containerConstraints = toContainerConstraints(containerConstraintsDTO);
        if(type.equals(AdditionalDataType.CONTAINER_TYPES))
            containerConstraints.getAcceptableContainerTypes().getId().addAll(additional);
        else if(type.equals(AdditionalDataType.MIME_TYPE_FILE_CONTENT))
                containerConstraints.getAcceptableMimeTypeFileContent().getId().addAll(additional);
        return containerConstraints;
    }
}
