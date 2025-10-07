<template>
  <div class="version">
    <a target="_blank" class="copyright" href=""> @2020-{{ currentYear }}版权信息 </a>
    <a target="_blank" class="version" href=""> test </a>
  </div>
</template>

<script setup>
  import dayjs from 'dayjs';
  const currentYear=dayjs().year();
</script>

<style lang="less" scoped>
  .version,.copyright {
    font-size: 14px;
    color: rgba(0, 0, 0, 0.45);
    a:hover {
      color: #0a0e14;
    }
  }
</style>