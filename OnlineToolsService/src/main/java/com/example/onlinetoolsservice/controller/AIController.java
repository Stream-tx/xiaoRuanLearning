package com.example.onlinetoolsservice.controller;

import com.example.onlinetoolsservice.common.Result;
import com.example.onlinetoolsservice.entity.QuestionEntity;
import com.example.onlinetoolsservice.entity.User;
import com.example.onlinetoolsservice.feign.AccountInterface;
import com.example.onlinetoolsservice.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("onlinetools/ai")
public class AIController {

    @Autowired(required = false)
    private AccountInterface accountInterface;
    private final AIService aIService;

    public AIController(AIService aIService) {
        this.aIService = aIService;
    }

    @PostMapping("accountInfo")
    public Result accountInfo(@RequestParam Long userId){
        return accountInterface.accountInfo(userId);
    }


    @PostMapping("addChatRecords")
    public Result acquireResponse(@RequestParam String qChat,Long userId) {
        return aIService.acquireResponse(qChat,userId);
    }

    @PostMapping("recommend")
    public Result recommend(@RequestParam Long userId) {
        User user = aIService.findUserById(userId);
        QuestionEntity question = aIService.recommendquestion(user);
        return Result.success(question);
    }

    @PostMapping("recommend2")
    public Result recommend(@RequestParam String username) {
        List<QuestionEntity> question = aIService.recommendquestion2(username);
        return Result.success(question);
    }

}
