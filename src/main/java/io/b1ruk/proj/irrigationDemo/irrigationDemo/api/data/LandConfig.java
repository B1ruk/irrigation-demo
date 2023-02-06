package io.b1ruk.proj.irrigationDemo.irrigationDemo.api.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LandConfig {
    private Long landId;
    private Long irrigationTime;
}
