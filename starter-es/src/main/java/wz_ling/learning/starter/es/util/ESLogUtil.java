package wz_ling.learning.starter.es.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import wz_ling.learning.starter.es.po.LoggerPo;

import java.time.LocalDateTime;

import static java.util.Objects.isNull;

@Slf4j
public class ESLogUtil {

    private static volatile ElasticsearchRestTemplate elasticsearchTemplate;

    public static void setElasticsearchRestTemplate(ElasticsearchRestTemplate elasticsearchTemplate) {
        if (isNull(ESLogUtil.elasticsearchTemplate)) {
            synchronized (ESLogUtil.class) {
                if (isNull(ESLogUtil.elasticsearchTemplate)) {
                    log.info("LogManager init elasticsearchTemplate ...");
                    ESLogUtil.elasticsearchTemplate = elasticsearchTemplate;
                }
            }
        }
    }

    public static void debug(String format, Object... objects) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            for (int i = 0; i < objects.length; i++) {
                format = format.replace("{}", mapper.writeValueAsString(objects[i]));
            }
        } catch (JsonProcessingException jsonProcessingException) {
            format = jsonProcessingException.getOriginalMessage();
        }
        String className = Thread.currentThread().getStackTrace()[2].getClassName();//调用的类名
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();//调用的方法名
        int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();//调用的行数
        LoggerPo log = LoggerPo.builder().level("debug")
                .localDateTime(LocalDateTime.now())
                .className(className)
                .methodName(methodName)
                .lineNumber(lineNumber)
                .log(format).build();
        //这里可以优化，一批批发送
        elasticsearchTemplate.save(log);
    }

    public static void info() {

    }

    public static void warn() {

    }

    public static void error() {

    }

}
