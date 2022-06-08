package wz_ling.learning.transaction.domain.po;


import lombok.Data;

import javax.persistence.*;

/**
 * 通用 Mapper 代码生成器
 *
 * @author mapper-generator
 */
@Data
@Table(name = "`demo`")
public class DemoPo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "`id`")
  private Long id;

  @Column(name = "`name`")
  private String name;
}




