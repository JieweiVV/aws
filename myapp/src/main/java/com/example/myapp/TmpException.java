package com.example.myapp;
public abstract class TmpException extends Exception {
    public TmpException() {

    }
    
    public TmpException(String msg) {
        super(msg);
    }

    public TmpException(Throwable throwable) {
        super(throwable);
    }

    public TmpException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
