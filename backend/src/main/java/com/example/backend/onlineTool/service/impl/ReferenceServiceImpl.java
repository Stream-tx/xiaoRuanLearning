package com.example.backend.onlineTool.service.impl;

import com.example.backend.onlineTool.repository.ReferenceRepository;
import com.example.backend.onlineTool.entity.Reference;
import com.example.backend.onlineTool.service.ReferenceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferenceServiceImpl implements ReferenceService {

    private final ReferenceRepository referenceRepository;

    public ReferenceServiceImpl(ReferenceRepository referenceRepository) {
        this.referenceRepository = referenceRepository;
    }

    @Override
    public List<Reference> listReferences() {
        return referenceRepository.findAll();
    }

    @Override
    public List<Reference> findReferencesBySearchKey(String searchKey) {
        return referenceRepository.findReferencesBySearchKey(searchKey);
    }
}
