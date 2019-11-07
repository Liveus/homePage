package com.liveus;

import com.liveus.common.bean.Configbean2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@EnableConfigurationProperties({Configbean2.class})
@SpringBootApplication
@MapperScan(basePackages = {"com.liveus.core.analyze.mapper","com.liveus.core.blog.mapper","com.liveus.core.info.mapper","com.liveus.core.manage.mapper","com.liveus.core.sys.mapper","com.liveus.core.user.mapper"})
public class HomePageApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomePageApplication.class, args);
	}
}
