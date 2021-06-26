package wz_ling.learning.starter.es.po;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LoggerPo {
    private LocalDateTime localDateTime;
    private String level;
    private String log;
    private String className;
    private String methodName;
    private Integer lineNumber;
}
