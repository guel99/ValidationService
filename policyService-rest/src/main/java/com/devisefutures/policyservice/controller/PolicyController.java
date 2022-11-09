package com.devisefutures.policyservice.controller;

import com.devisefutures.policyservice.bsl.enums.PolicyCreationMode;
import com.devisefutures.policyservice.bsl.protocols.ValidationPolicyGetResponse;
import com.devisefutures.policyservice.bsl.protocols.ValidationPolicyRequest;
import com.devisefutures.policyservice.bsl.protocols.ValidationPolicyCreationResponse;
import com.devisefutures.policyservice.bsl.services.PolicyAssemblerService;
import com.devisefutures.policyservice.bsl.services.PolicyStoreService;
import com.devisefutures.signaturevalidator.common.annotations.EnumValidator;
import com.devisefutures.signaturevalidator.common.exceptions.InvalidPolicyCreationModeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.validation.Valid;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

@RestController
@RequestMapping(value = "/policy/api")
public class PolicyController {

    /**
     * Service to create the policy file
     */
    @Autowired
    private PolicyAssemblerService policyAssemblerService;

    /**
     * Handles the policy store
     */
    @Autowired
    private PolicyStoreService policyStoreService;

    @PostMapping(value = "/create/{mode}", consumes = "application/json", produces = "application/json")
    @CrossOrigin(origins = "${webform.origin}")
    public ResponseEntity<ValidationPolicyCreationResponse> createPolicy(@Valid @RequestBody ValidationPolicyRequest request, @PathVariable String mode)
            throws JAXBException, IOException, SAXException, XMLStreamException, InvalidPolicyCreationModeException {
        String b64Policy = policyAssemblerService.assemble(request, mode);
        return ResponseEntity.ok(policyStoreService.storePolicy(b64Policy));
    }

    @GetMapping(value = "/{policyId}")
    @CrossOrigin(origins = "${webform.origin}")
    public ResponseEntity<ValidationPolicyGetResponse> getPolicy(@PathVariable String policyId) throws IOException {
        return ResponseEntity.ok(policyStoreService.getPolicy(policyId));
    }

    @GetMapping(value = "/connection/test")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("The service is up!");
    }
}
