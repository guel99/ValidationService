package com.devisefutures.signaturevalidator.controlers;

import com.devisefutures.signaturevalidator.bsl.exceptions.MalformedRequestException;
import com.devisefutures.signaturevalidator.bsl.protocols.ValidationRequest;
import com.devisefutures.signaturevalidator.bsl.protocols.ValidationResponse;
import com.devisefutures.signaturevalidator.bsl.services.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;

@RestController
@RequestMapping(value = "/api")
public class ValidationController {

    @Autowired
    private ValidationService validationService;

    @PostMapping(value = "/validate", consumes = "application/json", produces = "application/json")
    @CrossOrigin(origins = "${webform.origin}")
    public ResponseEntity<ValidationResponse> validateSignature(@Valid @RequestBody ValidationRequest validationRequest) throws MalformedRequestException, ValidationException {
            return ResponseEntity.ok(validationService.validate(validationRequest));
    }

    @GetMapping(value = "/connection/test")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("The service is up!");
    }

}
