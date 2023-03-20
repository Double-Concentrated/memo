package com.example.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Flat-White
 * @since 2021-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Memo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "memo_id", type = IdType.AUTO)
    private Long memoId;

    private Long userId;

    private String title;

    private String description;

    private String content;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer status;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}
