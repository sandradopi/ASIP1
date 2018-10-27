<template>
  <b-navbar
    toggleable="md">
    <b-navbar-toggle target="nav_collapse"/>
    
    <b-navbar-brand to="/login">Log in</b-navbar-brand>
     <b-navbar-brand to="/login">Log out</b-navbar-brand>

    <b-collapse
      id="nav_collapse"
      is-nav>

      <b-navbar-nav>
      <b-nav-item
          :to="{ name: 'MovieListUsuarioNormalVistas' }"
          exact>Viewed Movies</b-nav-item>

      <b-nav-item
          :to="{ name: 'MovieList' }"
          exact>Admin Films</b-nav-item>
      <b-nav-item
          :to="{ name: 'MovieListUsuarioNormalPendientes' }"
          exact>Pending Movies</b-nav-item>
        <b-nav-item
          :to="{ name: 'MovieListUsuarioNormal' }"
          exact>Movies</b-nav-item>
      </b-navbar-nav>

    </b-collapse>
  </b-navbar>
</template>

<style scoped lang="scss">
  .movie {
    white-space: pre;
  }
</style>