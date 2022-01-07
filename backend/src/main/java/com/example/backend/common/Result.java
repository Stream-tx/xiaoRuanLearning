package com.example.backend.common;

import lombok.Data;

@Data
public class Result {

    private int code;
    private String message;
    private Object data;

    public static Result success(Object data) {
        return success(200, "Success !", data);
    }

    public static Result unknownError(Object data) {
        return fail(201, "未知错误 !", data);
    }
    public static Result emptyResponse(Object data) {
        return fail(202, "没有信息 !", data);
    }
    public static Result invalidUser(Object data) {
        return fail(503, "无效用户 !", data);
    }
    public static Result invalidRole(Object data) {
        return fail(204, "无效角色 !", data);
    }
    public static Result invalidPassword(Object data) {
        return fail(503, "无效密码 !", data);
    }
    public static Result invalidService(Object data) {
        return fail(503, "无效服务 !", data);
    }


    public static Result success(int code, String message, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static Result fail(String message) {
        return fail(400, message, null);
    }

    public static Result fail(int code, String message, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

}
