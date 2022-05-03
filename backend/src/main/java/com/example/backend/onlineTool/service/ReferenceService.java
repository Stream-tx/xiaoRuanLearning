package com.example.backend.onlineTool.service;


import com.example.backend.onlineTool.entity.Reference;

import java.util.List;

public interface ReferenceService {

    List<Reference> listReferences();

    List<Reference> findReferencesBySearchKey(String searchKey);
}
