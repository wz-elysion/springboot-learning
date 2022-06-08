package wz_ling.learning.transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import wz_ling.learning.transaction.domain.po.DemoPo;
import wz_ling.learning.transaction.mapper.DemoMapper;


/**
 * <br>PROPAGATION_REQUIRED:如果当前没有事务，就新建一个事务，如果已经存在一个事务中，加入到这个事务中。</>
 * <br>PROPAGATION_SUPPORTS:支持当前事务，如果当前没有事务，就以非事务方式执行。</>
 * <br>PROPAGATION_MANDATORY:使用当前的事务，如果当前没有事务，就抛出异常。</>
 * <br>PROPAGATION_REQUIRES_NEW:新建事务，如果当前存在事务，把当前事务挂起。</>
 * <br>PROPAGATION_NOT_SUPPORTED:以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。</>
 * <br>PROPAGATION_NEVER:以非事务方式执行，如果当前存在事务，则抛出异常。</>
 * <br>PROPAGATION_NESTED:如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与PROPAGATION_REQUIRED类似的操作。</>
 *
 * @author: ElySioN
 * @create: 2019-08-05 16:39
 */
@Service
public class TransactionDemoService {

    @Autowired
    private DemoMapper demoMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public void required() {
        insert();
        insertAndException();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    private void insert() {
        DemoPo t = new DemoPo();
        t.setName("zsInsert");
        demoMapper.insert(t);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    private void insertAndException() {
        DemoPo t = new DemoPo();
        t.setName("zsInsertException");
        demoMapper.insert(t);
        throw new RuntimeException();
    }


}