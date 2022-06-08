package wz_ling.demo.lang;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    public void name(String name) {
        log.info("I`m {}", name);
        try {
            Thread.sleep(100);//模拟登陆过程中进行了数据库查询。各种业务逻辑处理的复杂工作
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}