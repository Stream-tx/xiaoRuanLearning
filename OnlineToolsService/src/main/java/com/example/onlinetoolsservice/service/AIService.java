package com.example.onlinetoolsservice.service;

import com.example.onlinetoolsservice.common.Result;
import com.example.onlinetoolsservice.entity.Chat;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface AIService {

    public Result questionRecommendation(@RequestParam Long userId);


    public Result addChatRecords(@RequestBody Chat chat) ;


    public Result deleteChatRecords(@RequestParam Long chatId) ;


    public Result findChatRecords(@RequestParam String content) ;


    public Result listChatRecordsByUserId(@RequestParam Long userId) ;
}
