package wz_ling.learning.mybatis;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;
import wz_ling.learning.mybatis.base.PageQuery;
import wz_ling.learning.mybatis.dao.PersonMapper;
import wz_ling.learning.mybatis.po.PersonPO;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

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
    public void query() {
        WeekendSqls<PersonPO> sqls = WeekendSqls.custom();
        sqls.andEqualTo(PersonPO::getAge, 20);
        Example example = Example.builder(PersonPO.class).where(sqls).build();
        List<PersonPO> list = personMapper.selectByExample(example);
        log.info("query success：{}", list);
    }

    @Test
    public void pageQuery() {
        PageQuery<PersonPO> pageQuery = new PageQuery<PersonPO>()
                .setPageNum(1)
                .setPageSize(3)
                .setPageSupplier(() -> personMapper.selectAll());
        PageInfo<PersonPO> personPOPageInfo = personMapper.pageQuery(pageQuery);
        log.info("query success：{}", personPOPageInfo);
    }


    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void cursor() {
        WeekendSqls<PersonPO> sqls = WeekendSqls.custom();
        Example example = Example.builder(PersonPO.class).where(sqls).build();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Cursor<PersonPO> personPOS = sqlSession.getMapper(PersonMapper.class).selectCursorByExampleMapper(example);
        AtomicInteger count = new AtomicInteger(0);
        personPOS.forEach(x -> {
            log.info("query success：{}", x);
            count.getAndIncrement();
        });
        sqlSession.close();
        log.info("count：{}", count);
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
