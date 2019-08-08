package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.util.JsonResult;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@CrossOrigin
public class MyController {
    @RequestMapping("/hello")
    public String index() {
        return "Hello world";
    }

    @RequestMapping(value = "/user/{password}", method = RequestMethod.PUT)
    public JsonResult<User> getUser(@PathVariable String password) {
        User u = new User(1, "百事", password);
        return new JsonResult<>(u);
    }

    @RequestMapping("/list")
    public JsonResult<List> getUserList(@RequestParam(value = "password", required = false) String password,
                                        @RequestParam("username") String username) {
        List<User> userList = new ArrayList<>();
        User user1 = new User(1, "百事", "123456");
        User user2 = new User(2, username, password);
        userList.add(user1);
        userList.add(user2);
        return new JsonResult<>(userList);
    }

    @RequestMapping("/map")
    public JsonResult<Map> getMap() {
        Map<String,Object> map = new HashMap<>(3);
        User user = new User(1, "百事", "123456");
        map.put("author", user);
        map.put("mail", "888888@icloud.com");
        map.put("phone", "18812345678");
        map.put("github", null);
        return new JsonResult<>(map);
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/query")
    public JsonResult<List> getMySqlData() {
        List<Map<String,Object>> result = jdbcTemplate.queryForList("select * from hxm_test");
        return new JsonResult<>(result);
    }

    @RequestMapping("/update")
    public JsonResult<String> updateMySqlData(@RequestParam("name") String name, @RequestParam("id") String id) {
        String sql = "update hxm_test set name=? where id=?";
        jdbcTemplate.update(sql, name, id);
        return new JsonResult<>("");
    }

    @Autowired
    private UserService userService;

    @RequestMapping("/user/{id}")
    public JsonResult<User> getUser(@PathVariable int id) {
        return new JsonResult<>(userService.Sel(id));
    }
}
