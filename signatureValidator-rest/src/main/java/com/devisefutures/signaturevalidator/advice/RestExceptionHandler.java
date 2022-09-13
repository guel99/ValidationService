package com.devisefutures.signaturevalidator.advice;

import com.devisefutures.signaturevalidator.bsl.exceptions.MalformedRequestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.xml.bind.ValidationException;
import java.io.IOException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ ValidationException.class, MalformedRequestException.class, IOException.class } )
    public ResponseEntity<Object> handleValidationError(Exception exc, WebRequest webRequest){
        return handleExceptionInternal(exc, ResponseEntity.badRequest().body(exc.getMessage()),
                new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
    }
}
