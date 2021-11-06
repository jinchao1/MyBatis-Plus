package com.jinchao.mybatisplus;

import com.jinchao.mybatisplus.dao.UserMapper;
import com.jinchao.mybatisplus.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: jinchao
 * @Date: 2021/10/24 - 22:42
 * @Description: com.jinchao.mybatisplus
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ARTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert(){
        User user = new User();
        user.setName("华强");
        user.setAge(30);
        user.setManagerId(2L);
        user.setCreateTime(LocalDateTime.now());
        user.setRemark("这是一个备注信息哦！");
        user.setRemark1("这是备注1");
        boolean insert = user.insert();
        System.out.println(insert);
    }

    @Test
    public void selectById(){
        User user = new User();
        User selectById = user.selectById(1);
        System.out.println(selectById);
        System.out.println(user==selectById);
    }

    @Test
    public void insertOrUpdate(){ // 实体不给id赋值执行insert，给id赋值的话先去查询，数据库里有数据就执行update，没数据就执行insert
        User user = new User();
        user.setId(5L);
        user.setAge(30);
        user.setManagerId(2L);
        user.setCreateTime(LocalDateTime.now());
        boolean insertOrUpdate = user.insertOrUpdate();
        System.out.println(insertOrUpdate);
    }



}
