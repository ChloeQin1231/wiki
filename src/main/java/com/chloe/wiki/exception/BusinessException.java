package com.chloe.wiki.exception;

public class BusinessException extends RuntimeException{

    private BusinessExceptionCode code;

    public BusinessException (BusinessExceptionCode code) {
        super(code.getDesc());
        this.code = code;
    }

    public BusinessExceptionCode getCode() {
        return code;
    }

    public void setCode(BusinessExceptionCode code) {
        this.code = code;
    }

    /**
     * Improve performance by not writing stack information
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}