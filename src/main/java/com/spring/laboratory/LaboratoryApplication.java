package com.spring.laboratory;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.spring.laboratory.Mapper")
@EnableScheduling
@EnableSwagger2
public class LaboratoryApplication {

	public static void main(String[] args) {
//		这是第一种方式
//		SpringApplication application =new SpringApplication(LaboratoryApplication.class);
//		application.run(args);
//		下面是第二种方式
		SpringApplication.run(LaboratoryApplication.class, args);
	}
}
