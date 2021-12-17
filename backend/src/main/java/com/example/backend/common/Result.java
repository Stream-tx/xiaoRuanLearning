package com.example.backend.common;

import lombok.Data;

@Data
public class Result {

    private int code;
    private String message;
    private Object data;

    public static Result success(Object data){
        return success(200, "Success !",data);
    }

    public static Result success(int code, String message, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static Result fail(String message){
        return fail(400, message,null);
    }

    public static Result fail(int code, String message, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

}
