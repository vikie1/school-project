package io.github.vikie1.backend.service.analytics;

import io.github.vikie1.backend.model.AccidentModel;
import io.github.vikie1.backend.model.analytics.ByVehicleType;
import io.github.vikie1.backend.repository.analytics.ByVehicleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @EnableAsync
public class ByVehicleTypeService {
    @Autowired
    ByVehicleTypeRepository byVehicleTypeRepository;

    @Async
    public void update(AccidentModel accidentModel){
        if (accidentModel == null) return;
        ByVehicleType byVehicleType;

        if (byVehicleTypeRepository.existsByCarType(accidentModel.getAccidentData().getCausalVehicleType())){
            byVehicleType = byVehicleTypeRepository.findByCarType(accidentModel.getAccidentData().getCausalVehicleType());
            byVehicleType.setTotalAccidents(byVehicleType.getTotalAccidents() + 1);
        } else byVehicleType = new ByVehicleType(1, accidentModel.getAccidentData().getCausalVehicleType());

        byVehicleTypeRepository.save(byVehicleType);
    }

    @Async
    public void deduct(AccidentModel accidentModel){
        if (accidentModel == null) return;
        ByVehicleType byVehicleType;

        if (byVehicleTypeRepository.existsByCarType(accidentModel.getAccidentData().getCausalVehicleType())){
            byVehicleType = byVehicleTypeRepository.findByCarType(accidentModel.getAccidentData().getCausalVehicleType());
            byVehicleType.setTotalAccidents(byVehicleType.getTotalAccidents() - 1);
        } else byVehicleType = new ByVehicleType(0, accidentModel.getAccidentData().getCausalVehicleType());

        byVehicleTypeRepository.save(byVehicleType);
    }

    public ByVehicleType getValue(String vehicleType) {
        return byVehicleTypeRepository.findByCarType(vehicleType);
    }

    public List<ByVehicleType> getPopular(long lowestValue){
        return byVehicleTypeRepository.findByTotalAccidentsGreaterThanEqual(lowestValue);
    }

    public List<ByVehicleType> getAll(){
        return byVehicleTypeRepository.findAll();
    }
}
