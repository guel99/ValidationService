package com.devisefutures.policyservice.bsl.services;

import com.devisefutures.policyservice.bsl.mappers.ConstraintsParametersMapper;
import com.devisefutures.policyservice.bsl.protocols.ValidationPolicyRequest;
import eu.europa.esig.dss.policy.ValidationPolicyFacade;
import eu.europa.esig.dss.policy.jaxb.ConstraintsParameters;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Provides functionality to xml policy file creation
 */
@Service
public class PolicyAssemblerService {

    private static final List<String> DEFAULT_ACCEPTABLE_CONTAINERS = List.of("application/vnd.etsi.asic-e+zip", "application/vnd.etsi.asic-s+zip");

    @Autowired
    private ConstraintsParametersMapper constraintsParametersMapper;

    /**
     * Returns a xml validation policy as a base64 encoded string
     * @return The XML validation policy as Base64 string
     */
    public String assemble(ValidationPolicyRequest request) throws JAXBException, IOException, SAXException {
        ConstraintsParameters constraintsParameters = constraintsParametersMapper.toConstraintsParameters(request);
        constraintsParameters.getContainerConstraints().getAcceptableContainerTypes().getId().addAll(DEFAULT_ACCEPTABLE_CONTAINERS);
        String xmlString = ValidationPolicyFacade.newFacade().marshall(constraintsParameters);
        return Base64.toBase64String(xmlString.getBytes(StandardCharsets.UTF_8));
    }
}
