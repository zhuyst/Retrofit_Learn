package indi.zhuyst.retrofitlearn.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ServletComponentScan
@MapperScan("indi.zhuyst.retrofitlearn.server.dao")
public class RetrofitLearnServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetrofitLearnServerApplication.class, args);
	}
}
