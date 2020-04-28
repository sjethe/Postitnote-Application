package com.backendexample.postitnotes.exception;

public class PostitnoteException extends RuntimeException {

    public PostitnoteException(String message){
        super(message);
    }

    public PostitnoteException(String message, Throwable t) {
        super(message, t);
    }


}
