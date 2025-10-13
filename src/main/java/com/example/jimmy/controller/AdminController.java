package com.example.jimmy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.jimmy.entity.Admin;
import com.example.jimmy.response.R;
import com.example.jimmy.response.ResponseCode;
import com.example.jimmy.service.AdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.LambdaConversionException;
import java.util.List;
@Tag(name = "管理员信息管理")
@RestController
public class AdminController {
    @Resource
    private AdminService adminService;
    @Operation(summary = "新增管理员")
    @PostMapping("/admin/add")
    @CrossOrigin
    public R add(@RequestBody Admin admin){
        LambdaQueryWrapper<Admin> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Admin::getUsername, admin.getUsername());
        long count = adminService.count(lambdaQueryWrapper);
        if (count>0){
            return R.fail(ResponseCode.USERNAME_EXISTS);
        }
        adminService.save(admin);
        return R.success();
    }
    @Operation(summary = "管理员信息列表")
    @PostMapping("/admin/list")
    @CrossOrigin
    public R<PageInfo<Admin>> list(@RequestBody Admin admin, @RequestParam Integer pageNum, @RequestParam Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        LambdaQueryWrapper<Admin> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(admin.getName() != null, Admin::getName, admin.getName());
        lambdaQueryWrapper.like(admin.getTel() != null, Admin::getTel, admin.getTel());
        lambdaQueryWrapper.orderByDesc(Admin::getId);
        List<Admin> list = adminService.list(lambdaQueryWrapper);
        PageInfo<Admin> pageInfo = new PageInfo<>(list);
        return R.data(pageInfo);
    }
    @Operation(summary = "修改管理员")
    @PostMapping("/admin/update")
    @CrossOrigin
    public R update(@RequestBody Admin admin){
        adminService.updateById(admin);
        return R.success();
    }
    @Operation(summary = "删除管理员")
    @PostMapping("/admin/del")
    @CrossOrigin
    public R del(@RequestParam List<Long> ids){
        adminService.removeByIds(ids);
        return R.success();
    }
}
