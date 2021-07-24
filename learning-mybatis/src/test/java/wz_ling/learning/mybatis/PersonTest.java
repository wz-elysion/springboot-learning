package wz_ling.learning.mybatis;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;
import wz_ling.learning.mybatis.dao.PersonMapper;
import wz_ling.learning.mybatis.po.PersonPO;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LearningMybatisApplication.class)
public class PersonTest {

    @Autowired
    private PersonMapper personMapper;

    @Test
    public void insert() {
        PersonPO person = new PersonPO();
        person.setAge(new Random().nextInt(40) + 5);
        String desc = UUID.randomUUID().toString().substring(0, 8);
        person.setName("zhangsan" + desc);
        person.setDesc(desc);
        personMapper.insert(person);
        log.info("insert success：{}", person);
    }

    @Test
    public void query(){
        WeekendSqls<PersonPO> sqls = WeekendSqls.custom();
        sqls.andEqualTo(PersonPO::getAge, 20);
        Example example = Example.builder(PersonPO.class).where(sqls).build();
        List<PersonPO> list = personMapper.selectByExample(example);
        log.info("query success：{}", list);
    }


    public void delete() {

    }

    @Test
    public void page() {
        PageHelper.startPage(2, 2);
        List<PersonPO> personPOS = personMapper.selectAll();
//        List<PersonPO> personPOS1 = personMapper.selectAll();//这里会查出所有数据，即不会分页。
        PageInfo<PersonPO> pageInfo = new PageInfo<>(personPOS);
        log.info("pageInfo:{}", pageInfo);
    }

}
