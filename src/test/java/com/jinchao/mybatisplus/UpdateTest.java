package com.jinchao.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
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

    @Test
    public void updateByWraper1(){
        User whereUser = new User();
        whereUser.setName("孙二");
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<User>(whereUser);
        userUpdateWrapper.eq("real_name","孙二").eq("age",19);
        User user = new User();
        user.setId(0002L);
        user.setAge(27);
        user.setEmail("suner2@qq.com");
        int update = userMapper.update(user, userUpdateWrapper);
        System.out.println("影响记录数："+update);
    }

    @Test
    public void updateByWraper2(){
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<User>();
        userUpdateWrapper.eq("real_name","孙二").eq("age",27).set("age",20);
        int update = userMapper.update(null, userUpdateWrapper);
        System.out.println("影响记录数："+update);
    }

    @Test
    public void updateByWraperLambda(){
        LambdaUpdateWrapper<User> updateWrapper = Wrappers.<User>lambdaUpdate();
        updateWrapper.eq(User::getName,"王一").eq(User::getAge,20).set(User::getAge,28);
        int update = userMapper.update(null, updateWrapper);
        System.out.println("影响记录数："+update);
    }

    @Test
    public void updateByWraperLambdaChain(){
        boolean update = new LambdaUpdateChainWrapper<User>(userMapper).eq(User::getName, "王一").eq(User::getAge, 28).set(User::getAge, 29).update();
        System.out.println(update);
    }
}
