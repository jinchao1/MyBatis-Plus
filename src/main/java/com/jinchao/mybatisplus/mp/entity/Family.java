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
@TableName("mp_family")
public class Family implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("familyMeberId")
    private Long familymeberid;

    @TableField("familyMeberName")
    private String familymebername;

    private Integer age;


}
