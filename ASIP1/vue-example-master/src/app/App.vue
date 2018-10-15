<template>
  <div id="app">
    <MenuBar/>
    <router-view class="content"/>
  </div>
</template>

<script>
import MenuBar from './components/MenuBar'

export default {
  components: { MenuBar }
}
</script>

<style scoped lang="scss">
  #app {
    color: #56b983;
  }
</style>
