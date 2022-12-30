package com.devisefutures.policyservice.bsl.protocols.requestelems;

import com.devisefutures.policyservice.bsl.protocols.annotations.MultiValuesEnumValidator;
import com.devisefutures.signaturevalidator.common.annotations.EnumValidator;
import eu.europa.esig.dss.enumerations.ASiCContainerType;
import eu.europa.esig.dss.policy.jaxb.Level;
import lombok.Data;

@Data
public class ContainerConstraintsDTO {

    /**
     * Default: Serão aceites quais tipos de container: ASiC-S e ASiC-E.
     */
    @MultiValuesEnumValidator(enumClazz = ASiCContainerType.class)
    private MultiValuesConstraintDTO acceptableContainerTypes;

    @EnumValidator(enumClazz = Level.class)
    private String zipCommentPresent;

    private MultiValuesConstraintDTO acceptableZipComment;

    @EnumValidator(enumClazz = Level.class)
    private String mimeTypeFilePresent;

    private MultiValuesConstraintDTO acceptableMimeTypeFileContent;

    @EnumValidator(enumClazz = Level.class)
    private String manifestFilePresent;

    @EnumValidator(enumClazz = Level.class)
    private String signedFilesPresent;

    @EnumValidator(enumClazz = Level.class)
    private String allFilesSigned;
}
