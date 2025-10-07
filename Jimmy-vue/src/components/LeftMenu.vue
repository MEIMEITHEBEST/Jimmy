<template>
  <div class="pdm-sidebar__inner">
    <el-menu :collapseTransition="false" class="pdm-sidebar__menu" background-color="#263238" active-text-color="#fff" text-color="#8a979e">
      <el-menu-item index="welcome" @click="$router.push({ name: 'Welcome' })">
        <el-icon><House /></el-icon>
        <span slot="title">首页</span>
      </el-menu-item>

      <el-sub-menu index="组织管理" :popper-class="'pdm-sidebar--dark-popper'">
        <template #title>
          <el-icon>
            <document />
          </el-icon>
          <span slot="title">组织管理</span>
        </template>
        <el-menu-item index="admin" @click="$router.push({ name: 'Admin' })">
          <el-icon>
            <document />
          </el-icon>
          <span slot="title">管理员信息管理</span>
        </el-menu-item>
      </el-sub-menu>

<!--      <el-sub-menu index="组织管理2" :popper-class="'pdm-sidebar&#45;&#45;dark-popper'">-->
<!--        <template #title>-->
<!--          <el-icon>-->
<!--            <document />-->
<!--          </el-icon>-->
<!--          <span slot="title">组织管理</span>-->
<!--        </template>-->
<!--        <el-menu-item index="MisDept" @click="$router.push({ name: 'MisDept' })">-->
<!--          <el-icon>-->
<!--            <document />-->
<!--          </el-icon>-->
<!--          <span slot="title">部门管理</span>-->
<!--        </el-menu-item>-->
<!--      </el-sub-menu>-->

    </el-menu>
  </div>
</template>
<script setup lang="ts">
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
  House,
} from "@element-plus/icons-vue";
import {useRouter} from "vue-router";

const $router = useRouter();

const handleOpen = (key: string, keyPath: string[]) =>{
  console.log(key, keyPath)
}
const handleClose = (key: string, keyPath: string[]) =>{
  console.log(key, keyPath)
}
</script>