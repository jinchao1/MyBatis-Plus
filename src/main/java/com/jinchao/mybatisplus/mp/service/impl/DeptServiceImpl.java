package com.jinchao.mybatisplus.mp.service.impl;

import com.jinchao.mybatisplus.mp.entity.Dept;
import com.jinchao.mybatisplus.mp.mapper.DeptMapper;
import com.jinchao.mybatisplus.mp.service.IDeptService;
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
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

}
