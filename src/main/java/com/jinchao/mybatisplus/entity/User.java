package com.jinchao.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Auther: jinchao
 * @Date: 2021/10/8 - 21:50
 * @Description: com.jinchao.mybatisplus.entity
 * @version: 1.0
 */
@Data
@TableName("mp_user")
public class User {
    //主键
    private Long id;
    //姓名
    @TableField(value = "real_name",condition = SqlCondition.LIKE)
    private String name;
    //年龄
    private Integer age;
    //邮箱
    private String email;
    //直属上级
    private Long managerId;
    //创建时间
    private LocalDateTime createTime;
    //备注
    private transient String remark;
    //备注1
    @TableField(exist = false)
    private String remark1;
}
