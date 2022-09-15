package com.devisefutures.policyservice.bsl.services;

import com.devisefutures.policyservice.bsl.enums.PolicyCreationMode;
import com.devisefutures.policyservice.bsl.mappers.ConstraintsParametersMapper;
import com.devisefutures.policyservice.bsl.protocols.ValidationPolicyRequest;
import com.devisefutures.signaturevalidator.common.exceptions.InvalidPolicyCreationModeException;
import eu.europa.esig.dss.policy.ValidationPolicyFacade;
import eu.europa.esig.dss.policy.jaxb.ConstraintsParameters;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
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
     * Service used to assemble a policy DSS-default based on
     */
    @Autowired
    private DSSValidationPolicyMergeService dssValidationPolicyMergeService;

    /**
     * Returns a xml validation policy as a base64 encoded string
     * @return The XML validation policy as Base64 string
     */
    public String assemble(ValidationPolicyRequest request, String mode)
            throws JAXBException, IOException, SAXException, XMLStreamException, InvalidPolicyCreationModeException {

        try {
            PolicyCreationMode creationMode = PolicyCreationMode.valueOf(mode.toUpperCase());
            ConstraintsParameters constraintsParameters = constraintsParametersMapper.toConstraintsParameters(request);

            if (creationMode.equals(PolicyCreationMode.SAFE))
                /* We merge the created policy with the DSS default one */
                dssValidationPolicyMergeService.merge(constraintsParameters);

            String xmlString = ValidationPolicyFacade.newFacade().marshall(constraintsParameters);
            return Base64.toBase64String(xmlString.getBytes(StandardCharsets.UTF_8));
        } catch (IllegalArgumentException e){
            throw new InvalidPolicyCreationModeException("Creation model is invalid");
        }
    }
}
