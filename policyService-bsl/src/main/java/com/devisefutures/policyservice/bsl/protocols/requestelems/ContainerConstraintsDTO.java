package com.devisefutures.policyservice.bsl.protocols.requestelems;

import com.devisefutures.signaturevalidator.common.annotations.EnumValidator;
import com.devisefutures.signaturevalidator.common.annotations.ListStrEnumValidator;
import eu.europa.esig.dss.enumerations.ASiCContainerType;
import eu.europa.esig.dss.policy.jaxb.Level;
import lombok.Data;

import java.util.List;

@Data
public class ContainerConstraintsDTO {

    /**
     * Default: Serão aceites quais tipos de container: ASiC-S e ASiC-E.
     */
    @ListStrEnumValidator(enumClazz = ASiCContainerType.class)
    private List<String> acceptableContainerTypes;

    /**
     * Para além dos mimetypes que definem o media type dos containers,
     * application/vnd.etsi.asic-s+zip e application/vnd.etsi.asic-e+zip,
     * a entidade poder adicionar qualquer outro mimetype que caracterize
     * o conteúdos do(s) objeto(s) a ser(em) assinado(s), tal como se
     * encontra especificado no ETSI EN 319 162-1 V1.1.1
     *
     * Default: Serão apenas considerados os media types já mencionados.
     */
    private List<String> additionalAcceptableMimeTypeFileContent;

    @EnumValidator(enumClazz = Level.class)
    private String allFilesSIgned;
}
