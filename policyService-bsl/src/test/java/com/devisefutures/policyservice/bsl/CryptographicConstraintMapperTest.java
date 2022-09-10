package com.devisefutures.policyservice.bsl;

import com.devisefutures.policyservice.bsl.mappers.AlgoExpirationDateMapperImpl;
import com.devisefutures.policyservice.bsl.mappers.CryptographicConstraintsMapperImpl;
import com.devisefutures.policyservice.bsl.protocols.requestelems.AlgoExpirationDateDTO;
import com.devisefutures.policyservice.bsl.protocols.requestelems.CryptographicConstraintDTO;
import eu.europa.esig.dss.policy.jaxb.Algo;
import eu.europa.esig.dss.policy.jaxb.AlgoExpirationDate;
import eu.europa.esig.dss.policy.jaxb.CryptographicConstraint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

class CryptographicConstraintMapperTest {

    @InjectMocks
    private CryptographicConstraintsMapperImpl cryptographicConstraintMapper;

    @Mock
    private AlgoExpirationDateMapperImpl algoExpirationDateMapper;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMap(){
        CryptographicConstraintDTO cryptographicConstraintDTO = new CryptographicConstraintDTO();
        Algo sha256 = new Algo();
        sha256.setValue("SHA256");
        sha256.setDate("2026");
        Algo rsa = new Algo();
        rsa.setValue("RSA");
        rsa.setSize(1024);
        rsa.setDate("2009");
        Algo rsa_3000 = new Algo();
        rsa_3000.setValue("RSA");
        rsa_3000.setSize(3000);
        rsa_3000.setDate("2026");
        List<Algo> acceptableEncryptionAlgo = List.of(rsa);
        List<Algo> acceptableDigestAlgo = List.of(sha256);
        AlgoExpirationDateDTO algoExpirationDateDTO = new AlgoExpirationDateDTO();
        algoExpirationDateDTO.setFormat("yyyy");
        algoExpirationDateDTO.setAlgos(List.of(rsa, sha256, rsa_3000));
        cryptographicConstraintDTO.setAcceptableEncryptionAlgo(List.of(rsa));
        cryptographicConstraintDTO.setAcceptableDigestAlgo(List.of(sha256));
        cryptographicConstraintDTO.setMiniPublicKeySize(List.of(rsa));
        cryptographicConstraintDTO.setAlgoExpirationDate(algoExpirationDateDTO);
        cryptographicConstraintDTO.setLevel("FAIL");

        AlgoExpirationDate algoExpirationDate = createAlgoExpirationDate(List.of(rsa, sha256, rsa_3000), "yyyy");
        Mockito.when(algoExpirationDateMapper.toAlgoExpirationDate(cryptographicConstraintDTO.getAlgoExpirationDate())).thenReturn(algoExpirationDate);
        CryptographicConstraint cryptographicConstraint = cryptographicConstraintMapper.toCryptographicConstraint(cryptographicConstraintDTO);

        Assertions.assertNotNull(cryptographicConstraint.getMiniPublicKeySize());
        Assertions.assertNotNull(cryptographicConstraint.getAlgoExpirationDate());
        Assertions.assertNotNull(cryptographicConstraint.getAcceptableDigestAlgo());
        Assertions.assertNotNull(cryptographicConstraint.getAcceptableEncryptionAlgo());
    }

    private AlgoExpirationDate createAlgoExpirationDate(List<Algo> algos, String format){
        AlgoExpirationDate algoExpirationDate = new AlgoExpirationDate();
        algoExpirationDate.getAlgos().addAll(algos);
        algoExpirationDate.setFormat(format);
        return algoExpirationDate;
    }
}
