package com.example.jimmy.response;

import lombok.Getter;

@Getter
public class R<T> {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 状态信息
     */
    private String message;
    /**
     * 泛型类型的返回数据
     */
    private T data;

    private R(Integer code) {
        this.code = code;
    }

    private R(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private R(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> R<T> success() {
        return new R<>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> R<T> success(String  message) {
        return new R<>(ResponseCode.SUCCESS.getCode(), message);
    }

    public static <T> R<T> data(T data) {
        return new R<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), data);
    }

    public static <T> R<T> fail(){
        return new R<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
    }

    public static <T> R<T> fail(String message){
        return new R<>(ResponseCode.ERROR.getCode(), message);
    }

    public static <T> R<T> fail(Integer code, String message){
        return new R<>(code, message);
    }

    public static <T> R<T> fail(ResponseCode responseCode){
        return new R<>(responseCode.getCode(), responseCode.getMessage());
    }
}
