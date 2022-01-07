package com.example.backend.service.impl;

import com.example.backend.entity.Code;
import com.example.backend.repository.CodeRepository;
import com.example.backend.service.CodeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeServiceImpl implements CodeService {
    private final CodeRepository codeRepository;

    public CodeServiceImpl(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @Override
    public Code findCodeById(Long codeId) {
        return codeRepository.findById(codeId).orElse(null);
    }

    @Override
    public List<Code> getCodeByUserIdAndQuestionId(Long userId, Long questionId) {
        return codeRepository.findAllByUserIdAndQuestionId(userId,questionId);
    }

    @Override
    public Code getTheLatestCode(Long userId, Long questionId) {
        return codeRepository.findCodeByUserIdAndQuestionIdOrderBySubmitTimeAsc(userId,questionId);
    }

    @Override
    public void updateCode(Code code) {
        codeRepository.save(code);
    }


}
