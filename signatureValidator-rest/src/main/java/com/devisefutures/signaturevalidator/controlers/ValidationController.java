package com.devisefutures.signaturevalidator.controlers;

import com.devisefutures.signaturevalidator.bsl.exceptions.MalformedRequestException;
import com.devisefutures.signaturevalidator.bsl.protocols.ValidationRequest;
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
    public ResponseEntity<?> validateSignature(@Valid @RequestBody ValidationRequest validationRequest) {
        try {
            return ResponseEntity.ok(validationService.validate(validationRequest));
        } catch (MalformedRequestException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (ValidationException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping(value = "/hello")
    public String hello(){
        return "Hello world";
    }

}
