package com.shuashua.buss.Exception;

import net.gy.SwiftFrameWork.MVVM.Exception.HttpServiceException;

import org.apache.http.conn.ConnectTimeoutException;

import java.net.SocketTimeoutException;

/**
 * Created by gy939 on 2016/9/13.
 */
public class ExceptionHandler implements IExceptionHandler{
    @Override
    public String obtainMsg(Throwable throwable) {
        String msg = throwable.getMessage();
        if (throwable instanceof HttpServiceException){

        }else if (throwable instanceof ConnectTimeoutException){

        }else if (throwable instanceof SocketTimeoutException){

        }else if (throwable instanceof Exception){

        }
        return msg;
    }
}
