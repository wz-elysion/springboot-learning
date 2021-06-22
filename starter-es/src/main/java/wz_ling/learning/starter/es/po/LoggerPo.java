package wz_ling.learning.starter.es.po;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document(indexName = "default-log", shards = 3, replicas = 1)
public class LoggerPo {
    private LocalDateTime localDateTime;
    private String level;
    private String log;
    private String className;
    private String methodName;
    private Integer lineNumber;
}
