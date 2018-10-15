<template>
  <b-navbar
    toggleable="md">
    <b-navbar-toggle target="nav_collapse"/>

    <b-navbar-brand to="/">VueExample</b-navbar-brand>

    <b-collapse
      id="nav_collapse"
      is-nav>

      <b-navbar-nav>
        <b-nav-item
          :to="{ name: 'PostList' }"
          exact>Posts</b-nav-item>
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
