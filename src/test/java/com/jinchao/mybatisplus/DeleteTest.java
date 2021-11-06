package com.jinchao.mybatisplus;

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
public class DeleteTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void deleteById(){
        int delete = userMapper.deleteById(1449675481979854849L);
        System.out.println("删除条数："+delete);
    }

    @Test
    public void deleteByMap(){
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("real_name","向东");
        columnMap.put("age",35);
        int delete = userMapper.deleteByMap(columnMap);
        System.out.println("删除条数："+delete);
    }

    @Test
    public void deleteBatchIds(){
        int deleteBatchIds = userMapper.deleteBatchIds(Arrays.asList(1449308079404408833L, 1449310695752585217L));
        System.out.println("删除条数v:"+deleteBatchIds);
    }

}
