package wz_ling.learning.mybatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wz_ling.learning.mybatis.dao.PersonMapper;
import wz_ling.learning.mybatis.po.PersonPO;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonMapper personMapper;

    public List<PersonPO> list() {
        return personMapper.selectAll();
    }
}
