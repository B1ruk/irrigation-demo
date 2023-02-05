package io.b1ruk.proj.irrigationDemo.irrigationDemo.repository;

import io.b1ruk.proj.irrigationDemo.irrigationDemo.model.IrrigationTimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IrrigationTimeSlotRepository extends JpaRepository<IrrigationTimeSlot, Long> {
}
