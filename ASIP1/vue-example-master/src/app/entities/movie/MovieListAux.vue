<template>
  <div class="fondo">
      <div
      v-if="error"
      class="error">
      <pre>{{ error }}</pre>
    </div>
    <br/>
    <h1 class="princ" > {{titulo}}</h1>
    <div class= "namemovie" v-for="movie in movies" :key="movie.idMovie">
    <div class= "tittle">
        <router-link 
         :to="{ name: 'MovieDetail', params: { id: movie.idMovie }}">
          {{ movie.name }}
        </router-link>
    </div>
    <img class="imagen" v-bind:src="'http://localhost:8080/api/movies/list/imagenes/'+ movie.idMovie">
    </br>
     <p class ="subtitle-tag">Approximate average Rating:{{ movie.media}}</p>
    </div>
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
      movies: null,
      error: null,
      titulo:''
    }
  },

   watch: {
    '$route': 'fetchData'
  },
  
  created() {
    this.fetchData()
    
  },
  methods: {
  fetchData() {
    debugger

    if (this.$route.params.id=='VISTA') {
      this.titulo = 'Viewed Movies'
      HTTP.get(`list/${this.$route.params.id}`)
      .then(response => {
       this.movies = response.data})
     .catch(err => {
       this.error = err.message })
     

    }

    else if (this.$route.params.id=='PENDIENTE'){
       this.titulo = 'Pending Movies'
       HTTP.get(`list/${this.$route.params.id}`)
      .then(response => {
       this.movies = response.data })
      .catch(err => {
       this.error = err.message})



    } else if (this.$route.params.id=='vistavote'){
       this.titulo = 'Movies to valorate'
       HTTP.get(`vistas/tovote`)
      .then(response => {
       this.movies = response.data})
       .catch(err => {
       this.error = err.message})
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
    margin-top :60px;
    margin-bottom:20px;
    width:25%;
    height:20%;
    font-size: big;
    float:left;
 
  }



  .imagen{
    width:90%;
    height:100%;
    margin-left:10px;
    margin-bottom:10px;
    margin-top:15px;
    background-color: black;
    border-radius: 20px 20px 20px 20px

    
    }


  .tittle{
    font-size: 20px;
    color: red;
    text-align: center;
    margin-bottom:10px;
    text-transform: uppercase;

      
    }
    .subtitle-tag {
      font-size: 10px;
      font-weight: 600;
      color: red;

    }
   


</style>



