package com.devisefutures.policyservice.bsl.services.defaultrules;

import eu.europa.esig.dss.policy.jaxb.ContainerConstraints;
import org.springframework.stereotype.Service;

@Service
public class ContainerConstraintsMergeService {

    /**
     * Merges the first specified ContainerConstraints object into the second one.
     * If the second one has some values already attributed, they will be changed
     * to the same values specified in the first one
     * @param containerConstraints The source ContainerConstraints
     * @param baseContConstraints The target ContainerConstraints where they were merged to
     * @return The merged certificate constraints
     */
    public ContainerConstraints merge(ContainerConstraints containerConstraints, ContainerConstraints baseContConstraints){
        if(containerConstraints == null)
            return baseContConstraints;

        if(containerConstraints.getAcceptableContainerTypes() != null) baseContConstraints.setAcceptableContainerTypes(containerConstraints.getAcceptableContainerTypes());
        if(containerConstraints.getAcceptableMimeTypeFileContent() != null) baseContConstraints.setAcceptableMimeTypeFileContent(containerConstraints.getAcceptableMimeTypeFileContent());
        if(containerConstraints.getAllFilesSigned() != null) baseContConstraints.setAllFilesSigned(containerConstraints.getAllFilesSigned());

        return baseContConstraints;
    }
}
