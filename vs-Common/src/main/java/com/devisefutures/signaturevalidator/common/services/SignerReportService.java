package com.devisefutures.signaturevalidator.common.services;

import eu.europa.esig.dss.enumerations.DigestAlgorithm;
import eu.europa.esig.dss.enumerations.SignatureLevel;
import eu.europa.esig.dss.enumerations.SignaturePackaging;
import eu.europa.esig.dss.model.DSSDocument;
import eu.europa.esig.dss.model.SignatureValue;
import eu.europa.esig.dss.model.ToBeSigned;
import eu.europa.esig.dss.token.DSSPrivateKeyEntry;
import eu.europa.esig.dss.token.Pkcs12SignatureToken;
import eu.europa.esig.dss.validation.CommonCertificateVerifier;
import eu.europa.esig.dss.xades.XAdESSignatureParameters;
import eu.europa.esig.dss.xades.signature.XAdESService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.crypto.dsig.CanonicalizationMethod;
import java.io.IOException;
import java.security.KeyStore;

@Service
public class SignerReportService {

    @Value("${signer.keystore.filepath}")
    private String keyStoreFilePath;

    @Value("${signer.keystore.secret}")
    private String keyStorePwd;

    /**
     * Sign the given document using enveloped XAdES
     * @param toBeSignedDocument The xml document to be signed
     * @return The signed document with the embedded signature
     * @throws IOException When occurs some error related with the keystore handle
     */
    public DSSDocument signDocument(DSSDocument toBeSignedDocument) throws IOException {

        if(keyStoreFilePath == null)
            throw new IllegalArgumentException("The property 'signer.keystore.filepath' must be defined in the application.properties spring boot app.");

        XAdESService xAdESService = new XAdESService(new CommonCertificateVerifier());

        XAdESSignatureParameters parameters = new XAdESSignatureParameters();

        // Create a XAdES-BASELINE-B signatureW
        parameters.setSignatureLevel(SignatureLevel.XAdES_BASELINE_B);
        parameters.setSignaturePackaging(SignaturePackaging.ENVELOPED);
        parameters.setDigestAlgorithm(DigestAlgorithm.SHA512);

        // Dar uma vista de olhos nos atributos da assinatura que possa fazer sentido serem adicionados à assinatura (section 4.6) ...

        // Canonicalization parameters:
        parameters.setSignedPropertiesCanonicalizationMethod(CanonicalizationMethod.EXCLUSIVE);
        parameters.setSignedInfoCanonicalizationMethod(CanonicalizationMethod.EXCLUSIVE);
        parameters.setKeyInfoCanonicalizationMethod(CanonicalizationMethod.EXCLUSIVE);
        parameters.setSignKeyInfo(true);

        try(Pkcs12SignatureToken signingToken = new Pkcs12SignatureToken(keyStoreFilePath,
                new KeyStore.PasswordProtection(keyStorePwd.toCharArray()))) {

            DSSPrivateKeyEntry privateKey = signingToken.getKeys().get(0);
            parameters.setSigningCertificate(privateKey.getCertificate());
            parameters.setCertificateChain(privateKey.getCertificateChain());

            ToBeSigned toBeSignedReport = xAdESService.getDataToSign(toBeSignedDocument, parameters);

            SignatureValue signatureValue = signingToken.sign(toBeSignedReport, DigestAlgorithm.SHA512, privateKey);

            return xAdESService.signDocument(toBeSignedDocument, parameters, signatureValue);
        }
    }
}
