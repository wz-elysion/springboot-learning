package wz_ling.learning.mybatis.base;

import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.cursor.Cursor;

@tk.mybatis.mapper.annotation.RegisterMapper
public interface SelectCursorByExampleMapper<T> {

    /**
     * 根据Example条件进行查询
     *
     * @param example
     * @return
     */
    @SelectProvider(type = MyExampleProvider.class, method = "dynamicSQL")
    Cursor<T> selectCursorByExampleMapper(Object example);
}
