package io.b1ruk.proj.irrigationDemo.irrigationDemo.repository;

import io.b1ruk.proj.irrigationDemo.irrigationDemo.model.Crop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CropRepository extends JpaRepository<Crop,Long> {
}
