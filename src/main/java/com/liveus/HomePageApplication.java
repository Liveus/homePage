package com.liveus;

import com.liveus.config.ConfigBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableConfigurationProperties({ConfigBean.class})
@SpringBootApplication
@MapperScan(basePackages = {"com.liveus.core.*.mapper"})
@EnableScheduling
public class HomePageApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomePageApplication.class, args);
	}
}
