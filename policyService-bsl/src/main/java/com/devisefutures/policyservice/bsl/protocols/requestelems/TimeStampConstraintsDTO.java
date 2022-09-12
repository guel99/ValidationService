package com.devisefutures.policyservice.bsl.protocols.requestelems;

import com.devisefutures.signaturevalidator.common.annotations.EnumValidator;
import eu.europa.esig.dss.policy.jaxb.Level;
import eu.europa.esig.dss.policy.jaxb.LevelConstraint;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;

@Data
public class TimeStampConstraintsDTO {

    /**
     * Checks the delay between the best-signature-time
     * and the claimed signing time
     */
    private TimeConstraintDTO timestampDelay;

    /**
     * Checks if the revocation date is after the best-signature-time
     */
    @EnumValidator(enumClazz = Level.class)
    private String revocationTimeAgainstBestSignatureTime;

    /**
     * Checks if the best-signature-time is before the expiration of the signing certificate
     */
    @EnumValidator(enumClazz = Level.class)
    protected String bestSignatureTimeBeforeExpirationDateOfSigningCertificate;

    /**
     * Checks the timestamps ordering (archival timestamp is not before a content-timestamp,...)
     */
    @EnumValidator(enumClazz = Level.class)
    protected String coherence;

    /**
     * Checks if a TSTInfo.tsa field is present for a timestamp
     */
    @EnumValidator(enumClazz = Level.class)
    private String tsaGeneralNamePresent;

    /**
     * Checks if a TSTInfo.tsa field's value matches the timestamp's
     * issuer distinguishing name when present
     */
    @EnumValidator(enumClazz = Level.class)
    private String tsaGeneralNameContentMatch;

    /**
     * Checks if a TSTInfo.tsa field's value and order matches the
     * timestamp's issuer distinguishing name when present
     */
    @EnumValidator(enumClazz = Level.class)
    private String tsaGeneralNameOrderMatch;

    /**
     * Group of common checks on this token signature
     */
    private BasicSignatureConstraintsDTO basicSignatureConstraints;

    /**
     * Group of checks on the signed attributes/properties
     */
    private SignedAttributesConstraintsDTO signedAttributesConstraints;
}
