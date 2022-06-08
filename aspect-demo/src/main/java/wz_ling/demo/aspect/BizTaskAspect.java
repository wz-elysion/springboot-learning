package wz_ling.demo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;
import wz_ling.demo.lang.BizTaskUserService;
import wz_ling.demo.lang.TaskActiver;


@Aspect
@Component
public class BizTaskAspect {

    //value指向我们的目标对象，defaultImpl指向我们的代理接口实现类
    @DeclareParents(value = "wz_ling.demo.lang.UserService", defaultImpl = BizTaskUserService.class)//注解在我们的代理接口上
    public TaskActiver taskActiver;//声明代理接口

}