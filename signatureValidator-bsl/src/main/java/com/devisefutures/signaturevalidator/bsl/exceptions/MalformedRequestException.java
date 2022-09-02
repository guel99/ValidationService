package com.devisefutures.signaturevalidator.bsl.exceptions;

public class MalformedRequestException extends Exception{

    public MalformedRequestException(){
        super();
    }

    public MalformedRequestException(String message){
        super(message);
    }
}
