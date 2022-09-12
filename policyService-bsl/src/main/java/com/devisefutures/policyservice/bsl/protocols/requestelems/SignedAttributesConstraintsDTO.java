package com.devisefutures.policyservice.bsl.protocols.requestelems;

import com.devisefutures.policyservice.bsl.protocols.annotations.MultiValuesEnumValidator;
import com.devisefutures.signaturevalidator.common.annotations.EnumValidator;
import com.devisefutures.signaturevalidator.common.annotations.ListStrEnumValidator;
import eu.europa.esig.dss.policy.jaxb.Level;
import lombok.Data;

import java.util.List;

/**
 * Group of constraints on the signed attributes/properties
 */
@Data
public class SignedAttributesConstraintsDTO {

    /**
     * Checks the presence of the signingCertificate (V1/V2) attribute
     */
    @EnumValidator(enumClazz = Level.class)
    private String signingCertificatePresent;

    /**
     * Checks the unicity of the signingCertificate (V1/V2) attribute
     * (if the signingCertificate reference unique)
     */
    @EnumValidator(enumClazz = Level.class)
    private String unicitySigningCertificate;

    /**
     * Checks is the signingCertificate (V1/V2) attribute covers only
     * those which are present in the certificate chain
     */
    @EnumValidator(enumClazz = Level.class)
    private String signingCertificateRefersCertificateChain;

    /**
     * hecks if all certificates in the chain are referenced by the
     * signingCertificate (V1/V2) attribute, when present
     */
    @EnumValidator(enumClazz = Level.class)
    private String referencesToAllCertificateChainPresent;

    /**
     * Verifies a validity against cryptographic constraints for a DigestAlgorithm
     * used on a signing-certificate-reference creation, when present
     */
    @EnumValidator(enumClazz = Level.class)
    private String signingCertificateDigestAlgorithm;

    /**
     * Checks the presence of the signing certificate digest
     */
    @EnumValidator(enumClazz = Level.class)
    private String certDigestPresent;

    /**
     * Checks if any signing certificate digest match with
     * the found signer
     */
    @EnumValidator(enumClazz = Level.class)
    private String certDigestMatch;

    /**
     * Checks if the serial number of the issuer certificate
     * present within a signing-certificate reference matches
     */
    @EnumValidator(enumClazz = Level.class)
    private String issuerSerialMatch;

    /**
     * Checks if 'kid' (key identifier) header parameter is
     * present within a protected header of the signature (JAdES only)
     */
    @EnumValidator(enumClazz = Level.class)
    private String keyIdentifierPresent;

    /**
     * Checks if the value of 'kid' (key identifier) header parameter
     * matches the signing-certificate (JAdES only)
     */
    @EnumValidator(enumClazz = Level.class)
    private String keyIdentifierMatch;

    /**
     * Checks the presence of the signing-time attribute
     */
    @EnumValidator(enumClazz = Level.class)
    private String signingTime;

    /**
     * Checks if the content-type attribute has the expected value
     */
    private ValueConstraintDTO contentType;

    /**
     * Checks if the content-hints attribute (CAdES) has the expected value
     */
    private ValueConstraintDTO contentHints;

    /**
     * Checks if the content-identifier attribute (CAdES) has the expected valu
     */
    private ValueConstraintDTO contentIdentifier;

    /**
     * Checks the presence of message-digest attribute (CAdES) or
     * SignedProperties (XAdES)
     */
    @EnumValidator(enumClazz = Level.class)
    private String messageDigestOrSignedPropertiesPresent;

    /**
     * Verifies whether the size of the elliptic curve key used to create
     * the signature corresponds to the defined signature algorithm (see RFC 7518)
     */
    @EnumValidator(enumClazz = Level.class)
    private String ellipticCurveKeySize;

    /**
     * Checks if CommitmentTypeIndication is in the acceptable values list
     */
    @MultiValuesEnumValidator(enumClazz = Level.class)
    private MultiValuesConstraintDTO commitmentTypeIndication;

    /**
     * Checks the presence of the production/localization attribute
     */
    @EnumValidator(enumClazz = Level.class)
    private String signerLocation;

    /**
     * Checks if claimed role is in the acceptable values list
     */
    @MultiValuesEnumValidator(enumClazz = Level.class)
    private MultiValuesConstraintDTO claimedRoles;

    /**
     * Checks if certified role is in the acceptable values list
     */
    @MultiValuesEnumValidator(enumClazz = Level.class)
    private MultiValuesConstraintDTO certifiedRoles;

    /**
     * Checks the presence of a content-timestamp attribute
     */
    @EnumValidator(enumClazz = Level.class)
    private String contentTimeStamp;

    /**
     * Checks the message imprint validity for present content-timestamps
     */
    @EnumValidator(enumClazz = Level.class)
    private String contentTimeStampMessageImprint;
}
