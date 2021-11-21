package com.jinchao.mybatisplus.mp.service.impl;

import com.jinchao.mybatisplus.mp.entity.User;
import com.jinchao.mybatisplus.mp.mapper.UserMapper;
import com.jinchao.mybatisplus.mp.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-11-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
