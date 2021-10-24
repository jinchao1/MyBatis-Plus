package com.jinchao.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jinchao.mybatisplus.dao.UserMapper;
import com.jinchao.mybatisplus.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * @Auther: jinchao
 * @Date: 2021/10/24 - 22:42
 * @Description: com.jinchao.mybatisplus
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UpdateTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void updateById(){
        User user = new User();
        user.setId(0001L);
        user.setAge(20);
        user.setEmail("wangyi1@qq.com");
        int i = userMapper.updateById(user);
        System.out.println("影响记录数："+i);
    }

    @Test
    public void updateByWraper(){
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("real_name","孙二").eq("age",19);
        User user = new User();
        user.setId(0002L);
        user.setAge(27);
        user.setEmail("suner2@qq.com");
        int update = userMapper.update(user, userUpdateWrapper);
        System.out.println("影响记录数："+update);
    }
}
