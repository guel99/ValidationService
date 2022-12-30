package com.devisefutures.policyservice.advice;

import com.devisefutures.policyservice.bsl.exception.SearchTokenNotFound;
import com.devisefutures.signaturevalidator.common.exceptions.InvalidPolicyCreationModeException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ JAXBException.class, IOException.class, SAXException.class, InvalidPolicyCreationModeException.class} )
    public ResponseEntity<Object> handleValidationError(Exception exc, WebRequest webRequest){
        return handleExceptionInternal(exc, ResponseEntity.badRequest().body(exc.getMessage()),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }

    @ExceptionHandler( SearchTokenNotFound.class )
    public ResponseEntity<Object> handleNotFoundErrors(Exception exc, WebRequest webRequest){
        return handleExceptionInternal(exc, ResponseEntity.status(HttpStatus.NOT_FOUND).body(exc.getMessage()),
                new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
    }
}
