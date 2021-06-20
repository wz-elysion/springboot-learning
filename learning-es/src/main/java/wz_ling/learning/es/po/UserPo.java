package wz_ling.learning.es.po;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Builder
@Accessors(fluent = true)
@Document(indexName = "user", shards = 3, replicas = 2)
public class UserPo {
    @Id
    private String id;
    private String name;
    private Integer age;
    private String sex;

}