package com.devisefutures.policyservice.bsl.advice;

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

    @ExceptionHandler({ JAXBException.class, IOException.class, SAXException.class } )
    public ResponseEntity<Object> handleValidationError(Exception exc, WebRequest webRequest){
        return handleExceptionInternal(exc, ResponseEntity.badRequest().body(exc.getMessage()),
                new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
    }
}
