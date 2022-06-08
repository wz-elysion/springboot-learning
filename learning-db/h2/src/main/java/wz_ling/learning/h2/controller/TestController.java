package wz_ling.learning.h2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author: ElySioN
 * @create: 2019-08-01 11:16
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/h2")
    public List<Map<String, Object>> test() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from user");
        return list;
    }
}