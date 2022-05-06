package com.example.onlinetoolsservice.service.impl;

import com.example.onlinetoolsservice.common.Result;
import com.example.onlinetoolsservice.entity.Chat;
import com.example.onlinetoolsservice.service.AIService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class AIServiceImpl implements AIService {
    @Override
    public Result questionRecommendation(@RequestParam Long userId){
        return Result.success(null);
    }

    @Override
    public Result addChatRecords(@RequestBody Chat chat) {
        return Result.success(null);
    }

    @Override
    public Result deleteChatRecords(@RequestParam Long chatId) {
        return Result.success(null);
    }

    @Override
    public Result findChatRecords(@RequestParam String content){
        return Result.success(null);
    }

    @Override
    public Result listChatRecordsByUserId(@RequestParam Long userId){
        return Result.success(null);
    }



}
