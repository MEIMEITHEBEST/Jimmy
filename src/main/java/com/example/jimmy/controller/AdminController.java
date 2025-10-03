package com.example.jimmy.controller;

import com.example.jimmy.entity.Admin;
import com.example.jimmy.response.R;
import com.example.jimmy.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "管理员信息管理")
@RestController
public class AdminController {
    @Resource
    private AdminService adminService;
    @Operation(summary = "新增管理员")
    @PostMapping("/admin/add")
    public R add(@RequestBody Admin admin){
        adminService.save(admin);
        return R.success();
    }
    @Operation(summary = "管理员信息列表")
    @GetMapping("/admin/list")
    public R<List<Admin>> list(){
        List<Admin> list = adminService.list();
        return R.data(list);
    }
    @Operation(summary = "修改管理员")
    @PostMapping("/admin/update")
    public R update(@RequestBody Admin admin){
        adminService.updateById(admin);
        return R.success();
    }
    @Operation(summary = "删除管理员")
    @PostMapping("/admin/del")
    public R del(@RequestParam Long id){
        adminService.removeById(id);
        return R.success();
    }
}
