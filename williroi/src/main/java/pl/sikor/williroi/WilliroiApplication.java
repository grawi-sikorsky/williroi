package pl.sikor.williroi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class WilliroiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WilliroiApplication.class, args);
	}

}
