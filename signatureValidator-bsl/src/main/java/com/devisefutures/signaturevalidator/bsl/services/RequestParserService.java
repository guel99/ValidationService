package com.devisefutures.signaturevalidator.bsl.services;

import com.devisefutures.signaturevalidator.bsl.exceptions.MalformedRequestException;
import com.devisefutures.signaturevalidator.bsl.protocols.ValidationRequest;
import com.devisefutures.signaturevalidator.bsl.protocols.requestelems.DigestInfoType;
import com.devisefutures.signaturevalidator.bsl.protocols.requestelems.indocs.DocumentType;
import com.devisefutures.signaturevalidator.bsl.protocols.requestelems.indocs.InputDocuments;
import com.devisefutures.signaturevalidator.bsl.protocols.requestelems.optinp.AdditionalKeyInfoType;
import com.devisefutures.signaturevalidator.bsl.protocols.requestelems.optinp.OptionalInputs;
import com.devisefutures.signaturevalidator.bsl.protocols.requestelems.sigobj.SignatureObject;
import eu.europa.esig.dss.enumerations.DigestAlgorithm;
import eu.europa.esig.dss.model.DSSDocument;
import eu.europa.esig.dss.model.DigestDocument;
import eu.europa.esig.dss.model.InMemoryDocument;
import eu.europa.esig.dss.spi.DSSUtils;
import eu.europa.esig.dss.spi.tsl.TrustedListsCertificateSource;
import eu.europa.esig.dss.spi.x509.CertificateSource;
import eu.europa.esig.dss.validation.DocumentValidator;
import eu.europa.esig.dss.validation.SignedDocumentValidator;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service responsable for parse the request sent
 * for the client and provide the inputs to the ValidationService
 * to perform the validation process
 */
@Service
public class RequestParserService {

    static final Logger logger = LoggerFactory.getLogger(RequestParserService.class);

    /**
     * Extracts the input object to the Validation
     * Service performs the validation process
     * @param request The validation request
     * @return DocumentValidator
     */
    public DocumentValidator getToValidateDocuments(ValidationRequest request) throws MalformedRequestException {
        DocumentValidator documentValidator = null;
        InputDocuments inputDocuments = request.getInDocs();
        SignatureObject signatureObject = request.getSigObj();
        if(inputDocuments.getNumDoc() + inputDocuments.getNumDocHash() > 1) {
            // ficheiros assinados em simultâneo (todos em inDocs são original files) - CAdES ou XAdES obrigatoriamente.
            // a assinatura encontra-se no sigObj container
            if(signatureObject == null)
                throw new MalformedRequestException("No signature element for many signed files");
            try {
                documentValidator = SignedDocumentValidator.fromDocument(new InMemoryDocument(Base64.decode(signatureObject.getB64Sig().getVal())));
                processMultipleInputDocsCAdESOrXAdES(inputDocuments, documentValidator);
            } catch (UnsupportedOperationException e) { throw new MalformedRequestException(e.getMessage()); }
        }
        else if(inputDocuments.getNumDoc() + inputDocuments.getNumDocHash() == 1){
            if(inputDocuments.getNumDoc() == 1 && signatureObject == null){
                // Enveloped signature (PAdES or XAdES)
                try{
                    documentValidator = SignedDocumentValidator
                            .fromDocument(new InMemoryDocument(Base64.decode(inputDocuments.getDoc().get(0).getB64Data().getVal())));
                } catch (UnsupportedOperationException e) { throw new MalformedRequestException(e.getMessage()); }
            }
            else if(inputDocuments.getNumDoc() == 1){
                // Detached signature; sent original document (XAdES, CAdES, JAdES)
                if (signatureObject == null)
                    throw new MalformedRequestException("No mandatory signature element for a detached signature");
                try {
                    documentValidator = SignedDocumentValidator.fromDocument(new InMemoryDocument(Base64.decode(signatureObject.getB64Sig().getVal())));
                    processSingleDocumentDetached(inputDocuments, documentValidator);
                } catch (UnsupportedOperationException e) { throw new MalformedRequestException(e.getMessage()); }
            }
            else if(inputDocuments.getNumDocHash() == 1){
                // Detached signature; the signed document DIGEST was sent separated from the signature (XAdES, CAdES, JAdES)
                if(signatureObject == null)
                    throw new MalformedRequestException("No mandatory signature element for a detached signature");
                try {
                    documentValidator = SignedDocumentValidator.fromDocument(new InMemoryDocument(Base64.decode(signatureObject.getB64Sig().getVal())));
                    processSingleHashDocDetached(inputDocuments, documentValidator);
                } catch (UnsupportedOperationException e) { throw new MalformedRequestException(e.getMessage()); }
            }
        }
        else{
            // The document input container is empty: envelopping signature
            if(signatureObject == null)
                throw new MalformedRequestException("No mandatory signature element for an enveloping signature");
            try {
                documentValidator = SignedDocumentValidator.fromDocument(new InMemoryDocument(Base64.decode(signatureObject.getB64Sig().getVal())));
            } catch (UnsupportedOperationException e) { throw new MalformedRequestException(e.getMessage()); }
        }
        return documentValidator;
    }

