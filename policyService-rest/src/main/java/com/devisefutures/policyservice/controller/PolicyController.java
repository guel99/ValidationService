package com.devisefutures.policyservice.controller;

import com.devisefutures.policyservice.bsl.services.PolicyAssemblerService;
import com.devisefutures.policyservice.bsl.services.PolicyStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    // TODO - terminar implementação do controller
}
