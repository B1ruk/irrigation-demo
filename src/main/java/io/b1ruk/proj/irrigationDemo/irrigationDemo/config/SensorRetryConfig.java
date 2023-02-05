package io.b1ruk.proj.irrigationDemo.irrigationDemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:sensor.retry.config.properties")
public class SensorRetryConfig {
}
