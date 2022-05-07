package com.example.backend.service;


import com.example.backend.entity.Reference;

import java.util.List;

public interface ReferenceService {

    List<Reference> listReferences();

    List<Reference> findReferencesBySearchKey(String searchKey);
}
