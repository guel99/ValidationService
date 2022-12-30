package com.devisefutures.policyservice.bsl.exception;

public class SearchTokenNotFound extends RuntimeException{

    public SearchTokenNotFound(){
        super();
    }

    public SearchTokenNotFound(String message){
        super(message);
    }
}
