package com.devisefutures.policyservice.bsl.services;

import com.devisefutures.policyservice.bsl.mappers.ConstraintsParametersMapper;
import com.devisefutures.policyservice.bsl.protocols.ValidationPolicyRequest;
import com.devisefutures.policyservice.bsl.services.defaultrules.DefaultContainerConstraintsService;
import com.devisefutures.policyservice.bsl.services.defaultrules.DefaultSignatureConstraintsService;
import eu.europa.esig.dss.policy.ValidationPolicyFacade;
import eu.europa.esig.dss.policy.jaxb.ConstraintsParameters;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Provides functionality to xml policy file creation
 */
@Service
public class PolicyAssemblerService {

    @Autowired
    private ConstraintsParametersMapper constraintsParametersMapper;

    /**
     * Provides the default fields in the policy creation
     * process for container constraints properties
     */
    @Autowired
    private DefaultContainerConstraintsService defaultContainerConstraintsService;

    /**
     * Provides the default fields in the policy creation
     * process for signature constraints properties
     */
    @Autowired
    private DefaultSignatureConstraintsService defaultSignatureConstraintsService;

    /**
     * Returns a xml validation policy as a base64 encoded string
     * @return The XML validation policy as Base64 string
     */
    public String assemble(ValidationPolicyRequest request) throws JAXBException, IOException, SAXException {
        ConstraintsParameters constraintsParameters = constraintsParametersMapper.toConstraintsParameters(request);

        /* Set the empty/non-editable parameters in the validation policy already created */
        constraintsParameters.setContainerConstraints(defaultContainerConstraintsService.setDefaultRules(constraintsParameters.getContainerConstraints()));
        constraintsParameters.setSignatureConstraints(defaultSignatureConstraintsService.setDefaultRules(constraintsParameters.getSignatureConstraints()));

        String xmlString = ValidationPolicyFacade.newFacade().marshall(constraintsParameters);
        return Base64.toBase64String(xmlString.getBytes(StandardCharsets.UTF_8));
    }
}
