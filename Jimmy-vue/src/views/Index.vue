<script setup lang="ts">
  import Header from '../components/Header.vue'
  import LeftMenu from "../components/LeftMenu.vue";
  import Footer from '../components/Footer.vue';
  import {ref, reactive} from "vue";
  const siteContent = reactive({
    documentClienHeight: 0,
    siteContentHeight: {}
  })
  function getSiteContentHeight() {
    siteContent.documentClienHeight = document.documentElement.clientHeight;
    let hight = siteContent.documentClienHeight - 90 - 100;
    siteContent.siteContentHeight = {
      minHeight: hight + 'px'
    }
  }
  getSiteContentHeight()
  window.onresize = function () {
    getSiteContentHeight()
  }
</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-header style="height: 90px">
        <Header></Header>
      </el-header>
      <el-container>
        <el-aside class="pdm-sidebar pdm-sidebar--dark">
          <LeftMenu></LeftMenu>
        </el-aside>

        <el-container>
          <div class="pdm-content__wrapper">
            <el-main class="pdm-content">
              <el-card :body-style=" siteContent.siteContentHeight ">
                <router-view></router-view>
              </el-card>
            </el-main>
            <el-footer class="layout-footer">
              <Footer></Footer>
            </el-footer>
          </div>


        </el-container>
      </el-container>
    </el-container>
  </div>
</template>



<style lang="less">
  @import url("../styles/style.less");

</style>