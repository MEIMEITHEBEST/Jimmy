package com.example.jimmy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.jimmy.entity.Admin;
import com.example.jimmy.mapper.AdminMapper;
import com.example.jimmy.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
}
