package com.devisefutures.policyservice.bsl.services.defaultrules.signature;

import eu.europa.esig.dss.policy.jaxb.BasicSignatureConstraints;
import eu.europa.esig.dss.policy.jaxb.Level;
import eu.europa.esig.dss.policy.jaxb.LevelConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

@Service
public class DefaultBasicSignatureConstraintsService {

    /**
     * The value for reference data existence constraint
     * non-editable field
     */
    private static final Level REFERENCE_DATA_EXISTENCE = Level.FAIL;

    /**
     * The value for reference data intact constraint non-editable
     */
    private static final Level REFERENCE_DATA_INTACT = Level.FAIL;


    private static final Level MANIFEST_ENTRY_OBJECT_EXISTENCE = Level.WARN;

    private static final Level SIGNATURE_INTACT = Level.FAIL;

    private static final Level SIGNATURE_VALID = Level.FAIL;

    private static final Level SIGNATURE_DUPLICATED = Level.FAIL;

    private static final Level PROSPECTIVE_CERTIFICATE_CHAIN = Level.FAIL;

    @Autowired
    private DefaultSigningCertificateService defaultSigningCertificateService;

    /**
     * Applies the default rules for non-editable/empty fields
     * related with SignatureConstraints BasicSignatureConstraints
     * @param basicSignatureConstraints The basicSignatureConstraints
     * @return The new BasicSignatureConstraints
     */
    public BasicSignatureConstraints setDefaultRules(BasicSignatureConstraints basicSignatureConstraints) throws XMLStreamException, JAXBException, IOException, SAXException {
        if(basicSignatureConstraints == null){
            basicSignatureConstraints = new BasicSignatureConstraints();
        }
        setReferenceDataExistence(basicSignatureConstraints);
        setReferenceDataIntact(basicSignatureConstraints);
        setManifestEntryObjectExistence(basicSignatureConstraints);
        setSignatureIntact(basicSignatureConstraints);
        setSignatureValid(basicSignatureConstraints);
        setSignatureDuplicated(basicSignatureConstraints);
        setProspectiveCertificateChain(basicSignatureConstraints);

        basicSignatureConstraints.setSigningCertificate(defaultSigningCertificateService.setDefaultRules(basicSignatureConstraints.getSigningCertificate()));

        return basicSignatureConstraints;
    }

    /**
     * Sets the value for reference data existence constraint
     * @param basicSignatureConstraints The basic signature constraints
     */
    public void setReferenceDataExistence(BasicSignatureConstraints basicSignatureConstraints){
        LevelConstraint referenceDataExistence = new LevelConstraint();
        referenceDataExistence.setLevel(REFERENCE_DATA_EXISTENCE);
        basicSignatureConstraints.setReferenceDataExistence(referenceDataExistence);
    }

    /**
     * Sets the value for referenceDataIntact constraint
     * @param basicSignatureConstraints The basic signature constraints
     */
    public void setReferenceDataIntact(BasicSignatureConstraints basicSignatureConstraints){
        LevelConstraint referenceDataIntact = new LevelConstraint();
        referenceDataIntact.setLevel(REFERENCE_DATA_INTACT);
        basicSignatureConstraints.setReferenceDataIntact(referenceDataIntact);
    }

    /**
     * Sets the default value for manifest entry object existence
     * @param basicSignatureConstraints
     */
    public void setManifestEntryObjectExistence(BasicSignatureConstraints basicSignatureConstraints){
        LevelConstraint manifestEntryObjectExistence = new LevelConstraint();
        manifestEntryObjectExistence.setLevel(MANIFEST_ENTRY_OBJECT_EXISTENCE);
        basicSignatureConstraints.setManifestEntryObjectExistence(manifestEntryObjectExistence);
    }

    /**
     * Sets the default value for signature intact existence
     * @param basicSignatureConstraints
     */
    public void setSignatureIntact(BasicSignatureConstraints basicSignatureConstraints){
        LevelConstraint signatureIntact = new LevelConstraint();
        signatureIntact.setLevel(SIGNATURE_INTACT);
        basicSignatureConstraints.setSignatureIntact(signatureIntact);
    }

    /**
     * Sets the default value for signature valid existence
     * @param basicSignatureConstraints
     */
    public void setSignatureValid(BasicSignatureConstraints basicSignatureConstraints){
        LevelConstraint signatureValid = new LevelConstraint();
        signatureValid.setLevel(SIGNATURE_VALID);
        basicSignatureConstraints.setSignatureValid(signatureValid);
    }

    /**
     * Sets the default value for signature duplicated existence
     * @param basicSignatureConstraints
     */
    public void setSignatureDuplicated(BasicSignatureConstraints basicSignatureConstraints){
        LevelConstraint signatureDuplicated = new LevelConstraint();
        signatureDuplicated.setLevel(SIGNATURE_DUPLICATED);
        basicSignatureConstraints.setSignatureDuplicated(signatureDuplicated);
    }

    /**
     * Sets the default value for prospective certificate chain existence
     * @param basicSignatureConstraints
     */
    public void setProspectiveCertificateChain(BasicSignatureConstraints basicSignatureConstraints){
        LevelConstraint prospectiveCertificateChain = new LevelConstraint();
        prospectiveCertificateChain.setLevel(PROSPECTIVE_CERTIFICATE_CHAIN);
        basicSignatureConstraints.setProspectiveCertificateChain(prospectiveCertificateChain);
    }

    // TODO - implement default constraints to signingCertificate and CACertificate
}
