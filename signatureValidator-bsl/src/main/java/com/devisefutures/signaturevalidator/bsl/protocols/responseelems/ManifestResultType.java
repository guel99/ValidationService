package com.devisefutures.signaturevalidator.bsl.protocols.responseelems;

import com.devisefutures.signaturevalidator.bsl.annotations.EnumValidator;
import com.devisefutures.signaturevalidator.bsl.protocols.requestelems.NsPrefixMappingType;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class ManifestResultType {

    /**
     * This element identifies the manifest reference, in the XML signature, to which this result
     * pertains
     */
    @NonNull
    private String xPath;

    /**
     * The Status element MUST contain one instance of a URI. » [DSS-4.3.24-2] Its value is
     * limited to an item of the following set:
     * urn:oasis:names:tc:dss:1.0:manifeststatus:Valid
     * urn:oasis:names:tc:dss:1.0:manifeststatus:Invalid
     * This element indicates the manifest validation outcome
     */
    @NonNull
    @EnumValidator(enumClazz = ManifestResultTypeStatus.class)
    private String status;

    /**
     * Defines the mapping of namespace URIs to namespace prefixes
     */
    private List<NsPrefixMappingType> nsDecl;
}
