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
    <div class= "tittle">
        <router-link
         :to="{ name: 'MovieDetail', params: { id: movie.idMovie }}">
          {{ movie.name }}
        </router-link>
    </div>
    <img class="imagen"  v-if="!isAdmin" src="movie.jpg">
    <div class="buttone">
     <b-btn
        class="eliminado"
        v-if="isAdmin"
        variant="primary"
        @click="eliminar(movie.idMovie)">Delete</b-btn>

    <div class= "oculto" v-if= "movie.hidden==false">
     <b-btn
        class="ocultado"
        v-if="isAdmin"
        variant="primary"
        @click="ocultar(movie.idMovie,movie)">Hide</b-btn>
    </div>

    <div class= "mostra" v-if= "movie.hidden==true">
     <b-btn
        class="mostrar"
        v-if="isAdmin"
        variant="primary"
        @click="mostrar(movie.idMovie,movie)">Show</b-btn>
    </div>
     </div>
    </div>
  </LoadingPage>
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
      error: null
    }
  },
  computed: {
     isAdmin() {
       return auth.isAdmin()
     }
   },
  created() {
    this.fetchData()
    
  },
  methods: {
  fetchData() {
      this.loading = true
     HTTP.get(`movies/vistas`)
    .then(response => {
       this.movies = response.data
     })
     .catch(err => {
       this.error = err.message
     })
    .finally(() => this.loading = false)
    },
    eliminar(idMovie){
        HTTP.delete(`movies/${idMovie}`)
        .then(this._successHandler)
        .catch(this._errorHandler)
      },

    ocultar(idMovie,movie){
        movie.hidden = true
        HTTP.put(`movies/${idMovie}`, movie)
        .then(this._successHandler)
        .catch(this._errorHandler)
      },

     mostrar(idMovie,movie){
        movie.hidden = false
        HTTP.put(`movies/${idMovie}`, movie)
        .then(this._successHandler)
        .catch(this._errorHandler)
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
  .namemovie {
    margin-top :20px;
    width:28%;
    height:20%;
    font-size: big;
    float:left;
    padding:20px;
    float-left:20px;
    margin-left :10px;}

    .imagen{
      width:40%;
      height:auto;
      float:left;
    
    }

     .tittle{
      float:right;
      text-align:middle;
      font-weight: bold;
      font-family: sans-serif;
      font: 150% sans-serif;
      font-size: 1.5vw;
      
    }
   

    .namemovie{
    background-color: #ffffff;
    border-radius: 35px 35px 35px 35px
  }

  .duration{
   color:  #555555;
   float:bottom;
   margin-top:10px;
   font-size: small;
   font-style:italic;

}

  .genre{
   color: #555555;
   float:bottom;
   margin-top:10px;
   font-size: small;
   font-style:italic;

}
  
  .eliminado {
    background-color: #f44336; 
    border: none;
    color: white;
    padding: 8px 22px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 12px;
    border-radius: 8px;
    -webkit-transition-duration: 0.4s;
    transition-duration: 0.4s;

    float:bottom;
  }

  .eliminado:hover {
    background-color: #f44336;
    color: white;
  
}
.ocultado {
    margin-top:10px;
    background-color: #555555; 
    border: none;
    color: white;
    padding: 8px 28px;
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
  width:25%;
  height:40%;

}

.mostrar{
    margin-top:10px;
    background-color: green; 
    border: none;
    color: white;
    padding: 8px 26px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 12px;
    border-radius: 8px;
    -webkit-transition-duration: 0.4s;
    transition-duration: 0.4s;

}


</style>



