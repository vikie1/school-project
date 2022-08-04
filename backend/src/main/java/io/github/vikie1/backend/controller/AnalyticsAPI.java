package io.github.vikie1.backend.controller;

import io.github.vikie1.backend.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/api/")
public class AnalyticsAPI {
    @Autowired
    AnalyticsService analyticsService;
}
