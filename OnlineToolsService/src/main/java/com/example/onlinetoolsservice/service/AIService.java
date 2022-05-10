package com.example.onlinetoolsservice.service;

import com.example.onlinetoolsservice.common.Result;
import com.example.onlinetoolsservice.entity.Chat;
import com.example.onlinetoolsservice.entity.QuestionEntity;
import com.example.onlinetoolsservice.entity.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AIService {

    public Result questionRecommendation(@RequestParam Long userId);


    public Result addChatRecords(@RequestBody Chat chat) ;


    public Result deleteChatRecords(@RequestParam Long chatId) ;


    public Result findChatRecords(@RequestParam String content) ;


    public Result listChatRecordsByUserId(@RequestParam Long userId) ;

//    根据自己的错题推荐
    public QuestionEntity recommendquestion(User user) ;
//    根据跟自己做题相似的人推荐
    public List<QuestionEntity> recommendquestion2(String username) ;
    User findUserById(Long id);
}
