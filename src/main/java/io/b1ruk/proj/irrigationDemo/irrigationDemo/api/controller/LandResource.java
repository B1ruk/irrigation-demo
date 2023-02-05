package io.b1ruk.proj.irrigationDemo.irrigationDemo.api.controller;

import io.b1ruk.proj.irrigationDemo.irrigationDemo.api.data.LandConfig;
import io.b1ruk.proj.irrigationDemo.irrigationDemo.model.IrrigationTimeSlot;
import io.b1ruk.proj.irrigationDemo.irrigationDemo.model.Land;
import io.b1ruk.proj.irrigationDemo.irrigationDemo.service.land.LandService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("LandResource")
public class LandResource {

    private final LandService landService;

    @PostMapping
    public ResponseEntity<HttpStatus> createLand(@RequestBody Land land) {
        return landService.createLand(land);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateLand(@RequestBody Land land) {
        return landService.updateLand(land);
    }

    @PostMapping("configureLand")
    public ResponseEntity<HttpStatus> configureLand(@RequestBody LandConfig landConfig) {
        return landService.configureLand(landConfig);
    }

    @GetMapping("lands")
    public ResponseEntity<List<Land>> lands(@PathVariable("start") Integer start, @PathVariable("max") Integer max) {
        List<Land> lands = landService.fetchLands(start, max);
        return ResponseEntity.ok()
                .body(lands);
    }
}
