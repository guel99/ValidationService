package com.devisefutures.policyservice.bsl;

import com.devisefutures.policyservice.bsl.mappers.CertificateConstraintsMapperImpl;
import com.devisefutures.policyservice.bsl.mappers.LevelConstraintMapper;
import com.devisefutures.policyservice.bsl.mappers.MultiValuesConstraintMapper;
import com.devisefutures.policyservice.bsl.mappers.TimeConstraintMapper;
import com.devisefutures.policyservice.bsl.protocols.requestelems.CertificateConstraintsDTO;
import com.devisefutures.policyservice.bsl.protocols.requestelems.MultiValuesConstraintDTO;
import com.devisefutures.policyservice.bsl.protocols.requestelems.TimeConstraintDTO;
import eu.europa.esig.dss.policy.jaxb.Level;
import eu.europa.esig.dss.policy.jaxb.TimeConstraint;
import eu.europa.esig.dss.policy.jaxb.TimeUnit;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class CertificateConstraintsTest {

    @InjectMocks
    private CertificateConstraintsMapperImpl certificateConstraintsMapper;

    @Mock
    private TimeConstraintMapper timeConstraintMapper;

    @Mock
    private LevelConstraintMapper levelConstraintMapper;

    @Mock
    private MultiValuesConstraintMapper multiValuesConstraintMapper;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    void test(){
        TimeConstraintDTO timeConstraintDTO = new TimeConstraintDTO();
        timeConstraintDTO.setTimeUnit("SECONDS");
        timeConstraintDTO.setValue(3);
        timeConstraintDTO.setLevel("WARN");

        CertificateConstraintsDTO certificateConstraintsDTO = createCertificateConstraintsDTO(timeConstraintDTO);
        Mockito.when(timeConstraintMapper.toTimeConstraint(timeConstraintDTO)).thenReturn(createTimeConstraint("SECONDS", 3, "WARN"));
    }

    public CertificateConstraintsDTO createCertificateConstraintsDTO(TimeConstraintDTO timeConstraintDTO){
        CertificateConstraintsDTO certificateConstraintsDTO = new CertificateConstraintsDTO();
        certificateConstraintsDTO.setAcceptableRevocationDataFound("FAIL");
        certificateConstraintsDTO.setAuthorityInfoAccessPresent("WARN");
        certificateConstraintsDTO.setCrlNextUpdatePresent("FAIL");
        certificateConstraintsDTO.setIssuedToNeutralPerson("FAIL");
        certificateConstraintsDTO.setNotExpired("FAIL");
        certificateConstraintsDTO.setOcspNextUpdatePresent("FAIL");
        certificateConstraintsDTO.setRecognition("FAIL");

        MultiValuesConstraintDTO multiValuesConstraintDTO = new MultiValuesConstraintDTO();
        multiValuesConstraintDTO.setLevel("FAIL");
        multiValuesConstraintDTO.setAcceptedValues(List.of("PT","US"));
        certificateConstraintsDTO.setCountry(multiValuesConstraintDTO);

        certificateConstraintsDTO.setRevocationFreshness(timeConstraintDTO);
        return certificateConstraintsDTO;
    }

    public TimeConstraint createTimeConstraint(String timeUnit, int value, String level){
        TimeConstraint timeConstraint = new TimeConstraint();
        timeConstraint.setValue(value);
        timeConstraint.setUnit(TimeUnit.fromValue(timeUnit));
        timeConstraint.setLevel(Level.fromValue(level));
        return timeConstraint;
    }
}
