package com.liveus;

import com.liveus.common.bean.ConfigBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@EnableConfigurationProperties({ConfigBean.class})
@SpringBootApplication
@MapperScan(basePackages = {"com.liveus.core.*.mapper"})
public class HomePageApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomePageApplication.class, args);
	}
}
