package io.b1ruk.proj.irrigationDemo.irrigationDemo.service.irrigation;

import io.b1ruk.proj.irrigationDemo.irrigationDemo.exception.IrrigationInitException;
import io.b1ruk.proj.irrigationDemo.irrigationDemo.exception.SensorNotAvailableException;
import io.b1ruk.proj.irrigationDemo.irrigationDemo.model.Land;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.retry.annotation.EnableRetry;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.doThrow;

@EnableRetry
@SpringBootTest(classes = {IrrigationSensorService.class,IrrigationMockImplementationService.class})
class IrrigationSensorServiceTest {

    private IrrigationSensorService irrigationSensorService;


    private Land land;

    @BeforeEach
    public void init(){
        this.irrigationSensorService=new IrrigationMockImplementationService();

        this.land = Land.builder()
                .landIndex(null)
                .soilType("silt")
                .build();

    }

    @Test
    @DisplayName("Should through an exception initialize land for irrigation")
    void shouldThroughExceptionInitLandForIrrigation(){

        assertThatThrownBy(()->irrigationSensorService.initLandForIrrigation(land))
                .isInstanceOf(IrrigationInitException.class);
    }

    @Test
    @DisplayName("Should timeout when initializing land for irrigation")
    void shouldTimeOutWhenInitializingLandForIrrigation(){
        land.setLandIndex(1);
        doThrow(new IrrigationInitException("unable to process request")).when(Mockito.spy(irrigationSensorService)).initLandForIrrigation(land);

        int methodCount = Mockito.mockingDetails(Mockito.spy(irrigationSensorService)).getInvocations().size();
        assertThat(methodCount).isGreaterThan(1);
    }

    @Test
    @DisplayName("Should initialize land for irrigation")
    void shouldUpdateLand(){
        assertThatThrownBy(()->irrigationSensorService.updateIrrigationSlot(land))
                .isInstanceOf(SensorNotAvailableException.class);
    }

    @Test
    @DisplayName("Should timeout when updating state")
    void shouldTimeoutWhenUpdatingState(){
        land.setLandIndex(1);
        doThrow(new IrrigationInitException("unable to process request")).when(Mockito.spy(irrigationSensorService)).updateIrrigationSlot(land);

        int methodCount = Mockito.mockingDetails(Mockito.spy(irrigationSensorService)).getInvocations().size();
        assertThat(methodCount).isGreaterThan(1);
    }

}