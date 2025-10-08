

<template>
  <div class="list-container">
    <el-form :inline="true">
      <el-form-item label="姓名">
        <el-input placeholder="姓名" v-model="queryForm.name" class="input" clearable="clearable"/>
      </el-form-item>

      <el-form-item label="电话">
        <el-input placeholder="电话" v-model="queryForm.tel" class="input" clearable="clearable"/>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSearch()">查询</el-button>
        <el-button type="primary">新增</el-button>
        <el-button type="danger">删除</el-button>
      </el-form-item>
    </el-form>

    <el-divider :border-style="'dashed'"/>

    <el-table stripe border :data="datalist" v-loading="listLoading" style="width: 100%" :header-cell-style="{background:'#f5f5f5'}">
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

    <el-pagination @current-change="handleCurrentChange" :current-page="queryForm.pagenum"
                   :page-size="queryForm.pagesize" background layout="total, prev, pager, next, jumper" :total="total"
                   style="float:right;margin:10px 20px 20px 0;">
    </el-pagination>
  </div>

</template>

<script setup lang="ts">
  //导入需要的功能或数据
  import { reactive, ref, onMounted } from 'vue';
  import { adminApi } from '@/api/admin-api';
  import { ElMessage, ElMessageBox } from 'element-plus';
  import Constants from '@/utils/constants';

  //定义相关变量
  //列表加载状态
  const listLoading = ref(false);
  //列表数据
  const datalist = ref([]);
  //记录总条数
  const total = ref(0);
  //选择的记录
  const multipleSelection = ref([])
  //查询表单初始值
  const queryFormState = {
    name: '',
    tel: '',
    pageNum: 1,
    pageSize: Constants.PAGE_SIZE,
  };
  //查询表单
  const queryForm = reactive({ ...queryFormState });

  //调用后台接口查询列表数据
  async function getlist() {
    try {
      //先将加载状态设置为 true  页面变成加载中状态
      listLoading.value = true;
      //调用后台查询管理员列表接口
      let responseModel = await adminApi.queryPageList({}, 1, 10);
      //定义 list 变量接收 后台返回的管理员 list数据
      const list = responseModel.data.list;
      //设置记录总条数
      total.value = responseModel.data.total;
      //设置列表数据
      datalist.value = list;
    } catch (e) {
      // ElMessage.error(e.toString());
    } finally {
      //将加载状态设置为 false  将页面的加载中隐藏
      listLoading.value = false;
    }
  }

  // // 点击查询
  // function onSearch() {
  //   //设置当前页码为1
  //   queryForm.pageNum = 1;
  //   //调用后台接口查询列表数据
  //   getlist();
  // }
  //
  // //点击分页查询
  // function handleCurrentChange(val) {
  //   //设置当前页码为点击的页码
  //   queryForm.pageNum = val;
  //   //调用后台接口查询列表数据
  //   getlist();
  // }

  //组件渲染完成后立即调用一次查询列表
  onMounted(getlist);
</script>

<style scoped>

</style>