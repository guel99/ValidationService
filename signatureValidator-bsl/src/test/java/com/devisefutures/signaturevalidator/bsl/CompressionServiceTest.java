package com.devisefutures.signaturevalidator.bsl;

import com.devisefutures.signaturevalidator.bsl.services.CompressionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

class CompressionServiceTest {

    private final Map<String, String> data =
            Map.of("file1.txt", "AKSJkugwygduyawdgi27te7awt682wagdakwhdasgadftwhfdawyte2542735uy3e8yduiuedgygduwy2iq7t6uwfqdtwy2",
                    "file2.txt", "KLHkuwyg983qyewhs2HKQY2FE62TEQWYGEQWGHDJAYGWDRSFUt782tqewdhqhd37tgD76r7dQUDF7dfUFyqfu6Y6DYFDYdydyqfsURYSqqut7");

    @InjectMocks
    private CompressionService compressionService;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCompression(){
        Assertions.assertDoesNotThrow(() -> {
            String compressedData = compressionService.compress(buildData());
            Map<String, byte[]> decompressedData = compressionService.decompress(compressedData);
            Assertions.assertEquals(decompressedData.size(), data.size());
            for(Map.Entry<String, String> mapEntry : data.entrySet()){
                String key = mapEntry.getKey();
                Assertions.assertTrue(decompressedData.containsKey(key));
                String value = new String(decompressedData.get(key));
                Assertions.assertEquals(data.get(key), value);
            }
        });
    }

    public Map<String, OutputStream> buildData(){

        Map<String, OutputStream> toCompressData = new HashMap<>();
        for(Map.Entry<String, String> entryMap : data.entrySet()){
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PrintWriter out = new PrintWriter(byteArrayOutputStream);
            out.print(entryMap.getValue());
            out.flush();
            out.close();
            toCompressData.put(entryMap.getKey(), byteArrayOutputStream);
        }
        return toCompressData;
    }
}
