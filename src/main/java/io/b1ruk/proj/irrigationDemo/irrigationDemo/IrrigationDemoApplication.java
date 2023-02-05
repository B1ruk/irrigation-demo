package io.b1ruk.proj.irrigationDemo.irrigationDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class IrrigationDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(IrrigationDemoApplication.class, args);
	}

}
