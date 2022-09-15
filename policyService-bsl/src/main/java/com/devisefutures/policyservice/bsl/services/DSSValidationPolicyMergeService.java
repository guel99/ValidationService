package com.devisefutures.policyservice.bsl.services;

import com.devisefutures.policyservice.bsl.services.defaultrules.*;
import eu.europa.esig.dss.policy.ValidationPolicy;
import eu.europa.esig.dss.policy.ValidationPolicyFacade;
import eu.europa.esig.dss.policy.jaxb.ConstraintsParameters;
import eu.europa.esig.dss.policy.jaxb.ModelConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

/**
 * Provides the policy creating with safe mode enabled. In
 * other words, the policy is created from the DSS default
 * validation policy. The only changes are the user defined constraints.
 */
@Service
public class DSSValidationPolicyMergeService {

    private ValidationPolicyFacade validationPolicyFacade = ValidationPolicyFacade.newFacade();

    /**
     * Provides facilities to merge instances of ContainerConstraints
     */
    @Autowired
    private ContainerConstraintsMergeService containerConstraintsMergeService;

    /**
     * Provides facilities to merge instances of SignatureConstraints
     */
    @Autowired
    private SignatureConstraintsMergeService signatureConstraintsMergeService;

    /**
     * Provides facilities to merge instances of TimeStampConstraints
     */
    @Autowired
    private TimeStampConstraintsMergeService timeStampConstraintsMergeService;

    /**
     * Provides facilities to merge instances of RevocationConstraints
     */
    @Autowired
    private RevocationConstraintsMergeService revocationConstraintsMergeService;

    /**
     * Provides facilities to merge instances of EIDAS constraints
     */
    @Autowired
    private EIDASMergeService eidasMergeService;

    /**
     * Merges a created policy with the default validation policy
     * defined by Digital Signature Service (DSS)
     * @param policy
     * @return
     * @throws XMLStreamException
     * @throws JAXBException
     * @throws IOException
     * @throws SAXException
     */
    public ConstraintsParameters merge(ConstraintsParameters policy)
            throws XMLStreamException, JAXBException, IOException, SAXException {

        ValidationPolicy dssDefaultPolicy = validationPolicyFacade.getDefaultValidationPolicy();

        policy.setContainerConstraints(containerConstraintsMergeService.merge(policy.getContainerConstraints(), dssDefaultPolicy.getContainerConstraints()));
        policy.setSignatureConstraints(signatureConstraintsMergeService.merge(policy.getSignatureConstraints(), dssDefaultPolicy.getSignatureConstraints()));
        policy.setCounterSignatureConstraints(signatureConstraintsMergeService.merge(policy.getCounterSignatureConstraints(), dssDefaultPolicy.getCounterSignatureConstraints()));
        if(policy.getCryptographic() == null)
            policy.setCryptographic(dssDefaultPolicy.getCryptographic());
        policy.setTimestamp(timeStampConstraintsMergeService.merge(policy.getTimestamp(), dssDefaultPolicy.getTimestampConstraints()));
        policy.setRevocation(revocationConstraintsMergeService.merge(policy.getRevocation(), dssDefaultPolicy.getRevocationConstraints()));
        if(policy.getModel() == null) {
            ModelConstraint modelConstraint = new ModelConstraint();
            modelConstraint.setValue(dssDefaultPolicy.getValidationModel());
            policy.setModel(modelConstraint);
        }

        return policy;
    }
}
