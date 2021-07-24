package wz_ling.learning.mybatis.base;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.function.Supplier;

/**
 * @see wz_ling.learning.mybatis.base.BaseMapper#pageQuery(PageQuery)
 * @param <T>
 */
@Data
@Accessors(chain = true)
public class PageQuery<T> {

    private int pageNum = 0;
    private int pageSize = Integer.MAX_VALUE;
    private Supplier<List<T>> pageSupplier;

}
