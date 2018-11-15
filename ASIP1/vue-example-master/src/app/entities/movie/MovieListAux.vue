<template>
  <div class="fondo">
  <LoadingPage
    :loading="loading"
    :error="error">
      <div
      v-if="error"
      class="error">
      <pre>{{ error }}</pre>
    </div>
    <br/>
    <h1 class="princ" :key="this.titulo"> {{titulo}}</h1>
    <div class= "namemovie" v-for="movie in movies" :key="movie.idMovie">
    <div class= "tittle">
        <router-link
         :to="{ name: 'MovieDetail', params: { id: movie.idMovie }}">
          {{ movie.name }}
        </router-link>
    </div>
    <img class="imagen" src="movie.jpg">
    </div>
  </LoadingPage>
</div>
</template>

<script>
import { HTTP } from '../../common/http-common'
import LoadingPage from '../../components/LoadingPage'
import auth from '../../common/auth'

export default {
  components: { LoadingPage},
  data() {
    return {
      loading: false,
      movies: null,
      error: null,
      titulo:''
    }
  },
  
  created() {
    this.fetchData()
    
  },
  methods: {
  fetchData() {
    this.loading = true

    if (this.$route.params.id=='vista') {
      this.titulo = 'Viewed Movies'
      HTTP.get(`movies/vistas`)
      .then(response => {
       this.movies = response.data})
     .catch(err => {
       this.error = err.message })
     .finally(() => this.loading = false)

    }

    else if (this.$route.params.id=='pendiente'){
       this.titulo = 'Pending Movies'
       HTTP.get(`movies/pendientes`)
      .then(response => {
       this.movies = response.data })
      .catch(err => {
       this.error = err.message})
    .finally(() => this.loading = false)


    } else if (this.$route.params.id=='vistavote'){
       this.titulo = 'Movies to valorate'
       HTTP.get(`movies/vistas/tovote`)
      .then(response => {
       this.movies = response.data})
       .catch(err => {
       this.error = err.message})
      .finally(() => this.loading = false)
    }
     
    },
    
    _successHandler(response) {
      this.fetchData()
    },

    _errorHandler(err) {
      this.error = err.response.data.message
    }
  }
}
</script>

<style scoped lang="scss">
  .princ{
    font-size: 40px;
    letter-spacing: 8px;
    color: red;
    text-align: center;
    text-transform: uppercase;
  }

  .namemovie {
    margin-top :20px;
    width:28%;
    height:20%;
    font-size: big;
    float:left;
    padding:10px;
    float-left:20px;
    margin-left :50px;
    background-color: white;
    border-radius: 20px 20px 20px 20px
  }

  .imagen{
    width:50%;
    height:30%;
    margin-left:70px;

    }

  .tittle{
    font-size: 20px;
    color: red;
    text-align: center;
    margin-bottom:10px;
    text-transform: uppercase;

      
    }
   


</style>



