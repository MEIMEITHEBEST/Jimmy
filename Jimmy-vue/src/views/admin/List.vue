<template>
  <div class="list-container">
    <el-form :inline="true">
      <el-form-item label="姓名">
        <el-input placeholder="姓名" v-model="queryForm.name"/>
      </el-form-item>

      <el-form-item label="电话">
        <el-input placeholder="电话" v-model="queryForm.tel"/>
      </el-form-item>

      <el-form-item>
        <el-button @click="onSearch()" type="primary" >查询</el-button>
        <el-button type="primary" @click="add()">新增</el-button>
        <el-button type="danger" @click="confirmDel()">批量删除</el-button>
      </el-form-item>
    </el-form>

    <el-divider :border-style="'dashed'"/>

    <el-table stripe border :data="datalist" v-loading="listLoading" style="width: 100%" :header-cell-style="{background:'#f5f5f5'}" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" />
      <el-table-column header-align="center" align="center" prop="username" label="用户名"/>
      <el-table-column header-align="center" align="center" prop="name" label="姓名"/>
      <el-table-column header-align="center" align="center" prop="sex" label="性别" />
      <el-table-column header-align="center" align="center" prop="tel" label="电话" />
      <el-table-column header-align="center" align="center" prop="headurl" label="头像" />

      <el-table-column header-align="center" align="center" label="操作" width="150px">
        <template #default="scope">
          <el-button type="text" @click="update(scope.row)" >编辑</el-button>
          <el-button type="text" @click="confirmDel(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination @current-change="handleCurrentChange" :current-page="queryForm.pageNum"
                   :page-size="queryForm.pageSize" background layout="total, prev, pager, next, jumper" :total="total"
                   style="float:right;margin:10px 20px 20px 0;">
    </el-pagination>

    <Addorupdate @refresh="getlist" ref="operateRef"></Addorupdate>
  </div>

</template>

<script setup lang="ts">
  //导入需要的功能或数据
  import { reactive, ref, onMounted } from 'vue';
  import { adminApi } from '@/api/admin-api';
  import { ElMessage, ElMessageBox } from 'element-plus';
  import constants from '@/utils/constants';
  import Addorupdate from "@/views/admin/Addorupdate.vue";

  //定义相关变量
  //列表加载状态
  const listLoading = ref(false);
  //列表数据
  const datalist = ref([]);
  //记录总条数
  const total = ref(0);
  //选择的记录
  const multipleSelection = ref([])
  //定义引用新增或修改组件的响应式变量
  const operateRef = ref();
  //查询表单初始值
  const queryFormState = {
    name: '',
    tel: '',
    pageNum: 1,
    pageSize: constants.PAGE_SIZE,
  };
  //查询表单
  const queryForm = reactive({...queryFormState });

  //调用后台接口查询列表数据
  async function getlist() {
    try {
      //先将加载状态设置为 true  页面变成加载中状态
      listLoading.value = true;
      //调用后台查询管理员列表接口
      let responseModel = await adminApi.queryPageList(queryForm, queryForm.pageNum, queryForm.pageSize);
      datalist.value = responseModel.data.list;
      total.value = responseModel.data.total;
    } catch (err) {
      // ElMessage.error(e.toString());
    } finally {
      //将加载状态设置为 false  将页面的加载中隐藏
      listLoading.value = false;
    }
  }

  // 点击查询
  function onSearch() {
    //设置当前页码为1
    queryForm.pageNum = 1;
    //调用后台接口查询列表数据
    getlist();
  }

  // 点击分页查询
  function handleCurrentChange(val: number) {
    //设置当前页码为点击的页码
    queryForm.pageNum = val;
    //调用后台接口查询列表数据
    getlist();
  }

  //新增管理员
  function add(){
    operateRef.value.showModel()
  }

  //修改管理员
  function update(row){
    operateRef.value.showModel(row)
  }

  // 删除管理员
  async function del(id) {
    try {
      listLoading.value = true;
      let ids = id?[id]:multipleSelection.value.map(item=>item.id);
      if(ids.length == 0){
        ElMessage.warning('请选择要删除的记录');
        return;
      }
      await adminApi.delete(ids);
      ElMessage.success('删除成功');
      getlist();
    }catch (e) {

    }finally {
      listLoading.value = false;
    }
  }

  function confirmDel(id){
    ElMessageBox.confirm('此操作将永久删除该用户, 是否继续?', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }).then(() => {
      del(id);
    }).catch(() => {
      ElMessage({
        type: 'info',
        message: '已取消删除'
      });
    });
  }

  function handleSelectionChange(val) {
    multipleSelection.value = val;
  }

  //组件渲染完成后立即调用一次查询列表
  onMounted(getlist);
</script>

<style scoped>

</style>