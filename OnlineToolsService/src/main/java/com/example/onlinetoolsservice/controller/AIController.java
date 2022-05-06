package com.example.onlinetoolsservice.controller;

import com.example.onlinetoolsservice.common.Result;
import com.example.onlinetoolsservice.entity.Chat;
import com.example.onlinetoolsservice.service.AIService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("onlinetools/ai")
public class AIController {

    private final AIService aIService;

    public AIController(AIService aIService) {
        this.aIService = aIService;
    }

    @PostMapping("questionRecommendation")
    public Result questionRecommendation(@RequestParam Long userId) {
        return Result.success(aIService.questionRecommendation(userId));
    }

    @PostMapping("addChatRecords")
    public Result addChatRecords(@RequestBody Chat chat) {
        return Result.success(aIService.addChatRecords(chat));
    }

    @PostMapping("deleteChatRecords")
    public Result deleteChatRecords(@RequestParam Long chatId) {
        return Result.success(aIService.deleteChatRecords(chatId));
    }

    @PostMapping("findChatRecords")
    public Result findChatRecords(@RequestParam String content) {
        return Result.success(aIService.findChatRecords(content));
    }

    @PostMapping("listChatRecords")
    public Result listChatRecords(@RequestParam Long userId) {
        return Result.success(aIService.listChatRecordsByUserId(userId));
    }

}
