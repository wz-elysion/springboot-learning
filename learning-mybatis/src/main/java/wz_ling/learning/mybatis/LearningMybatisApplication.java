package wz_ling.learning.mybatis;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import tk.mybatis.spring.annotation.MapperScan;


@MapperScan(basePackages = "wz_ling.learning.mybatis.dao")
@SpringBootApplication
@EnableAsync
public class LearningMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningMybatisApplication.class, args);
    }
}
