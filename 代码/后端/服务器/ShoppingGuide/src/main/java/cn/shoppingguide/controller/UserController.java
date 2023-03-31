package cn.shoppingguide.controller;

import cn.shoppingguide.entity.User;
import cn.shoppingguide.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
//用户登录注册
@Controller
@ResponseBody
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("select")
    public String select(@RequestParam("username")String username,
                         @RequestParam("password")String password) throws JsonProcessingException {
        Map<String, String> map=new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        User user = userService.selectUser(username, password);

        if(user==null){
            map.put("info", "您输入的用户名或密码错误");
        }else{
            map.put("info", "密码正确,进入系统...");
        }

        String res = mapper.writeValueAsString(map);

        return res;
    }

    @RequestMapping("update")
    public String update(@RequestParam("username") String username,
                         @RequestParam("oldPassword") String oldPassword,
                         @RequestParam("newPassword")String newPassword) throws JsonProcessingException {

        Map<String, String> map=new HashMap<>();
        ObjectMapper mapper=new ObjectMapper();

        /*
            先查询用户输入的旧密码是否正确,如果不正确直接返回
            如果正确,进行密码更改的操作
         */
        User user = userService.selectUser(username, oldPassword);
        if(user==null){
            //说明没找到 那就直接返回错误
            map.put("info", "您输入的用户名或密码错误");
            String res = mapper.writeValueAsString(map);
            return res;
        }

        //更改密码
        userService.updatePassword(username, newPassword);
        map.put("info", "更改密码成功");

        String res = mapper.writeValueAsString(map);

        return res;
    }

    @RequestMapping("insert")
    public String insertUser(@RequestParam("username")String username,
                             @RequestParam("password")String password) throws JsonProcessingException {
        Map<String, String> map=new HashMap<>();
        ObjectMapper mapper=new ObjectMapper();
        //判断用户名是否重复
        User user = userService.isexist(username);
        if(user==null){
            boolean b = userService.insertUser(username, password);

            if(b){
                map.put("info", "插入用户成功");
            }else{
                map.put("info", "插入用户失败");
            }
            String res1 = mapper.writeValueAsString(map);
            return res1;
        }else {
            map.put("info","用户名重复请重新输入");
            String res2 = mapper.writeValueAsString(map);
            return res2;
        }
    }

    @RequestMapping("delete")
    public String deleteUser(@RequestParam("username") String username) throws JsonProcessingException {

        Map<String, String> map=new HashMap<>();
        ObjectMapper mapper=new ObjectMapper();

        boolean b = userService.deleteUser(username);

        if(b==true){
            map.put("info", "删除用户成功");
        }else{
            map.put("info", "删除用户失败");
        }

        String res = mapper.writeValueAsString(map);
        return res;
    }
}
