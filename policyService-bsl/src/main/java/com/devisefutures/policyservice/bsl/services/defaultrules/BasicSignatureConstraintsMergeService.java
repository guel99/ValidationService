package com.devisefutures.policyservice.bsl.services.defaultrules;

import eu.europa.esig.dss.policy.jaxb.BasicSignatureConstraints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicSignatureConstraintsMergeService {

    /**
     * Provides facilities to merge the client requested
     * CertificateConstraints (signingCertificate and caCertificate)
     * to the default certificateConstraints
     * defined by Digital Signature Service
     */
    @Autowired
    private CertificateConstraintsMergeService certificateConstraintsMergeService;


    /**
     * Merges the first specified BasicSignatureConstraints object into the second one.
     * If the second one has some values already attributed, they will be changed
     * to the same values specified in the first one
     * @param basicSignatureConstraints The source BasicSignatureConstraints
     * @param baseBSignatureConstraints The target BasicSignatureConstraints where they were merged to
     * @return The merged basic signature constraints
     */
    public BasicSignatureConstraints merge(BasicSignatureConstraints basicSignatureConstraints, BasicSignatureConstraints baseBSignatureConstraints){
        if(basicSignatureConstraints == null)
            return baseBSignatureConstraints;

        if(basicSignatureConstraints.getSignerInformationStore() != null) baseBSignatureConstraints.setSignerInformationStore(basicSignatureConstraints.getSignerInformationStore());
        if(basicSignatureConstraints.getPdfPageDifference() != null) baseBSignatureConstraints.setPdfPageDifference(basicSignatureConstraints.getPdfPageDifference());
        if(basicSignatureConstraints.getPdfAnnotationOverlap() != null) baseBSignatureConstraints.setPdfAnnotationOverlap(basicSignatureConstraints.getPdfAnnotationOverlap());
        if(basicSignatureConstraints.getPdfVisualDifference() != null) baseBSignatureConstraints.setPdfVisualDifference(basicSignatureConstraints.getPdfVisualDifference());
        if(basicSignatureConstraints.getUndefinedChanges() != null) baseBSignatureConstraints.setUndefinedChanges(basicSignatureConstraints.getUndefinedChanges());
        if(basicSignatureConstraints.getTrustedServiceStatus() != null) baseBSignatureConstraints.setTrustedServiceStatus(basicSignatureConstraints.getTrustedServiceStatus());
        if(basicSignatureConstraints.getTrustedServiceTypeIdentifier() != null) baseBSignatureConstraints.setTrustedServiceTypeIdentifier(basicSignatureConstraints.getTrustedServiceTypeIdentifier());
        if(basicSignatureConstraints.getSigningCertificate() != null)
            baseBSignatureConstraints.setSigningCertificate(
                    certificateConstraintsMergeService.merge(
                            basicSignatureConstraints.getSigningCertificate(),
                            baseBSignatureConstraints.getSigningCertificate()
                    )
            );
        if(basicSignatureConstraints.getCACertificate() != null)
            baseBSignatureConstraints.setCACertificate(
                    certificateConstraintsMergeService.merge(
                            basicSignatureConstraints.getCACertificate(),
                            baseBSignatureConstraints.getCACertificate()
                    )
            );
        if(basicSignatureConstraints.getCryptographic() != null) baseBSignatureConstraints.setCryptographic(baseBSignatureConstraints.getCryptographic());

        return baseBSignatureConstraints;
    }
}
