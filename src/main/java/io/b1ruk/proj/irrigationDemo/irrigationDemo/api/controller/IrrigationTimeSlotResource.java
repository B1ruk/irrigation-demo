package io.b1ruk.proj.irrigationDemo.irrigationDemo.api.controller;

import io.b1ruk.proj.irrigationDemo.irrigationDemo.model.Land;
import io.b1ruk.proj.irrigationDemo.irrigationDemo.service.irrigation.IrrigationSensorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("IrrigationTimeSlotResource")
public class IrrigationTimeSlotResource {

    private final IrrigationSensorService irrigationSensorService;

    @PostMapping("initCropForIrrigation")
    public ResponseEntity<HttpStatus> initCropForIrrigation(Land land){
        return irrigationSensorService.initLandForIrrigation(land);
    }

    @PostMapping("updateSlot")
    public ResponseEntity<HttpStatus> updateSlot(@RequestBody Land land){
        return irrigationSensorService.updateIrrigationSlot(land);

    }
}
