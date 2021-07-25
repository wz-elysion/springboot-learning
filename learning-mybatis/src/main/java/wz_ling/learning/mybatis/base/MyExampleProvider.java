package wz_ling.learning.mybatis.base;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.provider.ExampleProvider;

public class MyExampleProvider extends ExampleProvider {

    public MyExampleProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String selectCursorByExampleMapper(MappedStatement ms) {
        return selectByExample(ms);
    }
}
