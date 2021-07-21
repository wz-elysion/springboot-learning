package wz_ling.learning.mybatis;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;


@MapperScan(basePackages = "wz_ling.learning.mybatis.dao")
@SpringBootApplication
public class LearningMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningMybatisApplication.class, args);
    }
}
