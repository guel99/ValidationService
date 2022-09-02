package com.devisefutures.signaturevalidator.bsl.services;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Provides data compression facilities
 */
@Service
public class CompressionService {

    /**
     * Compresses a set of files
     * @param toCompressFiles Map where the key corresponds to
     * the filename and value corresponds to a file stream
     * @return The compressed content as a base64 encoded string
     * @throws IOException related with file handle issues
     */
    public String compress(Map<String, OutputStream> toCompressFiles) throws IOException {
        ByteArrayOutputStream zipContentStream = new ByteArrayOutputStream();
        ZipOutputStream zipOut = new ZipOutputStream(zipContentStream);

        for(Map.Entry<String, OutputStream> entry : toCompressFiles.entrySet()){
            ZipEntry newEntry = new ZipEntry(entry.getKey());
            zipOut.putNextEntry(newEntry);

            byte[] content = ((ByteArrayOutputStream) entry.getValue()).toByteArray();
            zipOut.write(content);
        }
        zipOut.close();

        return Base64.toBase64String(zipContentStream.toByteArray());
    }

    public Map<String, byte[]> decompress(String compressedB64Encoded) throws IOException {
        Map<String, byte[]> decompressedData = new HashMap<>();
        byte[] content = Base64.decode(compressedB64Encoded);
        ByteArrayInputStream zipContentStream = new ByteArrayInputStream(content);
        ZipInputStream zipIn = new ZipInputStream(zipContentStream);
        ZipEntry zipEntry;
        while((zipEntry = zipIn.getNextEntry()) != null){
            decompressedData.put(zipEntry.getName(), zipIn.readAllBytes());
        }
        return decompressedData;
    }
}