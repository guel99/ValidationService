package com.devisefutures.policyservice.bsl.services.defaultrules;

import eu.europa.esig.dss.policy.jaxb.EIDAS;
import org.springframework.stereotype.Service;

@Service
public class EIDASMergeService {

    public EIDAS merge(EIDAS eidas, EIDAS baseEIDAS){
        if(eidas == null)
            return baseEIDAS;
        if(eidas.getTLFreshness() != null) baseEIDAS.setTLFreshness(eidas.getTLFreshness());
        if(eidas.getTLNotExpired() != null) baseEIDAS.setTLNotExpired(eidas.getTLNotExpired());
        if(eidas.getTLWellSigned() != null) baseEIDAS.setTLWellSigned(eidas.getTLWellSigned());
        if(eidas.getTLVersion() != null) baseEIDAS.setTLVersion(eidas.getTLVersion());

        return baseEIDAS;
    }
}
