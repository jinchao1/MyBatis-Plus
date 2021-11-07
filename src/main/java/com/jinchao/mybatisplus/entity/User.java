package com.jinchao.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@EqualsAndHashCode(callSuper = false)
public class User extends Model<User> {


    //主键
//    @TableId(type = IdType.AUTO)//自增
    @TableId(type = IdType.NONE)//默认策略，不配和配都是它（跟随全局）
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
