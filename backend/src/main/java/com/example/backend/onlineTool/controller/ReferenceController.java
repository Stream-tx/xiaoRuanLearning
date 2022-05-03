package com.example.backend.onlineTool.controller;

import com.example.backend.result.Result;
import com.example.backend.onlineTool.entity.Reference;
import com.example.backend.onlineTool.service.ReferenceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("reference")
public class ReferenceController {

    private final ReferenceService referenceService;

    public ReferenceController(ReferenceService referenceService) {
        this.referenceService = referenceService;
    }

    @PostMapping("listReference")
    public Result listQuestions(){
        List<Reference> references = referenceService.listReferences();
        return Result.success(references);
    }

    @PostMapping("searchReference")
    public Result searchReference(@RequestParam String searchKey) {
        return Result.success(referenceService.findReferencesBySearchKey(searchKey));
    }
}
