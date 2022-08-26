package io.github.vikie1.backend.service;

import io.github.vikie1.backend.model.AccidentModel;
import io.github.vikie1.backend.repository.AccidentsRepository;
import io.github.vikie1.backend.service.analytics.ByAccidentTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AccidentsService {
    @Autowired
    AccidentsRepository accidentsRepository;
    @Autowired
    ByAccidentTime byAccidentTime;

    // CREATE
    public void add(AccidentModel accidentModel) {
        accidentsRepository.save(accidentModel);
        byAccidentTime.update(accidentModel);
    }

    // READ
    public AccidentModel get(long id) {
        return accidentsRepository.findById(id).orElse(null);
    }
    public List<AccidentModel> get(){
        return accidentsRepository.findAll();
    }
    public List<AccidentModel> getToday(){
        return accidentsRepository.findByAccidentDataTime(LocalDate.now());
    }
    public List<AccidentModel> getByDate(LocalDate date){
        return accidentsRepository.findByAccidentDataTime(date);
    }

    // UPDATE
    public void update(AccidentModel accidentModel) {
        accidentsRepository.save(accidentModel);
    }

    // DELETE
    public void delete(long id) {
        byAccidentTime.deduct(get(id));
        accidentsRepository.deleteById(id);
    }
}
