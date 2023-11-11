package org.Eden;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.Eden.mapper")
public class EdenApplication {
    public static void main(String[] args) {
        SpringApplication.run(EdenApplication.class,args);
    }
}
