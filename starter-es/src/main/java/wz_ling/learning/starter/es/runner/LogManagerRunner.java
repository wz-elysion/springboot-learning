package wz_ling.learning.starter.es.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Component;
import wz_ling.learning.starter.es.util.ESLogUtil;

@Component
@ConditionalOnClass(ElasticsearchRestTemplate.class)
public class LogManagerRunner implements CommandLineRunner {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;

    @Override
    public void run(String... args) throws Exception {
        ESLogUtil.setElasticsearchRestTemplate(elasticsearchTemplate);
    }
}
