package wz_ling.demo.lang;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BizTaskUserService implements TaskActiver {

    private ThreadLocal<Boolean> isOpen = new ThreadLocal<>();


    public BizTaskUserService() {
        isOpen.set(false);
    }

    @Override
    public void startTask() {
        log.info("{},thread:{}",this,Thread.currentThread());
        log.info("task is open");
        isOpen.set(true);
    }

    @Override
    public void stopTask() {
        log.info("task is stop");
        isOpen.set(false);
    }

    @Override
    public void doSomething() {
        if(isOpen.get()){
            log.info("doSomething....");
        }
    }
}
