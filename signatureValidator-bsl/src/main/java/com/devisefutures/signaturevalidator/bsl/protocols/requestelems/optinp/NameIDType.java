package com.devisefutures.signaturevalidator.bsl.protocols.requestelems.optinp;

import lombok.Data;

/**
 * The NameID component is used when an element serves to represent an entity by a string-valued name.
 * This component reflects the structure ‘NameID’ defined in the SAML2 specification [SAML2RWXSD]. This
 * section provides the definition required to support the DSS-X 2.0 multi-syntax approach.
 */
@Data
public class NameIDType {

    private String value;

    /**
     * The optional Format element MUST contain one instance of a URI. The
     * Format element represents the classification of string-based identifier information.
     */
    private String format;

    /**
     * The SPProvidedID element defines the alternative
     * identifier of the principal most recently set by
     * the service provider or affiliation, if any
     */
    private String provId;

    /**
     * The NameQualifier element contains the security or administrative domain that qualifies the
     * name. This attribute provides a means to federate names from disparate user stores without
     * collision.
     */
    private String nameQual;

    /**
     * The SPNameQualifier element further qualifies a name with the name of a service
     * provider or affiliation of providers. This attribute provides an additional means to federate names
     * on the basis of the relying party or parties
     */
    private String spNameQual;
}
