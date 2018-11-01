<template>
  <LoadingPage
    :loading="loading"
    :error="error">
      <div
      v-if="error"
      class="error">
      <pre>{{ error }}</pre>
    </div>
      <b-btn
         v-if="isAdmin"
         :to="{ name: 'MovieCreate' }"
         variant="primary">New</b-btn>
    <br/>
    <div class= "namemovie" v-for="movie in movies" :key="movie.idMovie">
        <router-link
         :to="{ name: 'MovieDetail', params: { id: movie.idMovie }}">
          {{ movie.name }}
        </router-link>
    <div class="buttone">
    <button
        class="eliminado"
        v-if="isAdmin"
        variant="primary"
        @click="eliminar()">Delete</button>

    <button
        class="ocultado"
        v-if="isAdmin"
        variant="primary"
        @click="ocultar()">Hide</button>
    </div>
    </div>
  </LoadingPage>
</template>

<script>
import { HTTP } from '../../common/http-common'
import LoadingPage from '../../components/LoadingPage'
import auth from '../../common/auth'

export default {
  components: { LoadingPage },
  data() {
    return {
      loading: false,
      movies: null,
      error: null
    }
  },
  computed: {
     isAdmin() {
       return auth.isAdmin()
     }
   },
  created() {
    this.loading = true
    HTTP.get('movies')
    .then(response => {
       this.movies = response.data
     })
     .catch(err => {
       this.error = err.message
     })
    .finally(() => this.loading = false)
  },
  methods: {
    eliminar(){
        HTTP.delete(`movies/${this.$route.params.id}`)
        .then(this._successHandler)
        .catch(this._errorHandler)
      },

    ocultar(){
        HTTP.delete(`movies/${this.$route.params.id}`)
        .then(this._successHandler)
        .catch(this._errorHandler)
      },
    
    _successHandler(response) {
      this.$router.replace({ name: 'MovieList'})
    },
    _errorHandler(err) {
      this.error = err.response.data.message
    }
  }
}
</script>

<style scoped lang="scss">
  .namemovie {
    margin-top :10px;
    float:left;
    padding:20px;
    float-left:20px;
    margin-left :10px;
  }
  .eliminado {
    background-color: #f44336; 
    border: none;
    color: white;
    padding: 8px 20px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 12px;
    border-radius: 8px;
    -webkit-transition-duration: 0.4s;
    transition-duration: 0.4s;
  }

  .eliminado:hover {
    background-color: #f44336;
    color: white;
  
}
.ocultado {
    background-color: #555555; 
    border: none;
    color: white;
    padding: 8px 20px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 12px;
    border-radius: 8px;
    -webkit-transition-duration: 0.4s;
    transition-duration: 0.4s;
  }

  .ocultado:hover {
    background-color: #555555;
    color: white;
  
}
.buttone{
  margin-top:10px;

}




</style>

