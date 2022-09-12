package com.devisefutures.policyservice.bsl.services;

import org.apache.commons.io.FileExistsException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Stores the policy files
 */
@Service
public class PolicyStoreService {

    @Value("${policystoreservice.basePath}")
    private String policyDirPath;

    /**
     * The list of policy ids that already exists
     */
    private List<String> attributedIds;

    @PostConstruct
    private void init() throws FileExistsException, SecurityException {
        attributedIds = new ArrayList<>();
        File mainDir = new File(policyDirPath);
        if(mainDir.exists() && mainDir.isFile())
            throw new FileExistsException("A file with path '" + policyDirPath + "' already exists. Change the name on application.properties");
        if(mainDir.mkdir()){
            File[] files = mainDir.listFiles();
            assert files != null;
            for (File file : files)
                if(file.isFile())
                    attributedIds.add(file.getName());
        }
        else
            throw new AccessControlException("It was not possible to create main directory");
    }

    /**
     * Stores a xml policy base64 encoded string
     * @return UUID attributed to the string
     * stored policy file
     * @throws FileNotFoundException if the policy file cannot be created
     */
    public UUID storePolicy(String content) throws FileNotFoundException {
        UUID policyAttributedId;
        do {
            policyAttributedId = UUID.randomUUID();
        } while (attributedIds.contains(policyAttributedId.toString()));
        File newPolicy = new File(policyDirPath + policyAttributedId);
        PrintWriter out = new PrintWriter(new FileOutputStream(newPolicy));
        out.print(content);
        out.flush();
        out.close();
        return policyAttributedId;
    }
}
