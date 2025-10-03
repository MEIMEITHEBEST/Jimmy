package com.example.jimmy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@TableName("admin")
@Schema(description = "管理员信息实体类")
public class Admin {
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(description = "用户名")
    private String username;

    @TableField("password")
    @Schema(description = "用户密码")
    private String userpwd;

    @Schema(description = "管理员姓名")
    private String name;

    @Schema(description = "管理员性别")
    private String sex;

    @Schema(description = "管理员电话")
    private String tel;

    @Schema(description = "管理员头像")
    private String headurl;
}
