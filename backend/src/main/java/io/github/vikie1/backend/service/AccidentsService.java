package io.github.vikie1.backend.service;

import io.github.vikie1.backend.repository.AccidentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccidentsService {
    @Autowired
    AccidentsRepository accidentsRepository;
}
