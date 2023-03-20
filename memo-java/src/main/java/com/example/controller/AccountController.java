package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.lang.Result;
import com.example.dto.LoginDTO;
import com.example.dto.RegisterDTO;
import com.example.entity.User;
import com.example.service.UserService;
import com.example.vo.UserVO;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AccountController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO loginDTO, HttpServletResponse response){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", loginDTO.getUsername());
        User user = userService.getOne(wrapper);
        if (user != null){
            if (user.getPassword().equals(DigestUtils.md5Hex(loginDTO.getPassword()))){
                UserVO userVO = new UserVO();
                userVO.setId(user.getUserId());
                userVO.setEmail(user.getEmail());
                userVO.setUsername(user.getUserName());
                userVO.setPassword(user.getPassword());
                return Result.success("登陆成功", userVO);
            }else {
                return Result.fail("用户名或密码错误");
            }
        }else {
            return Result.fail("用户不存在");
        }
    }

    @GetMapping("/logout")
    public Result logout(){
        return Result.success("注销成功");
    }

    @PostMapping("/register")
    @ResponseBody
    public Result register(@RequestBody RegisterDTO registerDTO){
        User query = userService.getOne(new QueryWrapper<User>().eq("user_name", registerDTO.getUsername()));
        if (query != null){
            return Result.fail("用户名已存在");
        }else {
            User user = new User();
            user.setUserName(registerDTO.getUsername());
//            密码md5加密后存入数据库
            user.setPassword(DigestUtils.md5Hex(registerDTO.getPassword()));
            user.setEmail(registerDTO.getEmail());
            userService.save(user);
            return Result.success("注册成功", user);
        }
    }
}
