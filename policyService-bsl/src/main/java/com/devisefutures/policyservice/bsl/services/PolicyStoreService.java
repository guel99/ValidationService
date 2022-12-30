package com.devisefutures.policyservice.bsl.services;

import com.devisefutures.policyservice.bsl.exception.SearchTokenNotFound;
import com.devisefutures.policyservice.bsl.protocols.RemotePolicyDTO;
import com.devisefutures.policyservice.bsl.protocols.ValidationPolicyCreationResponse;
import com.devisefutures.policyservice.bsl.protocols.ValidationPolicyGetResponse;
import org.apache.commons.io.FileExistsException;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.AccessControlException;
import java.time.LocalDateTime;
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
        if(mainDir.exists() || mainDir.mkdir()){
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
    public ValidationPolicyCreationResponse storePolicy(String content) throws FileNotFoundException {
        ValidationPolicyCreationResponse validationPolicyResponse = new ValidationPolicyCreationResponse();
        UUID policyAttributedId;
        do {
            policyAttributedId = UUID.randomUUID();
        } while (attributedIds.contains(policyAttributedId.toString()));
        validationPolicyResponse.setPolicyId(policyAttributedId.toString());
        validationPolicyResponse.setTimestamp(LocalDateTime.now().toString());
        File newPolicy = new File(policyDirPath + policyAttributedId);
        PrintWriter out = new PrintWriter(new FileOutputStream(newPolicy));
        out.print(content);
        out.flush();
        out.close();
        attributedIds.add(policyAttributedId.toString());
        return validationPolicyResponse;
    }

    /**
     * Gets a policy with a specific uuid
     * @param id The policy attributed uuid on the policy creation
     * @return The ValidationPolicyGetResponse
     * @throws IOException when some io error occurs
     * @throws FileNotFoundException when this id is not attributed
     */
    public ValidationPolicyGetResponse getPolicy(String id) throws IOException {
        if(attributedIds.contains(id)){
            File policyFile = new File(policyDirPath + id);
            if(policyFile.exists() && policyFile.isFile()){
                ValidationPolicyGetResponse validationPolicyGetResponse = new ValidationPolicyGetResponse();
                validationPolicyGetResponse.setPolicyId(id);
                BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(policyFile)));
                String b64Policy = in.readLine();
                in.close();
                validationPolicyGetResponse.setPolicyXmlB64(b64Policy);
                return validationPolicyGetResponse;
            }
            else
                throw new FileNotFoundException("The specified policy does not exist");
        }
        else
            throw new FileNotFoundException("The specified policy does not exist");
    }

    public String getPolicyXML(String id) throws IOException {
        if(attributedIds.contains(id)){
            File policyFile = new File(policyDirPath + id);
            if(policyFile.exists() && policyFile.isFile()){
                BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(policyFile)));
                String b64Policy = in.readLine();
                in.close();
                return new String(Base64.decode(b64Policy), StandardCharsets.UTF_8);
            }
            else
                throw new FileNotFoundException("The specified policy does not exist");
        }
        else
            throw new FileNotFoundException("The specified policy does not exist");
    }

    /**
     * Used to find a list of policy ids that contains the substring passed as parameter
     * @param policyIdSubStr The substring used to check
     * @return The list of strings that match the one passed as parameter
     */
    public List<RemotePolicyDTO> search(String policyIdSubStr){
        List<RemotePolicyDTO> ids = new ArrayList<>();
        for(String id : this.attributedIds){
            if(id.contains(policyIdSubStr)) {
                // Note: the source value is only setted by the rest-controller
                RemotePolicyDTO remotePolicyDTO = new RemotePolicyDTO(id,"");
                ids.add(remotePolicyDTO);
            }
        }
        if(ids.isEmpty())
            throw new SearchTokenNotFound("Policy not found for the search token '" + policyIdSubStr + "'");
        return ids;
    }
}
