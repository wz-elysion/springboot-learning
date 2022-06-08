package wz_ling.learning.mongo;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wz_ling.learning.mongo.model.Person;


@RestController
@RequestMapping("/mongo")
@SpringBootApplication
public class MongoApplicationDemo {


    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/test")
    public String test() {
        return "ok";
    }

    @GetMapping("/update/set")
    public String updateSet() {
        Criteria criteria = Criteria.where("name").is("name1");
        Query query = Query.query(criteria);
        Update update = new Update();
        update.set("age", 1);
        FindAndModifyOptions options = new FindAndModifyOptions();
        //先查询，如果没有符合条件的，会执行插入，插入的值是查询值 ＋ 更新值
        options.upsert(true);
        Person person = mongoTemplate.findAndModify(query, update, options, Person.class);
        return JSON.toJSONString(person);
    }

    @GetMapping("/update/setOnInsert")
    public String updateSetOnInsert() {
        Criteria criteria = Criteria.where("name").is("name1");
        Query query = Query.query(criteria);
        Update update = new Update();
        update.setOnInsert("age", 2);
        FindAndModifyOptions options = new FindAndModifyOptions();
        //先查询，如果没有符合条件的，会执行插入，插入的值是查询值 ＋ 更新值。
        //update.set()，会使用旧值代替新值
        //update.setOnInsert，会保留旧值
        options.upsert(true);
        Person person = mongoTemplate.findAndModify(query, update, options, Person.class);
        return JSON.toJSONString(person);
    }


    public static void main(String[] args) {
        SpringApplication.run(MongoApplicationDemo.class, args);
    }

}