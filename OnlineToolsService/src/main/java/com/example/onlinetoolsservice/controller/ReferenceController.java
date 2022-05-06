package com.example.onlinetoolsservice.controller;

import com.example.onlinetoolsservice.common.Result;
import com.example.onlinetoolsservice.entity.Reference;
import com.example.onlinetoolsservice.service.ReferenceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("onlinetools/reference")
public class ReferenceController {

    private final ReferenceService referenceService;

    public ReferenceController(ReferenceService referenceService) {
        this.referenceService = referenceService;
    }

    @PostMapping("listReference")
    public Result listReferences(){
        List<Reference> references = referenceService.listReferences();
        return Result.success(references);
    }

    @PostMapping("searchReference")
    public Result searchReference(@RequestParam String searchKey) {
        return Result.success(referenceService.findReferencesBySearchKey(searchKey));
    }
}
