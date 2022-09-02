package com.devisefutures.signaturevalidator.bsl.exceptions;

public class ValidationException extends Exception{

    public ValidationException(){
        super();
    }

    public ValidationException(String message){
        super(message);
    }
}
