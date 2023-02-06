package io.b1ruk.proj.irrigationDemo.irrigationDemo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "land")
@JsonIgnoreProperties(value = "irrigationTimeSlots")
public class Land {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "soil_type")
    private String soilType;

    @ManyToOne
    @JoinColumn(name = "crop")
    private Crop crop;

    /**
     * we use land index as a way to identify the specific land location
     * the sensor class is responsible for translating the land index to the actual location
     */
    @Column(name = "land_index",unique = true)
    private Integer landIndex;

    @OneToMany
    private Set<IrrigationTimeSlot> irrigationTimeSlots = new HashSet<>();
}
