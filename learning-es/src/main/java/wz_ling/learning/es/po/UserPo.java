package wz_ling.learning.es.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "user", shards = 3, replicas = 2)
public class UserPo {
    @Id
    private String id;
    private String name;
    private Integer age;
    private String sex;

}