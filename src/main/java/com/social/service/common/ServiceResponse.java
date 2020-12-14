package com.social.service.common;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServiceResponse<T> implements Serializable {
    private int status;
    private String msg;
    private  T data;

    public ServiceResponse(int status) {
        this.status = status;
    }

    public ServiceResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ServiceResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }

    public ServiceResponse(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    /**
     * 判断返回消息是否正确，并且该方法在返回参数序列化的时候无效
     */
    @JsonIgnore
    public boolean isSuccess() {
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public static <T> ServiceResponse<T> createBySuccess(){
        return new ServiceResponse<T>(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getDesc());
    }

    public static <T> ServiceResponse<T> createBySuccessMessage(String msg){
        return new ServiceResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }

    public static <T> ServiceResponse<T> createBySuccessData(T data){
        return new ServiceResponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }

    public static <T> ServiceResponse<T> createBySuccessMessageAndData(String msg, T data){
        return new ServiceResponse<T>(ResponseCode.SUCCESS.getCode(),msg, data);
    }

    public static <T> ServiceResponse<T> createByError(){
        return new ServiceResponse<T>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
    }

    public static <T> ServiceResponse<T> createByErrorMessage(String msg){
        return new ServiceResponse<T>(ResponseCode.ERROR.getCode(), msg);
    }

    public static <T> ServiceResponse<T> createByErrorResponseCode(int errorCode, String msg){
        return new ServiceResponse<T>(errorCode, msg);
    }

    public static <T> ServiceResponse<T> createByIllegalArgument(){
        return new ServiceResponse<T>(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
    }

    public static <T> ServiceResponse<T> createByIllegalArgument(String errorMsg){
        return new ServiceResponse<T>(ResponseCode.ILLEGAL_ARGUMENT.getCode(), errorMsg);
    }

    public static <T> ServiceResponse<T> createByNeedLogin(){
        return new ServiceResponse<T>(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
    }

    public static <T> ServiceResponse<T> createByNeedLogin(String errorMsg){
        return new ServiceResponse<T>(ResponseCode.NEED_LOGIN.getCode(),errorMsg);
    }

    public static <T> ServiceResponse<T> createByException(String errorMsg){
        return new ServiceResponse<T>(ResponseCode.Exception.getCode(),errorMsg);
    }

}
