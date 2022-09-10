package com.devisefutures.policyservice.bsl.protocols.requestelems;

import com.devisefutures.signaturevalidator.common.annotations.EnumValidator;
import eu.europa.esig.dss.policy.jaxb.Level;
import lombok.Data;


/**
 * Group of common checks for any kind of signed token
 * (signature, timestamp or revocation data)
 */
@Data
public class BasicSignatureConstraintsDTO {

    /**
     * Checks if CMS Signed Data Signer Information
     * Store has only one signer information (PAdES only)
     */
    @EnumValidator(enumClazz = Level.class)
    private String signerInformationStore;

    /**
     * Checks if the signed revision has more or less pages
     * comparing to the final provided document
     */
    @EnumValidator(enumClazz = Level.class)
    private String pdfPageDifference;

    /**
     * Checks if the document contains any annotation overlaps
     */
    @EnumValidator(enumClazz = Level.class)
    private String pdfAnnotationOverlap;

    /**
     * Checks if the signed revision have a visual
     * difference(s) with the provided document
     */
    @EnumValidator(enumClazz = Level.class)
    private String pdfVisualDifference;

    /**
     * Verifies whether the document does not contain undefined
     * changes after the signature revision
     */
    @EnumValidator(enumClazz = Level.class)
    private String undefinedChanges;

    /**
     * Checks if the related trust service type identifier is
     * in the acceptable values list
     */
    private MultiValuesConstraintDTO trustedServiceTypeIdentifier;

    /**
     * Checks if the related trust service status is in the
     * acceptable values list
     */
    private MultiValuesConstraintDTO trustedServiceStatus;

    /**
     * Group of checks to operate on the signing certificate
     */
    private CertificateConstraintsDTO signingCertificateConstraints;

    /**
     * Group of checks to operate on the CA certificate(s)
     */
    private CertificateConstraintsDTO caCertificateConstraints;

    /**
     * Group of cryptographic checks to operate on the current signed token
     */
    private CryptographicConstraintDTO cryptographicConstraints;
}
