package com.moodleeducation.commoncore.base;

import com.moodleeducation.commoncore.enums.ResultEnum;

public class BaseException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    /**
     * 异常码
     */
    protected int code;

    public BaseException() {
        super("系统异常");
        this.code = 99;
    }

    public BaseException(ResultEnum resultEnum) {
        super(resultEnum.getDesc());
        this.code = resultEnum.getCode();
    }

    public BaseException(String message) {
        super(message);
        this.code = 99;
    }

    public BaseException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
