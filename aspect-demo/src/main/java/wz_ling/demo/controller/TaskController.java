package wz_ling.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wz_ling.demo.lang.TaskActiver;
import wz_ling.demo.lang.UserService;


@Slf4j
@RestController
@RequestMapping("/task")
public class TaskController {


    @Autowired
    private UserService userController;

    @GetMapping("/start")
    public void start(){
        TaskActiver t = (TaskActiver)userController;
        t.startTask();
        log.info("...");
    }

    @GetMapping("/stop")
    public void stop(){

    }

}
