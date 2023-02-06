package io.b1ruk.proj.irrigationDemo.irrigationDemo.service.irrigation;

import io.b1ruk.proj.irrigationDemo.irrigationDemo.exception.IrrigationInitException;
import io.b1ruk.proj.irrigationDemo.irrigationDemo.exception.SensorNotAvailableException;
import io.b1ruk.proj.irrigationDemo.irrigationDemo.model.Land;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class IrrigationMockImplementationService implements IrrigationSensorService {

    @Override
    public ResponseEntity<HttpStatus> initLandForIrrigation(Land land) {
        if (Objects.isNull(land.getLandIndex())){
            throw new IrrigationInitException("Invalid land index state");
        }
        //TODO implement irrigation assignment
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<HttpStatus> updateIrrigationSlot(Land land) {
        if (Objects.isNull(land.getLandIndex())){
            throw new SensorNotAvailableException("invalid sensor state");
        }
        //TODO update irrigation slot
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public void irrigationInitException(IrrigationInitException e) {

    }

    @Override
    public void sensorNotAvailableRecoverMessage(SensorNotAvailableException e) {
        //TODO recover message & alert logic implementation
    }
}
