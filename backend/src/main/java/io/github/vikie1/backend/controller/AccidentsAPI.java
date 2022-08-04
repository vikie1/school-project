package io.github.vikie1.backend.controller;

import io.github.vikie1.backend.service.AccidentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/api/")
public class AccidentsAPI {
    @Autowired
    AccidentsService accidentsService;
}
