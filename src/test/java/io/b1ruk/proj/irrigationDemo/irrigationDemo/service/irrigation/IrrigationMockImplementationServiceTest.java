package io.b1ruk.proj.irrigationDemo.irrigationDemo.service.irrigation;

import io.b1ruk.proj.irrigationDemo.irrigationDemo.exception.IrrigationInitException;
import io.b1ruk.proj.irrigationDemo.irrigationDemo.model.Land;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.doThrow;

class IrrigationMockImplementationServiceTest {

    @Mock
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
    @DisplayName("Should initialize land for irrigation")
    void shouldInitLandForIrrigation(){
        assertThatThrownBy(()->irrigationSensorService.initLandForIrrigation(land))
                .isInstanceOf(IrrigationInitException.class);
    }

    @Test
    @DisplayName("Should timeout when initializing land for irrigation")
    void shouldTimeoutWhenInitializingLandForIrrigation(){
        land.setLandIndex(1);
        doThrow(new IrrigationInitException("unable to process request")).when(Mockito.spy(irrigationSensorService)).initLandForIrrigation(land);

        int methodCount = Mockito.mockingDetails(Mockito.spy(irrigationSensorService)).getInvocations().size();
        assertThat(methodCount).isGreaterThan(1);
    }

}