package com.devisefutures.policyservice.bsl.services;

import com.devisefutures.policyservice.bsl.mappers.LevelConstraintMapper;
import eu.europa.esig.dss.policy.jaxb.LevelConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Provides functionality to xml policy file creation
 */
@Service
public class PolicyAssemblerService {

    private static final List<String> DEFAULT_ACCEPTABLE_CONTAINERS = List.of("application/vnd.etsi.asic-e+zip", "application/vnd.etsi.asic-s+zip");

    @Autowired
    private LevelConstraintMapper levelConstraintMapper;

    /**
     * Returns a xml validation policy as a base64 encoded string
     *
     * @return The XML validation policy as Base64 string
     */
    public LevelConstraint create(String level){
        return levelConstraintMapper.toLevelConstraint(level);
    }
}
