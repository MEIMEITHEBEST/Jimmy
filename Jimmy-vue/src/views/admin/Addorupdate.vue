<template>
  <el-dialog
      v-model="dialogVisible"
      :title="!form.id?'新增':'修改'"
      width="40%"
  >

  <el-form :rules="rules" label-width="100px" style="max-width: 600px" ref="formRef" :model="form">
    <el-form-item label="用户名" prop="username">
      <el-input v-model="form.username" :readonly="ureadonly" style="width: 50%"/>
    </el-form-item>

    <el-form-item label="密码" prop="userpwd">
      <el-input v-model="form.userpwd" type="password" style="width: 50%"/>
    </el-form-item>

    <el-form-item label="姓名" prop="name">
      <el-input v-model="form.name" style="width: 50%"/>
    </el-form-item>

    <el-form-item label="性别" prop="sex">
      <el-select v-model="form.sex" style="width: 50%" placeholder="请选择">
        <el-option label="男" value="男"></el-option>
        <el-option label="女" value="女"></el-option>
      </el-select>
    </el-form-item>

    <el-form-item label="电话" prop="tel">
      <el-input v-model="form.tel" style="width: 50%"/>
    </el-form-item>

    <el-form-item label="头像" prop="headurl">
      <el-input v-model="form.headurl" style="width: 50%"/>
    </el-form-item>
  </el-form>

    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="onsubmit" :loading="btnloading">
          确定
        </el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
  import {reactive, ref} from "vue";
  import {adminApi} from "@/api/admin-api";
  import {ElMessage} from "element-plus";
  defineExpose({showModel})
  const dialogVisible = ref(false);
  const btnloading = ref(false);
  const emit = defineEmits(['refresh']);
  const ureadonly = ref(false);

  //手机号格式验证
  var checkPhone = (rule: any, value: any, callback: any) => {
    const phoneReg = /^1[34578]\d{9}$/;
    if (!value) {
      return callback(new Error('手机号不能为空'))
    }
    setTimeout(() => {
      if (!Number.isInteger(+value)) {
        callback(new Error('请输入数字值'))
      } else {
        if (phoneReg.test(value)) {
          callback()
        } else {
          callback(new Error('手机号格式不正确'))
        }
      }
    }, 100);
  };

  function showModel(row) {
    if(row){
      ureadonly.value = true;
      Object.assign(form, row);
    }else{
      ureadonly.value = false;
      Object.assign(form, formDefault);
    }
    dialogVisible.value = true;
  }

  //表单组件
  const formRef = ref();

  //表单初始值
  const formDefault = {
    id:undefined,
    username:undefined,
    userpwd:undefined,
    name:undefined,
    sex:undefined,
    tel:undefined,
    headurl:undefined
  }

  let form = reactive({...formDefault});

  function onsubmit() {
    try {
      btnloading.value = true;
      formRef.value.validate().then(async () => {
        // 表单验证通过
        if (form.id) {
          await adminApi.update(form);
          btnloading.value = false;
          dialogVisible.value = false;
          emit('refresh')
        } else {
          await adminApi.add(form);
        }
        ElMessage.success(`${form.id?'修改':'新增'}成功`)
        btnloading.value = false;
        dialogVisible.value = false;
        emit('refresh')
      })
    }catch (e) {
      // 表单验证失败
    }finally {
      btnloading.value = false;
    }
    console.log("print" + form.username);
  }

  const rules = reactive({
    username: [
      { required: true, message: '请输入用户名', trigger: 'blur' },
    ],
    userpwd: [
      { required: true, message: '请输入密码', trigger: 'blur' },
    ],
    name: [
      { required: true, message: '请输入姓名', trigger: 'blur' },
    ],
    sex: [
      { required: true, message: '请选择性别', trigger: 'blur' },
    ],
    tel: [
        { required: true, message: '请输入电话号码', trigger: 'blur' }, { validator: checkPhone, trigger: 'blur' }
    ],
    headurl: [
        { required: true, message: '请上传头像', trigger: 'blur' },
    ]
  })

  // 关闭弹窗时重置表单
  function handleClose() {
    // 重置表单数据
    Object.assign(form, formDefault);
    // 清除表单验证
    formRef.value?.resetFields();
  }


</script>

<style scoped>

</style>