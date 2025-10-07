<script setup lang="ts">

</script>

<template>
  <div class="list-container">
    <el-form :inline="true">
      <el-form-item label="姓名">
        <el-input placeholder="姓名"/>
      </el-form-item>

      <el-form-item label="电话">
        <el-input placeholder="电话"/>
      </el-form-item>

      <el-form-item>
        <el-button type="primary">查询</el-button>
        <el-button type="primary">新增</el-button>
        <el-button type="danger">删除</el-button>
      </el-form-item>
    </el-form>

    <el-divider :border-style="'dashed'"/>

    <el-table stripe border style="width: 100%" :header-cell-style="{background:'#f5f5f5'}">
      <el-table-column type="selection" width="55" />
      <el-table-column header-align="center" prop="username" label="用户名"/>
      <el-table-column header-align="center" prop="name" label="姓名"/>
      <el-table-column header-align="center" prop="sex" label="性别" />
      <el-table-column header-align="center" prop="tel" label="电话" />
      <el-table-column header-align="center" prop="headurl" label="头像" />

      <el-table-column header-align="center" label="操作" width="150px">
        <template #default="scope">
          <el-button type="primary" size="mini">编辑</el-button>
          <el-button type="danger" size="mini">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination background layout="prev, pager, next" :total="1000" style="float: right;margin: 10px 20px 20px 0px;"/>
  </div>

</template>

<style scoped>

</style>