package com.devisefutures.policyservice.bsl.services.defaultrules;

import eu.europa.esig.dss.policy.jaxb.SignatureConstraints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignatureConstraintsMergeService {

    /**
     * Provides facilities to merge the client requested
     * BasicSignatureConstraints to the DefaultBasicSignatureConstraints
     * defined by Digital Signature Service
     */
    @Autowired
    private BasicSignatureConstraintsMergeService basicSignatureConstraintsMergeService;

    /**
     * Provides facilities to merge the client requested
     * SignedAttributesConstraints to the default signed attributes constraints
     * defined by Digital Signature Service
     */
    @Autowired
    private SignedAttributesConstraintsMergeService signedAttributesConstraintsMergeService;

    /**
     * Provides facilities to merge the client requested
     * UnsignedAttributesConstraints to the default unsigned attributes constraints
     * defined by Digital Signature Service
     */
    @Autowired
    private UnsignedAttributesConstraintsMergeService unsignedAttributesConstraints;

    /**
     * Merges the first specified SignatureConstraints object into the second one.
     * If the second one has some values already attributed, they will be changed
     * to the same values specified in the first one
     * @param signatureConstraints The source CertificateConstraint
     * @param baseSignatureConstraints The target CertificateConstraint where they were merged to
     * @return The merged certificate constraints
     */
    public SignatureConstraints merge(SignatureConstraints signatureConstraints, SignatureConstraints baseSignatureConstraints){
        if(signatureConstraints == null)
            return baseSignatureConstraints;

        if(signatureConstraints.getStructuralValidation() != null) baseSignatureConstraints.setStructuralValidation(signatureConstraints.getStructuralValidation());
        if(signatureConstraints.getAcceptablePolicies() != null) baseSignatureConstraints.setAcceptablePolicies(signatureConstraints.getAcceptablePolicies());
        if(signatureConstraints.getPolicyAvailable() != null) baseSignatureConstraints.setPolicyAvailable(signatureConstraints.getPolicyAvailable());
        if(signatureConstraints.getSignaturePolicyStorePresent() != null) baseSignatureConstraints.setSignaturePolicyStorePresent(signatureConstraints.getSignaturePolicyStorePresent());
        if(signatureConstraints.getPolicyHashMatch() != null) baseSignatureConstraints.setPolicyHashMatch(signatureConstraints.getPolicyHashMatch());
        if(signatureConstraints.getAcceptableFormats() != null) baseSignatureConstraints.setAcceptableFormats(signatureConstraints.getAcceptableFormats());
        if(signatureConstraints.getFullScope() != null) baseSignatureConstraints.setFullScope(signatureConstraints.getFullScope());
        if(signatureConstraints.getBasicSignatureConstraints() != null)
            baseSignatureConstraints.setBasicSignatureConstraints(
                    basicSignatureConstraintsMergeService.merge(
                            signatureConstraints.getBasicSignatureConstraints(), baseSignatureConstraints.getBasicSignatureConstraints()
                    )
            );
        if(signatureConstraints.getSignedAttributes() != null)
            baseSignatureConstraints.setSignedAttributes(
                    signedAttributesConstraintsMergeService.merge(
                            signatureConstraints.getSignedAttributes(), baseSignatureConstraints.getSignedAttributes()
                    )
            );
        if(signatureConstraints.getUnsignedAttributes() != null)
            baseSignatureConstraints.setUnsignedAttributes(
                    unsignedAttributesConstraints.merge(
                            signatureConstraints.getUnsignedAttributes(), baseSignatureConstraints.getUnsignedAttributes()
                    )
            );

        return baseSignatureConstraints;
    }
}
