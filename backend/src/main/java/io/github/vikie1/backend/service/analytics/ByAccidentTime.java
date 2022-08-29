package io.github.vikie1.backend.service.analytics;

import io.github.vikie1.backend.model.AccidentModel;
import io.github.vikie1.backend.model.analytics.ByTime;
import io.github.vikie1.backend.repository.analytics.ByTimeRepository;
import io.github.vikie1.backend.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.logging.Logger;

@Service @EnableAsync
public class ByAccidentTime {
    @Autowired
    ByTimeRepository byTimeRepository;

    // Write
    @Async
    public void update(AccidentModel accidentModel){
        if (accidentModel == null) return;

        String timeSlot = TimeUtils.getTimeSlot(accidentModel.getAccidentData().getTime().toLocalTime());
        ByTime statToUpdate;

        if (byTimeRepository.existsByTimeSlot(timeSlot)){ // if the slot already exists in the db, then increase the accident count of that slot
            statToUpdate = byTimeRepository.findByTimeSlot(timeSlot);
            statToUpdate.setTotalAccidents(statToUpdate.getTotalAccidents() + 1);
        } else statToUpdate = new ByTime(1, timeSlot); // otherwise create it

        byTimeRepository.save(statToUpdate);
    }

    public void deduct(AccidentModel accidentModel){
        if (accidentModel == null) return;
        String timeSlot = TimeUtils.getTimeSlot(accidentModel.getAccidentData().getTime().toLocalTime());
        ByTime statToUpdate;

        if (byTimeRepository.existsByTimeSlot(timeSlot)){ // if the slot already exists in the db, then increase the accident count of that slot
            statToUpdate = byTimeRepository.findByTimeSlot(timeSlot);
            statToUpdate.setTotalAccidents(statToUpdate.getTotalAccidents() - 1);
        } else statToUpdate = new ByTime(0, timeSlot); // ?? otherwise just initialise it at 0

        byTimeRepository.save(statToUpdate);
    }

    //Read
    public ByTime getByTime(LocalTime time){
        return byTimeRepository.findByTimeSlot(TimeUtils.getTimeSlot(time));
    }
    public List<ByTime> getPopular(long lowestValue){
        return byTimeRepository.findByTotalAccidentsGreaterThanEqual(lowestValue);
    }
    public List<ByTime> get(){
        return byTimeRepository.findAll(Sort.by(Sort.Direction.DESC, "totalAccidents"));
    }
}