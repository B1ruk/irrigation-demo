package io.b1ruk.proj.irrigationDemo.irrigationDemo.service.land;

import io.b1ruk.proj.irrigationDemo.irrigationDemo.api.data.LandConfig;
import io.b1ruk.proj.irrigationDemo.irrigationDemo.model.Crop;
import io.b1ruk.proj.irrigationDemo.irrigationDemo.model.IrrigationTimeSlot;
import io.b1ruk.proj.irrigationDemo.irrigationDemo.model.Land;
import io.b1ruk.proj.irrigationDemo.irrigationDemo.repository.CropRepository;
import io.b1ruk.proj.irrigationDemo.irrigationDemo.repository.IrrigationTimeSlotRepository;
import io.b1ruk.proj.irrigationDemo.irrigationDemo.repository.LandRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class LandService {

    private final LandRepository landRepository;
    private final CropRepository cropRepository;
    private final IrrigationTimeSlotRepository irrigationTimeSlotRepository;

    @Transactional
    public ResponseEntity<HttpStatus> createLand(Land land) {
        Crop crop = land.getCrop();
        cropRepository.save(crop);

        if (Objects.isNull(land.getLandIndex())){
            throw new RuntimeException("Land index cannot be null");
        }

        landRepository.save(land);

        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity<HttpStatus> updateLand(Land land) {
        landRepository.save(land);
        return ResponseEntity.ok().build();
    }

    public List<Land> fetchLands(Integer start, Integer max) {
        if (Objects.nonNull(start) && Objects.nonNull(max)) {
            return landRepository.findAll(PageRequest.of(start, max)).getContent();
        }
        return landRepository.findAll();
    }

    @Transactional
    public ResponseEntity<HttpStatus> configureLand(LandConfig landConfig) {
        var land = landRepository.findById(landConfig.getLandId()).orElseThrow(() -> new RuntimeException("Unable to find land"));

        var irrigationTimeSlot = new IrrigationTimeSlot(land, landConfig.getIrrigationTime());
        irrigationTimeSlotRepository.save(irrigationTimeSlot);
        return ResponseEntity.ok().build();
    }
}
