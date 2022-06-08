package wz_ling.learning.transaction.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author: ElySioN
 * @create: 2019-08-02 17:02
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}