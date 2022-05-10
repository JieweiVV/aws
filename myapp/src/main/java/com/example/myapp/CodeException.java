package com.example.myapp;

import com.example.myapp.Trycatch.ErrorCode;

public class CodeException extends TmpException {

    private String source;
    private ErrorCode code;
    public CodeException(ErrorCode code, String source) {
        this.code = code;
        this.source = source;
    }

    public CodeException(ErrorCode code, String source, Throwable ex) {
        this(code, source, null, ex);
    }

    public CodeException(ErrorCode code, String source, String msg, Throwable ex) {
        super(msg, ex);
        this.code = code;
        this.source = source;
    }

    public ErrorCode getCode() {
        return this.code;
    }

    public String getSource() {
        return this.source;
    }
}
