package wz_ling.learning.mybatis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wz_ling.learning.mybatis.dao.PersonMapper;
import wz_ling.learning.mybatis.po.PersonPO;

import java.util.List;
import java.util.concurrent.Future;

@Slf4j
@Service
public class PersonService {

    @Autowired
    private PersonMapper personMapper;

    @Autowired
//    @Lazy
    //不加lazy注解启动会报错，大概就是说自己依赖自己，这里和A依赖B，B依赖A还是有丢丢差别的
    private PersonService self;

    public List<PersonPO> list() {
        return personMapper.selectAll();
    }

    @Transactional
    @Async
    //加了异步注解实际调用该方法，返回的是null
    public Future<PersonPO> asyncInsert(PersonPO personPO) throws InterruptedException {
        log.info("异步thread-{}", Thread.currentThread().getId());
        personMapper.insert(personPO);
        int i = 1 / 0;
        Thread.sleep(5000L);
        return new AsyncResult<>(personPO);
    }

    @Transactional
    public PersonPO insert(PersonPO personPO) throws InterruptedException {
        log.info("thread-{}", Thread.currentThread().getId());
        personMapper.insert(personPO);
        Thread.sleep(5000L);
        return personPO;
    }

    public Future<PersonPO> testAop(PersonPO person) throws InterruptedException {
//        return asyncInsert(person);//事务和异步都不生效
        return self.asyncInsert(person);//事务和异步都生效
    }


}
