package com.jinchao.mybatisplus.mp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2021-11-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("mp_dept")
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("deptId")
    private Long deptid;

    @TableField("deptName")
    private String deptname;

    @TableField("jobNum")
    private Long jobnum;


}
