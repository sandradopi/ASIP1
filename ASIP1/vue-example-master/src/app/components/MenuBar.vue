<template>
  <b-navbar
    toggleable="md">
    <b-navbar-toggle target="nav_collapse"/>

    <b-navbar-brand 
      class="hello"
      to="/">Movie World
    </b-navbar-brand>

    <b-collapse
      id="nav_collapse"
      is-nav>

      <b-navbar-nav>
        <b-nav-item
          :to="{ name: 'MovieList' }"
          exact>Movies</b-nav-item>

       <b-nav-item
          v-if="!isAdmin && isLogged"
          :to="{ name: 'MovieListVistas' , params:{ id: 'vista' }}"
          exact>Viewed Movies</b-nav-item>

        <b-nav-item
          v-if="!isAdmin && isLogged"
          :to="{ name: 'MovieListPendientes' , params:{ id: 'pendiente' }}"
          exact>Pending Movies</b-nav-item>

        <b-nav-item
          v-if="!isAdmin && isLogged"
          :to="{ name: 'MovieListVistasVote' , params:{ id: 'vistavote' }}"
          exact>Viewed Movies to Value</b-nav-item>

        <b-nav-item
          v-if="!isAdmin && isLogged"
          :to="{ name: 'UsersList' }"
          exact>Users</b-nav-item>

        <b-nav-item
          v-if="isAdmin"
          :to="{ name: 'ActorList' }"
          exact>Actors</b-nav-item>

         <b-nav-item
          v-if="isAdmin"
          :to="{ name: 'GenreList' }"
          exact>Genres</b-nav-item>

        <b-nav-item
          v-if="isAdmin"
          :to="{ name: 'DirectorList' }"
          exact>Directors</b-nav-item>

      
        <b-nav-item
          v-if="!isLogged"
          :to="{ name: 'Login' }"
          exact>Login</b-nav-item>
      

        <b-nav-item
          v-if="isLogged"
          @click="logout()">Logout</b-nav-item>
      </b-navbar-nav>

      <b-navbar-nav class="ml-auto">
        <b-nav-item>
        {{ loggedUser }} </b-nav-item>
      </b-navbar-nav>

    </b-collapse>
  </b-navbar>
</template>

<script>
import auth from '../common/auth'
export default {

  computed: {
    isLogged() {
      return auth.user.logged
    },
    loggedUser() {
      return auth.user.logged ? `${auth.user.login} (${auth.user.authority})` : 'not logged'
    },
    isAdmin() {
       return auth.isAdmin()
     }
  },
  methods: {
    logout() {
      auth.logout()
      this.$router.push({ name: 'Home' })
    }
  }
}
</script>
<style scoped lang="scss">
.hello{
  font-size: 20px;
  letter-spacing: 1px;
  color: red;
  text-align: center;
  text-transform: uppercase;
}

.nav-item{
  font-size:17px;

}

ml-auto.nav-item{
  color: red;
}


</style>