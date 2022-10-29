package com.devisefutures.functionaltests;

import eu.europa.esig.dss.enumerations.DigestAlgorithm;
import eu.europa.esig.dss.enumerations.SignatureLevel;
import eu.europa.esig.dss.enumerations.SignaturePackaging;
import eu.europa.esig.dss.model.DSSDocument;
import eu.europa.esig.dss.model.InMemoryDocument;
import eu.europa.esig.dss.model.SignatureValue;
import eu.europa.esig.dss.model.ToBeSigned;
import eu.europa.esig.dss.token.DSSPrivateKeyEntry;
import eu.europa.esig.dss.token.Pkcs12SignatureToken;
import eu.europa.esig.dss.validation.CommonCertificateVerifier;
import eu.europa.esig.dss.xades.XAdESSignatureParameters;
import eu.europa.esig.dss.xades.signature.XAdESService;

import javax.xml.crypto.dsig.CanonicalizationMethod;
import java.io.*;
import java.security.KeyStore;

public class SigningEtsiReportTest {

    /**
     * Relative path where the signing keystore is stored
     */
    static final String KEYSTORE_FILE = "sign_cert/cert.p12";

    /**
     * Absolute path where the etsi report to be signed is stored
     */
    static final String ETSI_REPORT_FILE = "C:\\Users\\14544593\\Documents\\Thesis\\Tovalidate\\ESIG_plugtests_20220608_165914\\ESIG-P\\PT_DEV\\Verification_of_AT_RIT_Signature-P-AT_RIT-1.xml";

    /**
     * Absolute path where the signed etsi report will be stored after performing the signing process
     */
    static final String ETSI_REPORT_FILE_SIGNED = "C:\\Users\\14544593\\Documents\\Thesis\\Tovalidate\\ESIG_plugtests_20220608_165914\\ESIG-P\\PT_DEV\\Verification_of_AT_RIT_Signature-P-AT_RIT-1_signed.xml";

    /**
     * Loads the to be signed data (the etsi validation report)
     * @return
     */
    private static DSSDocument loadEtsiReport() {
        try {
            return new InMemoryDocument(new FileInputStream(ETSI_REPORT_FILE));
        }catch (FileNotFoundException e){
            return null;
        }
    }

    /**
     * Signes the etsi validation report
     * @return
     */
    private static void signDocument() throws IOException {
        XAdESService xAdESService = new XAdESService(new CommonCertificateVerifier());

        XAdESSignatureParameters parameters = new XAdESSignatureParameters();

        // Create a XAdES-BASELINE-B signature
        parameters.setSignatureLevel(SignatureLevel.XAdES_BASELINE_B);
        parameters.setSignaturePackaging(SignaturePackaging.ENVELOPED);
        parameters.setDigestAlgorithm(DigestAlgorithm.SHA512);

        // Dar uma vista de olhos nos atributos da assinatura que possa fazer sentido serem adicionados à assinatura (section 4.6) ...

        // Canonicalization parameters:
        parameters.setSignedPropertiesCanonicalizationMethod(CanonicalizationMethod.EXCLUSIVE);
        parameters.setSignedInfoCanonicalizationMethod(CanonicalizationMethod.EXCLUSIVE);
        parameters.setKeyInfoCanonicalizationMethod(CanonicalizationMethod.EXCLUSIVE);
        parameters.setSignKeyInfo(true);

        try(Pkcs12SignatureToken signingToken = new Pkcs12SignatureToken(KEYSTORE_FILE,
                new KeyStore.PasswordProtection("validationService".toCharArray()))) {

            DSSPrivateKeyEntry privateKey = signingToken.getKeys().get(0);
            parameters.setSigningCertificate(privateKey.getCertificate());
            parameters.setCertificateChain(privateKey.getCertificateChain());

            DSSDocument toBeSignedDocument = loadEtsiReport();
            ToBeSigned toBeSignedReport = xAdESService.getDataToSign(loadEtsiReport(), parameters);

            SignatureValue signatureValue = signingToken.sign(toBeSignedReport, DigestAlgorithm.SHA512, privateKey);

            DSSDocument signedDocument = xAdESService.signDocument(toBeSignedDocument, parameters, signatureValue);

            signedDocument.writeTo(new FileOutputStream(ETSI_REPORT_FILE_SIGNED));
        }
    }

    public static void main(String[] args) {
        try{
            signDocument();
        } catch (IOException e){
            System.err.println("Some error occured signing this file: '" + ETSI_REPORT_FILE + "'");
        }
    }
}
