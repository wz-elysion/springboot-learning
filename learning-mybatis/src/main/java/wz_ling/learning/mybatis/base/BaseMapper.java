package wz_ling.learning.mybatis.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.function.Supplier;

import static java.util.Objects.isNull;

public interface BaseMapper<T> extends Mapper<T> {

     default PageInfo<T> pageQuery(PageQuery<T> pageQuery){
         Supplier<List<T>> pageSupplier = pageQuery.getPageSupplier();
         if(isNull(pageSupplier)){
             return null;
         }
         PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize());
         List<T> record = pageSupplier.get();
         return new PageInfo<>(record);
     }

}
