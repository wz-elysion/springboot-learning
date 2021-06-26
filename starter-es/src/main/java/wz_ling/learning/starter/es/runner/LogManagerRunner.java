package wz_ling.learning.starter.es.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Component;
import wz_ling.learning.starter.es.util.ESLogUtil;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class LogManagerRunner implements CommandLineRunner {

    @Value("${server.name:default}")
    private String logIndexName;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;

    @Override
    public void run(String... args) throws Exception {
        log.info("LogManager init elasticsearchTemplate ...");
        IndexCoordinates index = IndexCoordinates.of("es-log-" + logIndexName);
        ESLogUtil.initESLogUtil(elasticsearchTemplate, index);
    }

    private void initIndex(IndexCoordinates index) {
        log.info("initIndex:{}", index.getIndexName());
        IndexOperations indexOps = elasticsearchTemplate.indexOps(index);
        if (indexOps.exists()) {
            log.warn("indexName[{}] already exist", index.getIndexName());
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("number_of_shards", 1);
            map.put("number_of_replicas", 0);
            Document setting = Document.from(map);
            boolean create = indexOps.create(setting);
            log.info("createIndex[{}]:{}", index.getIndexName(), create);
        }
    }
}
