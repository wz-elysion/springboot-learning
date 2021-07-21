package wz_ling.learning.mybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wz_ling.learning.mybatis.po.PersonPO;
import wz_ling.learning.mybatis.service.PersonService;

import java.util.List;

@RequestMapping("/person")
@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/list")
    public List<PersonPO> list() {
        return personService.list();
    }
}
