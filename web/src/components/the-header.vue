<template>
  <a-layout-header class="header">
<div class="logo" />
<a-menu
    v-model:selectedKeys="selectedKeys1"
    theme="dark"
    mode="horizontal"
    :style="{ lineHeight: '64px' }"
>
  <a-menu-item key="1">nav 1</a-menu-item>
  <a-menu-item key="2">nav 2</a-menu-item>
  <a-menu-item key="3">nav 3</a-menu-item>
</a-menu>
</a-layout-header>
</template>



<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'the-header'
});
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
