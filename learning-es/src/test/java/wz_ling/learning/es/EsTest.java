package wz_ling.learning.es;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;
import wz_ling.learning.es.po.UserPo;
import wz_ling.learning.starter.es.util.ESLogUtil;

import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EsLearningApplication.class)
public class EsTest {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;

    @Test
    public void test() {
//        createIndex();
//        deleteIndex();
//        saveOne();
//        getOne();
//        deleteOne();
//        batchSave();
//        search();
//        batchDelete();
//        testLog();
    }

    public void createIndex() {
        String indexName = UserPo.class.getAnnotation(Document.class).indexName();
        IndexOperations indexOps = getIndexOps(UserPo.class);
        if (indexOps.exists()) {
            log.warn("indexName[{}] already exist", indexName);
        } else {
            boolean create = getIndexOps(UserPo.class).create();
            log.info("createIndex[{}]:{}", indexName, create);
        }
        getIndex();
    }

    public void deleteIndex() {
        String indexName = UserPo.class.getAnnotation(Document.class).indexName();
        boolean deleted = getIndexOps(UserPo.class).delete();
        log.info("deleteIndex[{}]:{}", indexName, deleted);
    }

    public void getIndex() {
        Map<String, Object> settings = getIndexOps(UserPo.class).getSettings(true);
        log.info("getIndex:{}", settings);
    }


    private IndexOperations getIndexOps(Class<?> clazz) {
        return elasticsearchTemplate.indexOps(clazz);
    }


    public void saveOne() {
        UserPo user = UserPo.builder().id("0001").name("zhangsan").age(18).sex("男").build();
        UserPo save = elasticsearchTemplate.save(user);
        log.info("testSaveOne:{}", save);
    }

    public void getOne() {
        UserPo get = elasticsearchTemplate.get("0001", UserPo.class);
        log.info("getOne:{}", get);
    }

    public void deleteOne() {
        String delete = elasticsearchTemplate.delete("rzuAGnoBzMQ295T4HVZE", UserPo.class);
        log.info("deleteOne:{}", delete);
    }

    public void batchSave() {
        UserPo user1 = UserPo.builder().id("1001").name("zhangsan1").age(18).sex("男").build();
        UserPo user2 = UserPo.builder().id("1002").name("zhangsan2").age(11).sex("男").build();
        UserPo user3 = UserPo.builder().id("1003").name("zhangsan3").age(19).sex("女").build();
        UserPo user4 = UserPo.builder().id("1004").name("zhangsan4").age(23).sex("男").build();
        Iterable<UserPo> saveBatch = elasticsearchTemplate.save(user1, user2, user3, user4);
        log.info("batchSave:{}", saveBatch);
    }

    public void search() {
        SearchHits<UserPo> search = elasticsearchTemplate.search(Query.findAll(), UserPo.class);
        log.info("search:{}", search);
    }

    public void batchDelete() {
        String indexName = UserPo.class.getAnnotation(Document.class).indexName();
        elasticsearchTemplate.delete(Query.findAll(), UserPo.class, IndexCoordinates.of(indexName));
    }

    public void testLog() {
        ESLogUtil.debug("测试：{}", UserPo.builder().id("1001").name("zhangsan1").age(18).sex("男").build());
    }

}