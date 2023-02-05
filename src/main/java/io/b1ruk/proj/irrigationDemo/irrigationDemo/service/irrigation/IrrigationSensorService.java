package io.b1ruk.proj.irrigationDemo.irrigationDemo.service.irrigation;

import io.b1ruk.proj.irrigationDemo.irrigationDemo.exception.IrrigationInitException;
import io.b1ruk.proj.irrigationDemo.irrigationDemo.exception.SensorNotAvailableException;
import io.b1ruk.proj.irrigationDemo.irrigationDemo.model.Land;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public interface IrrigationSensorService {

    @Retryable(value = IrrigationInitException.class, maxAttemptsExpression = "${retry.maxAttempts}", backoff = @Backoff(delayExpression = "${retry.maxDelay}"))
    ResponseEntity<HttpStatus> initLandForIrrigation(Land land);

    @Retryable(value = SensorNotAvailableException.class, maxAttemptsExpression = "${retry.maxAttempts}", backoff = @Backoff(delayExpression = "${retry.maxDelay}"))
    ResponseEntity<HttpStatus> updateIrrigationSlot(Land land);

    @Recover
    void sensorNotAvailableRecoverMessage(SensorNotAvailableException e);

    @Recover
    void irrigationInitException(IrrigationInitException e);
}
