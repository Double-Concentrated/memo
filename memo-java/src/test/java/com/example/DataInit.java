package com.example;

import com.example.entity.Memo;
import com.example.entity.User;
import com.example.service.impl.MemoServiceImpl;
import com.example.service.impl.UserServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DataInit {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    MemoServiceImpl memoService;

    @Test
    public void addUser(){
        User user = new User();
        user.setUserName("root");
        user.setPassword(DigestUtils.md5Hex("root"));
        userService.save(user);

        System.out.println("addUser success");
    }

    @Test
    public void addMemo(){
        Memo memo = new Memo();
        memo.setUserId(1L);
        memo.setTitle("记得吃早餐");
        memo.setDescription("记得吃早餐");
        memo.setContent("记得吃早餐");
        memoService.save(memo);

        memo.setTitle("记得吃午餐");
        memo.setDescription("记得吃午餐");
        memo.setContent("记得吃午餐");
        memoService.save(memo);

        memo.setTitle("记得吃晚餐");
        memo.setDescription("记得吃晚餐");
        memo.setContent("记得吃晚餐");
        memoService.save(memo);

        memo.setTitle("早点睡觉");
        memo.setDescription("早点睡觉");
        memo.setContent("早点睡觉");
        memoService.save(memo);

        memo.setTitle("好好生活");
        memo.setDescription("好好生活");
        memo.setContent("好好生活");
        memoService.save(memo);

        memo.setUserId(1L);
        memo.setTitle("ROOT");
        memo.setDescription("ROOT");
        memo.setContent("ROOT");
        for (int i = 0; i < 10; i++) {
            memoService.save(memo);
        }
        memo.setUserId(2L);
        memo.setTitle("Test");
        memo.setDescription("Test");
        memo.setContent("Test");
        for (int i = 0; i < 10; i++) {
            memoService.save(memo);
        }

        System.out.println("addMemo success");
    }
}
