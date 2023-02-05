package io.b1ruk.proj.irrigationDemo.irrigationDemo.repository;

import io.b1ruk.proj.irrigationDemo.irrigationDemo.model.Land;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LandRepository extends JpaRepository<Land, Long> {

    Optional<Land> findLandByLandIndex(Integer landIndex);
}
