package wz_ling.learning.mybatis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wz_ling.learning.mybatis.po.PersonPO;
import wz_ling.learning.mybatis.service.PersonService;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Future;

@Slf4j
@RequestMapping("/person")
@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/list")
    public List<PersonPO> list() {
        return personService.list();
    }

    @GetMapping("/asyncInsert")
    public String asyncInsert() {
        try {
            Future<PersonPO> rs = personService.asyncInsert(buildPerson());
            log.info("当前thread-{},insert success：{}", Thread.currentThread().getId(), rs);
        } catch (Exception e) {

        }
        return "ok";
    }

    @GetMapping("/insert")
    public String insert() {
        try {
            PersonPO rs = personService.insert(buildPerson());
            log.info("当前thread-{},insert success：{}", Thread.currentThread().getId(), rs);
        } catch (Exception e) {

        }
        return "ok";
    }

    @GetMapping("/test/aop")
    public String testAop() {
        try {
            Future<PersonPO> rs = personService.testAop(buildPerson());
            log.info("当前thread-{},insert success：{}", Thread.currentThread().getId(), rs);
        } catch (Exception e) {
            log.info("当前thread-{},insert error", Thread.currentThread().getId());
        }
        return "ok";
    }

    private PersonPO buildPerson() {
        PersonPO person = new PersonPO();
        person.setAge(new Random().nextInt(40) + 5);
        String desc = UUID.randomUUID().toString().substring(0, 8);
        person.setName("zhangsan" + desc);
        person.setDesc(desc);
        return person;
    }

}
