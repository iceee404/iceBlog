package com.ice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Package:com.ice
 * @ClassName:IceBlogApplication
 * @Auther:iceee
 * @Date:2022/2/28
 * @Description:
 */
@MapperScan("com.ice.mapper")
@SpringBootApplication
public class IceBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(IceBlogApplication.class, args);
    }
}
