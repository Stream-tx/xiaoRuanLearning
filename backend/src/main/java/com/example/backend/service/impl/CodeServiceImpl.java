package com.example.backend.service.impl;

import com.example.backend.entity.Code;
import com.example.backend.repository.CodeRepository;
import com.example.backend.service.CodeService;
import org.springframework.stereotype.Service;

@Service
public class CodeServiceImpl implements CodeService {
    private final CodeRepository codeRepository;

    public CodeServiceImpl(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @Override
    public Code findCodeById(Long codeId) {
        return codeRepository.getById(codeId);
    }
}
