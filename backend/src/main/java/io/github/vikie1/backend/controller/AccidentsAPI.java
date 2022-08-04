package io.github.vikie1.backend.controller;

import io.github.vikie1.backend.model.AccidentModel;
import io.github.vikie1.backend.service.AccidentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController @RequestMapping("/api/accidents")
public class AccidentsAPI {
    @Autowired
    AccidentsService accidentsService;

    // GET
    @RequestMapping @GetMapping("/")
    public Map<String, List<AccidentModel>> getAll(){
        Map<String, List<AccidentModel>> result = new HashMap<>();
        List<AccidentModel> allAccidentsList = accidentsService.get();

        if (allAccidentsList.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There exists no data for your query");

        result.put("allAccidents", allAccidentsList);
        return result;
    }
    @GetMapping("/{id}")
    public AccidentModel getById(@PathVariable long id){
        AccidentModel accident = accidentsService.get(id);

        if (accident == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There exists no data for your query query id");

        return accident;
    }
    @GetMapping("/today")
    public Map<String, List<AccidentModel>> getToday(){
        Map<String, List<AccidentModel>> result = new HashMap<>();
        List<AccidentModel> todayAccidentsList = accidentsService.getToday();

        if (todayAccidentsList.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There have been no accident reports today");

        result.put("accidentsReportedToday", todayAccidentsList);
        return result;
    }
    @GetMapping("/date/{date}")
    public Map<String, List<AccidentModel>> getByDate(@PathVariable LocalDate date){
        Map<String, List<AccidentModel>> result = new HashMap<>();
        List<AccidentModel> accidentsList = accidentsService.getByDate(date);

        if (accidentsList.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There were no accidents reported on" + date.toString());

        result.put("accidentsReportedByDate", accidentsList);
        return result;
    }

    @RequestMapping(method = RequestMethod.POST) @PostMapping("/")
    public void create(@RequestBody AccidentModel accidentModel){
         accidentsService.add(accidentModel);
    }

    @RequestMapping(method = RequestMethod.PUT) @PutMapping("/")
    @ResponseBody
    public void update(@RequestBody AccidentModel accidentModel) {
        accidentsService.update(accidentModel);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        accidentsService.delete(id);
    }
}
