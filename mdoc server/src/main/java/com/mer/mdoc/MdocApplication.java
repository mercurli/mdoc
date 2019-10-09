package com.mer.mdoc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class MdocApplication {

	public static void main(String[] args) {
		SpringApplication.run(MdocApplication.class, args);
	}

}
