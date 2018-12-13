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
          :to="{ name: 'MovieListVistas' , params:{ id: 'VISTA' }}"
          exact>Viewed Movies</b-nav-item>

        <b-nav-item
          v-if="!isAdmin && isLogged"
          :to="{ name: 'MovieListPendientes' , params:{ id: 'PENDIENTE' }}"
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
          class="nav-item"
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
        <b-nav-item
         v-if="isLogged && !isAdmin" 
         :to="{ name: 'UserDetail' , params: { id: WhatLogin, bol:false}}"> <!--esta variable bol es la que controla que aparezca la informacion del usuario y oculte las peliculas vistas y pendientes en UserDetail. Supongamos que fuimos a la lista de usuarios y pulsamos en las peliculas vistas de otro usuario que no es el que esta logueado, al pulsar desde el menu en el perfil de la persona logueada, nos irá gracias a esto, a la información personal de la persona logueada, en vez de mostrar las peliculas vistas, que era en la pantalla en la que estabamos pero de la persona logueada. -->
        {{ loggedUser }} </b-nav-item>

        <b-nav-item
         v-if="isLogged && isAdmin" >
        {{ loggedUser }} </b-nav-item>
      </b-navbar-nav>

      <b-navbar-nav class="ml-auto1">
      <b-nav-item
       v-if="!isLogged">
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
    WhatLogin() {
      return auth.user.login
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

ul.navbar-nav li.nav-item a {
  font-size:19px;
  color:white;

}

.navbar-light .navbar-nav .show > .nav-link, .navbar-light .navbar-nav .active > .nav-link, .navbar-light .navbar-nav .nav-link.show, .navbar-light .navbar-nav .nav-link.active{
  color:red;
}

.navbar-light .navbar-nav .nav-link:hover, .navbar-light .navbar-nav .nav-link:focus {
   color:red;
}


</style>