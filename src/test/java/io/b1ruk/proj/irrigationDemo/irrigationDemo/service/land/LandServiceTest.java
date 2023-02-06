package io.b1ruk.proj.irrigationDemo.irrigationDemo.service.land;

import io.b1ruk.proj.irrigationDemo.irrigationDemo.api.data.LandConfig;
import io.b1ruk.proj.irrigationDemo.irrigationDemo.model.Crop;
import io.b1ruk.proj.irrigationDemo.irrigationDemo.model.IrrigationTimeSlot;
import io.b1ruk.proj.irrigationDemo.irrigationDemo.model.Land;
import io.b1ruk.proj.irrigationDemo.irrigationDemo.repository.CropRepository;
import io.b1ruk.proj.irrigationDemo.irrigationDemo.repository.IrrigationTimeSlotRepository;
import io.b1ruk.proj.irrigationDemo.irrigationDemo.repository.LandRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

class LandServiceTest {

    @Mock
    private LandRepository landRepository;

    @Mock
    private CropRepository cropRepository;

    @Mock
    private IrrigationTimeSlotRepository irrigationTimeSlotRepository;

    private LandService landService;


    private Crop crop;
    private Land land;
    private IrrigationTimeSlot irrigationTimeSlot;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);

        this.landService=new LandService(landRepository,cropRepository,irrigationTimeSlotRepository);

         this.crop = Crop.builder()
                .name("maize")
                .description("maize desc")
                .waterRequiredAmount(450)
                .build();

        this.land = Land.builder()
                .crop(crop)
                .landIndex(4)
                .soilType("silt")
                .build();

        this.irrigationTimeSlot= IrrigationTimeSlot
                .builder()
                .land(land)
                .irrigationTime(4500)
                .build();
    }


    @Test
    @DisplayName("It should create land model")
    void createLand() {
        given(cropRepository.save(crop)).willReturn(crop);
        given(landRepository.save(land)).willReturn(land);

        assertThat(landService.createLand(land).getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
    }


    @Test
    @DisplayName("It should through an exception when landIndex is null")
    void shouldThroughExceptionWhenLandIndexIsNull() {
        given(cropRepository.save(crop)).willReturn(crop);
        land.setLandIndex(null);
        given(landRepository.save(land)).willReturn(land);


        assertThatThrownBy(()->landService.createLand(land))
                .isInstanceOf(RuntimeException.class);

    }

    @Test
    void updateLand(Land land) {
        given(landRepository.save(land)).willReturn(land);

        assertThat(landService.updateLand(land).getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
    }

    @Test
    void fetchLands() {
        List<Land> lands = Arrays.asList(land, land);
        given(landRepository.findAll(PageRequest.of(0,10))).willReturn(new PageImpl<>(lands));

        assertThat(landService.fetchLands(0,10)).isNotEmpty();
        assertThat(landService.fetchLands(0,10).size()).isEqualTo(2L);
    }

    @Test
    void configureLand() {
        LandConfig landConfig=new LandConfig(1L,3600L);
        given(landRepository.findById(1L)).willReturn(Optional.of(land));
        given(irrigationTimeSlotRepository.save(irrigationTimeSlot)).willReturn(irrigationTimeSlot);

        assertThat(landService.configureLand(landConfig).getStatusCode()).isEqualByComparingTo(HttpStatus.OK);

    }


    @Test
    @DisplayName("should through an exception")
    void shouldThroughExceptionWhenConfiguringLand() {
        LandConfig landConfig=new LandConfig(1L,3600L);
        given(landRepository.findById(1L)).willReturn(null);

        assertThatThrownBy(()->landService.configureLand(landConfig))
                .isInstanceOf(RuntimeException.class);

    }
}