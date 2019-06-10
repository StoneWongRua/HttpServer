package com.tong.exception;

/**
 * @Author stonewong
 * @Description TODO
 * @Date 2019/6/10 17:34
 * @Param
 * @return
 **/
public class TongException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public TongException() {
        // TODO Auto-generated constructor stub
    }

    public TongException(String msg) {
        super(msg);
    }
}
