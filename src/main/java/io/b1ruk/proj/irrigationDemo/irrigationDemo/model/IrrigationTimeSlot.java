package io.b1ruk.proj.irrigationDemo.irrigationDemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "irrigation_time_slot")
public class IrrigationTimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "land")
    private Land land;

    @Column(name = "irrigation_time")
    private long irrigationTime;

    public IrrigationTimeSlot(Land land, long irrigationTime) {
        this.land = land;
        this.irrigationTime = irrigationTime;
    }
}
