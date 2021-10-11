package com.lyxsdu.controller;

import com.lyxsdu.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class login {
    /*设置一个默认的访问首页。*/
    @GetMapping({"/","/login"})
    public String login (){
        return  "login";
    }
    /*再点击登录后，要跳转到默认的admin首页。这里由两个问题，1是点击按钮上的action 路径*/

    @PostMapping("/login") //这里我不明白为什么登录地址要和首页的访问地址写一样。登录采用的是post方式。
    public  String admin(User user){
        //return  "main";
        /*return  "main"; 如果直接return到main页面，那么就是转发，请求地址没有改变。那么当再次点击刷新页面，会重复提交post请求，执行重复提交表单，不能再路径上回传，那是get请求了*/
        //想想密码错误也能过来吗，不行的。得判断。
        if (user.getUserName().equals("liyixin")|| user.getPassword().equals("12345") ){
            return "redirect:/main.html";
        }
        else{
            return "login";
        }

       // return  "redirect:/main"; //看懂这一块了，重定向不能直接重定向到页面，只能重定向到请求。html不是jsp，可以重定向到jsp。
      /*额，无意中还把post防刷写处理来了。*/
    }
    @GetMapping("/main.html") //这一块感觉还是不对。这个路径可以直接请求。
    public  String admin2(User user, HttpSession session){
        //防止直接被用户用路径访问，这个地方得加拦截器，或者判断。满足条件才能跳转，不满足即没登录，得跳转到登录页。

        return "main";
    }
}
