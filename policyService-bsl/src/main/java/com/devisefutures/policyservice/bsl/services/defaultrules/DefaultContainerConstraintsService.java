package com.devisefutures.policyservice.bsl.services.defaultrules;

import eu.europa.esig.dss.policy.jaxb.ContainerConstraints;
import eu.europa.esig.dss.policy.jaxb.Level;
import eu.europa.esig.dss.policy.jaxb.LevelConstraint;
import eu.europa.esig.dss.policy.jaxb.MultiValuesConstraint;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultContainerConstraintsService {

    /**
     * Default acceptable Container types
     */
    private static final List<String> DEFAULT_ACCEPTABLE_CONTAINERS = List.of("ASiC-E", "ASiC-S");
    private static final Level DEFAULT_ACCEPTABLE_CONTAINERS_LEVEL = Level.FAIL;

    /**
     * Default acceptable mime type file content
     */
    private static final List<String> DEFAULT_ACCEPTABLE_MIME_TYPE_FILE_CONTENT = List.of("application/vnd.etsi.asic-e+zip", "application/vnd.etsi.asic-s+zip");
    private static final Level DEFAULT_ACCEPTABLE_MIME_TYPE_FILE_CONTENT_LEVEL = Level.WARN;

    /**
     * Default value for all files signed constraint
     */
    private static final Level DEFAULT_ALL_FILES_SIGNED = Level.FAIL;

    /**
     * values for some level constraints non editable by front end users
     */
    private static final Level MIME_TYPE_FILE_PRESENT = Level.INFORM;
    private static final Level ZIP_COMMENT_PRESENT = Level.INFORM;
    private static final Level MANIFEST_FILE_PRESENT = Level.FAIL;
    private static final Level SIGNED_FILES_PRESENT = Level.FAIL;

    /**
     * Applies the default rules for non-editable/empty fields
     * @param containerConstraints The containerConstraints
     * @return The container constraints with the default parameters
     */
    public ContainerConstraints setDefaultRules(ContainerConstraints containerConstraints){
        if(containerConstraints == null){
            containerConstraints = new ContainerConstraints();
        }
        setDefaultAcceptableContainers(containerConstraints);
        setDefaultAcceptableMimeTypeFileContent(containerConstraints);
        setDefaultAllFilesSigned(containerConstraints);
        setMimeTypeFilePresent(containerConstraints);
        setZipCommentPresent(containerConstraints);
        setAcceptableZipComment(containerConstraints);
        setManifestFilePresent(containerConstraints);
        setSignedFilesPresent(containerConstraints);

        return containerConstraints;
    }

    /**
     * In default, all container types (ASiC-S and ASiC-E are allowed)
     * @param containerConstraints Container constraints
     */
    private void setDefaultAcceptableContainers(ContainerConstraints containerConstraints){
        MultiValuesConstraint acceptableContainerTypes = containerConstraints.getAcceptableContainerTypes();
        if(acceptableContainerTypes == null){
            acceptableContainerTypes = new MultiValuesConstraint();
            acceptableContainerTypes.getId().addAll(DEFAULT_ACCEPTABLE_CONTAINERS);
            acceptableContainerTypes.setLevel(DEFAULT_ACCEPTABLE_CONTAINERS_LEVEL);
            containerConstraints.setAcceptableContainerTypes(acceptableContainerTypes);
        }
    }

    /**
     * In default, "application/vnd.etsi.asic-e+zip" and "application/vnd.etsi.asic-s+zip" are allowed (WARN otherwise)
     * @param containerConstraints The container constraints
     */
    private void setDefaultAcceptableMimeTypeFileContent(ContainerConstraints containerConstraints){
        MultiValuesConstraint acceptableMimeTypeFileContent = containerConstraints.getAcceptableMimeTypeFileContent();
        if(acceptableMimeTypeFileContent == null){
            acceptableMimeTypeFileContent = new MultiValuesConstraint();
            acceptableMimeTypeFileContent.getId().addAll(DEFAULT_ACCEPTABLE_MIME_TYPE_FILE_CONTENT);
            acceptableMimeTypeFileContent.setLevel(DEFAULT_ACCEPTABLE_MIME_TYPE_FILE_CONTENT_LEVEL);
            containerConstraints.setAcceptableMimeTypeFileContent(acceptableMimeTypeFileContent);
        }
        else{
            // we add them even if exists other allowed values
            acceptableMimeTypeFileContent.getId().addAll(DEFAULT_ACCEPTABLE_MIME_TYPE_FILE_CONTENT);
            acceptableMimeTypeFileContent.setLevel(DEFAULT_ACCEPTABLE_MIME_TYPE_FILE_CONTENT_LEVEL);
        }
    }

    /**
     * In default, all files in the container must be signed
     * @param containerConstraints The container constraints
     */
    private void setDefaultAllFilesSigned(ContainerConstraints containerConstraints){
        LevelConstraint allFilesSigned = containerConstraints.getAllFilesSigned();
        if(allFilesSigned == null){
            allFilesSigned = new LevelConstraint();
            allFilesSigned.setLevel(DEFAULT_ALL_FILES_SIGNED);
            containerConstraints.setAllFilesSigned(allFilesSigned);
        }
    }

    /**
     * Sets the value for mimeTypeFileContent
     * @param containerConstraints The container constraints
     */
    private void setMimeTypeFilePresent(ContainerConstraints containerConstraints){
        LevelConstraint mimeTypeFilePresent = new LevelConstraint();
        mimeTypeFilePresent.setLevel(MIME_TYPE_FILE_PRESENT);
        containerConstraints.setMimeTypeFilePresent(mimeTypeFilePresent);
    }

    /**
     * Sets the value for zipCommentPresent constraint
     * @param containerConstraints The container constraints
     */
    private void setZipCommentPresent(ContainerConstraints containerConstraints){
        LevelConstraint zipCommentPresent = new LevelConstraint();
        zipCommentPresent.setLevel(ZIP_COMMENT_PRESENT);
        containerConstraints.setZipCommentPresent(zipCommentPresent);
    }

    /**
     * Sets the value for acceptableZipComment constraint
     * @param containerConstraints The container constraints
     */
    private void setAcceptableZipComment(ContainerConstraints containerConstraints){
        MultiValuesConstraint acceptableZipComment = new MultiValuesConstraint();
        acceptableZipComment.getId().addAll(containerConstraints.getAcceptableMimeTypeFileContent().getId().stream().map(str -> "mimetype=" +
                str).collect(Collectors.toList()));
        acceptableZipComment.setLevel(Level.INFORM);
    }

    /**
     * Sets the value for manifestFilePresent constraint
     * @param containerConstraints The container constraints
     */
    private void setManifestFilePresent(ContainerConstraints containerConstraints){
        LevelConstraint manifestFilePresent = new LevelConstraint();
        manifestFilePresent.setLevel(MANIFEST_FILE_PRESENT);
        containerConstraints.setManifestFilePresent(manifestFilePresent);
    }

    /**
     * Sets value for signedFilesPresent
     * @param containerConstraints The container constraints
     */
    private void setSignedFilesPresent(ContainerConstraints containerConstraints){
        LevelConstraint signedFilesPresent = new LevelConstraint();
        signedFilesPresent.setLevel(SIGNED_FILES_PRESENT);
        containerConstraints.setSignedFilesPresent(signedFilesPresent);
    }
}
