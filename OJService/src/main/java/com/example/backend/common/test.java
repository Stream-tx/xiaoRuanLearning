package com.example.backend.common;

public class test {
    public static void main(String[] args) {
        Result result = Result.emptyResponse(null);
        Result result1 = Result.invalidUser(null);
        Result result2 = Result.unknownError(null);
        Result result3 = Result.invalidPassword(null);
        Result result4 = Result.invalidRole(null);
        Result result5 = Result.invalidService(null);
    }
}
