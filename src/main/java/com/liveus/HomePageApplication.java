package com.liveus;

import com.liveus.domain.Configbean2;
import com.liveus.utils.MyMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@EnableConfigurationProperties({Configbean2.class})
@SpringBootApplication
@MapperScan(basePackages = "com.liveus.dao"/*, markerInterface = MyMapper.class*/)
public class HomePageApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomePageApplication.class, args);
	}
}
