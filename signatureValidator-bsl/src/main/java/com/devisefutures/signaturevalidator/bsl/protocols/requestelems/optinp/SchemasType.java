package com.devisefutures.signaturevalidator.bsl.protocols.requestelems.optinp;

import com.devisefutures.signaturevalidator.bsl.protocols.requestelems.indocs.DocumentType;
import lombok.Data;

import java.util.List;

/**
 * The Schemas component provides an in-band mechanism for communicating XML schemas required for
 * validating an XML document.
 */
@Data
public class SchemasType {

    private List<DocumentType> schema;
}
