package io.github.vikie1.backend.controller;

import io.github.vikie1.backend.model.analytics.ByTime;
import io.github.vikie1.backend.service.analytics.ByAccidentTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController @RequestMapping("/api/analytics")
public class AnalyticsAPI {
    @Autowired
    ByAccidentTime byAccidentTime;

    //GET - Time related statistics
    @GetMapping("/time")
    public Map<String, List<ByTime>> getAccidentsByTimeSlots(){
        Map<String, List<ByTime>> result = new HashMap<>();
        List<ByTime> accidentsList = byAccidentTime.get();

        if (accidentsList == null || accidentsList.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There exists no data for your query");

        result.put("analytics", accidentsList);
        return result;
    }
    @GetMapping("/time/at/{time}")
    public ByTime getBySpecificTime(@PathVariable String time){
        try {
            LocalTime queryTime = LocalTime.parse(time);
            ByTime result = byAccidentTime.getByTime(LocalTime.parse(time));

            if (result == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There exists no data for your query");

            return result;
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The could not process the time format provided");
        }
    }
    @GetMapping("/time/least/{lowestValue}")
    public Map<String, List<ByTime>> getByTimePopularity(@PathVariable long lowestValue){
        Map<String, List<ByTime>> result = new HashMap<>();
        List<ByTime> accidentsList = byAccidentTime.getPopular(lowestValue);

        if (accidentsList == null || accidentsList.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There exists no data for your query");

        result.put("analytics", accidentsList);
        return result;
    }
}
