package wz_ling.learning.mybatis.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "person")
public class PersonPO {

    @Id
    @GeneratedValue(generator = "JDBC")//可回显主键id
    private Integer id;

    @Column(name = "`name`")
    private String name;

    @Column(name = "`age`")
    private Integer age;

    @Column(name = "`desc`")
    private String desc;

}