    /**
     * Indicates the indication for report signing present
     * in the request message passed as parameter
     * @param request The ValidationRequest
     * @return The boolean value that indicates if the
     * report must or not be signed
     */
    public boolean signReport(ValidationRequest request){
        if(request.getOptInp() == null || request.getOptInp().getReturnValReport() == null)
            return false;
        return request.getOptInp().getReturnValReport().isSignIt();
    }

    /**
     * Gets the certificate source provided in
     * the request to use in the validation process
     * @param optInp The optional inputs container
     *               in the validation request message
     * @return The certificate source specified in the message
     */
    public CertificateSource getCertificateSource(OptionalInputs optInp){
        CertificateSource certificateSource = new TrustedListsCertificateSource();
        if(optInp.getAddKeyInfo() != null) {
            for(AdditionalKeyInfoType keyInfo : optInp.getAddKeyInfo()) {
                if(keyInfo.getCert() != null)
                    certificateSource.addCertificate(DSSUtils.loadCertificateFromBase64EncodedString(keyInfo.getCert()));
            }
        }
        return certificateSource;
    }

    /**
     * Tells if the optInp has specified a certificate source so apply
     * @param optInp The request message's option input container
     * @return True if there is specified a certificate source in the request
     */
    public boolean hasCertificateSourceInfo(OptionalInputs optInp){

        return (optInp != null && optInp.getAddKeyInfo() != null &&
                optInp.getAddKeyInfo().stream().filter(keyInfo -> keyInfo.getCert() != null).collect(Collectors.toList()).size() != 0);
    }

    /**
     * Creates an instance of DocumentValidator from
     * @param inputDocuments The container with all original documents
     * @param signatureDoc DocumentValidator only with the signature file
     * @return A DocumentValidator with the signature file and all signed files
     */
    private DocumentValidator processMultipleInputDocsCAdESOrXAdES(InputDocuments inputDocuments, DocumentValidator signatureDoc){
        List<DSSDocument> originalData = new ArrayList<>();
        inputDocuments.getDoc().forEach(doc -> {
            String contentB64 = doc.getB64Data().getVal();
            DSSDocument last = new InMemoryDocument(Base64.decode(contentB64));
            originalData.add(last);
        });
        inputDocuments.getDocHash().forEach(dochash -> {
            DigestInfoType digestInfo = dochash.getDi().get(0);
            String contentB64 = digestInfo.getValue();
            DigestDocument digestDocument = new DigestDocument();
            digestDocument.addDigest(DigestAlgorithm.valueOf(digestInfo.getAlg()), contentB64);
            originalData.add(digestDocument);
        });
        signatureDoc.setDetachedContents(originalData);
        return signatureDoc;
    }

    /**
     * Creates an instance of DocumentValidator from a detached
     * signature and its detached signed (hash doc) content
     * @param inputDocuments The container with the hash content
     * @param signatureDoc DocumentValidator only with the signature file
     * @return A DocumentValidator with the signature file and the signed content
     */
    private DocumentValidator processSingleHashDocDetached(InputDocuments inputDocuments, DocumentValidator signatureDoc){
        List<DSSDocument> detachedContent = new ArrayList<>();
        DigestDocument dssDocument = new DigestDocument();
        DigestInfoType digest = inputDocuments.getDocHash().get(0).getDi().get(0);
        dssDocument.addDigest(DigestAlgorithm.valueOf(digest.getAlg()), digest.getValue());
        detachedContent.add(dssDocument);
        signatureDoc.setDetachedContents(detachedContent);
        return signatureDoc;
    }

    /**
     * Creates an instance of DocumentValidator from a detached
     * signature and its detached signed (document) content
     * @param inputDocuments The container with the original document
     * @param signatureDoc DocumentValidator only with the signature file
     * @return A DocumentValidator with the signature file and the signed content
     */
    private DocumentValidator processSingleDocumentDetached(InputDocuments inputDocuments, DocumentValidator signatureDoc){
        List<DSSDocument> detachedContent = new ArrayList<>();
        DocumentType originalDoc = inputDocuments.getDoc().get(0);
        DSSDocument dssDocument = new InMemoryDocument(Base64.decode(originalDoc.getB64Data().getVal()));
        detachedContent.add(dssDocument);
        signatureDoc.setDetachedContents(detachedContent);
        return signatureDoc;
    }
}
