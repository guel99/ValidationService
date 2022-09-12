package com.devisefutures.policyservice.bsl.protocols.requestelems;

import com.devisefutures.policyservice.bsl.protocols.annotations.MultiValuesEnumValidator;
import com.devisefutures.signaturevalidator.common.annotations.EnumValidator;
import eu.europa.esig.dss.enumerations.KeyUsageBit;
import eu.europa.esig.dss.policy.jaxb.Level;
import lombok.Data;


/**
 * Group of checks related to a certificate
 */
@Data
public class CertificateConstraintsDTO {

    /**
     * Checks if the signing certificate is known
     */
    @EnumValidator(enumClazz = Level.class)
    private String recognition;

    /**
     * Checks if the signing certificate is well signed
     */
    @EnumValidator(enumClazz = Level.class)
    private String signature;

    /**
     * Checks if the validation time is in the validity
     * range of the certificate
     */
    @EnumValidator(enumClazz = Level.class)
    private String notExpired;

    /**
     * Checks the presence of the AIA (issuer) in the certificate
     */
    @EnumValidator(enumClazz = Level.class)
    private String authorityInfoAccessPresent;

    /**
     * Checks the presence of the CRL distribution points or AIA
     * (OCSP) in the certificate
     */
    @EnumValidator(enumClazz = Level.class)
    private String revocationInfoAccessPresent;

    /**
     * Checks if a revocation data is found in the signature or
     * externally for this certificate
     */
    @EnumValidator(enumClazz = Level.class)
    private String revocationDataAvailable;

    /**
     * Checks if an acceptable revocation data has been
     * found for this certificate
     */
    @EnumValidator(enumClazz = Level.class)
    private String acceptableRevocationDataFound;

    /**
     * Checks the presence of the NextUpdate value for a CRL
     */
    @EnumValidator(enumClazz = Level.class)
    private String crlNextUpdatePresent;

    /**
     * Checks the presence of the NextUpdate value for an OCSP response
     */
    @EnumValidator(enumClazz = Level.class)
    private String ocspNextUpdatePresent;

    /**
     * Checks the freshness of the revocation data against the
     * defined time constraint (whether the revocation data has
     * been issued after the control time minus the defined time)
     */
    private TimeConstraintDTO revocationFreshness;

    /**
     * Defines whether the revocation freshness shall be checked against
     * the difference between thisUpdate and nextUpdate times, in case
     * when the RevocationFreshness constraint is not defined.
     */
    @EnumValidator(enumClazz = Level.class)
    private String revocationFreshnessNextUpdate;

    /**
     * Checks if the certificate's key usages are in the acceptable values list
     */
    @MultiValuesEnumValidator(enumClazz = KeyUsageBit.class)
    private MultiValuesConstraintDTO keyUsage;

    /**
     * Checks if the certificate's extended key usages are in
     * the acceptable values list
     */
    @MultiValuesEnumValidator(enumClazz = KeyUsageBit.class)
    private MultiValuesConstraintDTO extendedKeyUsage;

    /**
     * Checks if the certificate's surname is in the acceptable values list
     */
    private MultiValuesConstraintDTO surname;

    /**
     * Checks if the certificate's given name is in the acceptable values list
     */
    private MultiValuesConstraintDTO givenName;

    /**
     * Checks if the certificate's common name is in the acceptable values list
     */
    private MultiValuesConstraintDTO commonName;

    /**
     * Checks if the certificate's pseudonym is in the acceptable values list
     */
    private MultiValuesConstraintDTO pseudonym;

    /**
     * Checks if the certificate's organization unit is in the acceptable values list
     */
    private MultiValuesConstraintDTO organizationUnit;

    /**
     * Checks if the certificate's organization name is in the acceptable values list
     */
    private MultiValuesConstraintDTO organizationName;

    /**
     * Checks if the certificate's country is in the acceptable values list
     */
    private MultiValuesConstraintDTO country;

    /**
     * Checks the presence of the serial number for the current certificate
     */
    @EnumValidator(enumClazz = Level.class)
    private String serialNumberPresent;

    /**
     * Checks if the certificate is not revoked (with a revocation date and status
     * different than certificateHold)
     */
    @EnumValidator(enumClazz = Level.class)
    private String notRevoked;

    /**
     * Checks if the certificate is not with a status certificateHold
     */
    @EnumValidator(enumClazz = Level.class)
    private String notOnHold;

    /**
     * Checks if the validation time is in the validity range of the
     * certificate of the issuer of the revocation information
     */
    @EnumValidator(enumClazz = Level.class)
    private String revocationIssuerNotExpired;

    /**
     * Checks if the certificate is a self-signed certificate
     */
    @EnumValidator(enumClazz = Level.class)
    private String selfSigned;

    /**
     * Checks if the certificate is NOT a self-signed certificate
     */
    @EnumValidator(enumClazz = Level.class)
    private String notSelfSigned;

    /**
     * Checks if the certificate policy id is in the acceptable values list
     */
    private MultiValuesConstraintDTO policyIds;

    /**
     * Checks if the certificate is issued for a natural person (no TL overrule)
     */
    @EnumValidator(enumClazz = Level.class)
    private String issuedToNaturalPerson;

    /**
     * Checks if the certificate is issued for a legal person (no TL overrule)
     */
    @EnumValidator(enumClazz = Level.class)
    private String issuedToLegalPerson;

    /**
     * Checks if the certificate's SemanticsIdentifier has a value from the
     * acceptable values list
     */
    private MultiValuesConstraintDTO semanticsIdentifier;

    /**
     * Checks if the certificate use a pseudonym
     */
    @EnumValidator(enumClazz = Level.class)
    private String usePseudonym;

    /**
     * Group of cryptographic checks to operate on the certificate
     */
    private CryptographicConstraintDTO cryptographicConstraint;
}
