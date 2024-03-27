package org.compie;

import org.compie.config.BalldontlieConfigurationProperties;
import org.compie.config.RedisConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@SpringBootApplication
@EnableConfigurationProperties({
		BalldontlieConfigurationProperties.class,
		RedisConfigurationProperties.class
})
@EnableScheduling
public class ExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamApplication.class, args);
	}

}
