package com.example.onlinetoolsservice.controller;

import com.example.onlinetoolsservice.common.Result;
import com.example.onlinetoolsservice.service.ReferenceService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.vavr.control.Try;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequestMapping("onlinetools/reference")
public class ReferenceController {

    private final ReferenceService referenceService;

    public ReferenceController(ReferenceService referenceService) {
        this.referenceService = referenceService;
    }

    @PostMapping("listReference")
    public Result listReferences(){

        RetryConfig config  = RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration( Duration.ofMillis(1500))
                .build();
        Retry retry = Retry.of("id", config);
        Try<Result> supplier = (Try<Result>) Try.ofSupplier(
                        Retry.decorateSupplier(retry,
                                () -> Result.success(referenceService.listReferences())))
                .recover(throwable -> {
            return Result.success(null);
        });

        //List<Reference> references = referenceService.listReferences();

        return supplier.get();
    }
    
    @CircuitBreaker(name = "order")
    @PostMapping("searchReference")
    public Result searchReference(@RequestParam String searchKey) {
        return Result.success(referenceService.findReferencesBySearchKey(searchKey));
    }
}
