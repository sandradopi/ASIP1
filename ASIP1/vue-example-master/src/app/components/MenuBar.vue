<template>
  <b-navbar
    toggleable="md">
    <b-navbar-toggle target="nav_collapse"/>
    
    <b-navbar-brand to="/">Log in</b-navbar-brand>

    <b-collapse
      id="nav_collapse"
      is-nav>

      <b-navbar-nav>
        <b-nav-item
          :to="{ name: 'MovieList' }"
          exact>Movies</b-nav-item>
      </b-navbar-nav>

    </b-collapse>
  </b-navbar>
</template>

<script>
export default {
  computed: {
    entitiesActive: function () {
      return [ 'MovieCreate', 'MovieList', 'DirectorList' ].indexOf(this.$route.name) != -1
    }
  }
}
</script>

<style scoped lang="scss">
  .movie {
    white-space: pre;
  }
</style>