package com.example.onlinetoolsservice.service;


import com.example.onlinetoolsservice.entity.Reference;

import java.util.List;

public interface ReferenceService {

    List<Reference> listReferences();

    List<Reference> findReferencesBySearchKey(String searchKey);
}
