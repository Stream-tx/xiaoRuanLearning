package com.example.backend.service;

import com.example.backend.common.Result;
import com.example.backend.entity.Code;

import java.util.List;

public interface CodeService {

    Code findCodeById(Long codeId);

    List<Code> getCodeByUserIdAndQuestionId(Long userId, Long questionId);

    Code getTheLatestCode(Long userId, Long questionId);

    Result saveCode(Code code);

    void updateCode(Code code);
}
