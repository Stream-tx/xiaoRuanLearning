package com.example.ojservice.service;

import com.example.ojservice.entity.Code;

import java.util.List;

public interface CodeService {

    Code findCodeById(Long codeId);

    List<Code> getCodeByUserIdAndQuestionId(Long userId, Long questionId);

    Code getTheLatestCode(Long userId, Long questionId);

    void updateCode(Code code);
}
