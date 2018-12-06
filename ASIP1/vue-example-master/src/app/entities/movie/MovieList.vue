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
    <h1 class="princ"> Movies</h1>
    <div class= "namemovie" v-for="movie in movies" :key="movie.idMovie">
    <div class= "tittle">
    {{ movie.name }}
        <router-link
         :to="{ name: 'MovieDetail', params: { id: movie.idMovie }}">
          <img class="imagen"  v-if="movie.idMovie<9 " v-bind:src="'http://localhost:8080/api/movies/list/imagenes/'+ movie.idMovie">

    <img class="imagen"  v-if="(movie.idMovie==9 || movie.idMovie>9)" src="movie.jpg">
        </router-link>
      
      <div class="ratingt" v-if="!isAdmin">Average Rating:</div>
      <div class ="media" v-if="!isAdmin">
        <div class="media1" v-if="!isAdmin"> {{ movie.media}}
        </div>
      </div>
    

    </br>
     
    </div>
    
    <div class="buttone" v-if="isAdmin">
     <b-btn
        class="eliminado"
        variant="primary"
        @click="eliminar(movie.idMovie, movie.name)">Delete</b-btn>

    <div class= "oculto" v-if= "isAdmin && movie.hidden==false">
     <b-btn
        class="ocultado"
        variant="primary"
        @click="ocultar(movie.idMovie,movie)">Hide</b-btn>
    </div>

    <div class= "mostra" v-if= "movie.hidden==true">
     <b-btn
        class="mostrar"
        variant="primary"
        @click="mostrar(movie.idMovie,movie)">Show</b-btn>
    </div>
     </div>
    </div>
    </div>
  </LoadingPage>
</template>

<script>
import { HTTP } from '../../common/http-common'
import LoadingPage from '../../components/LoadingPage'
import auth from '../../common/auth'
import Vue from 'vue'

export default {
  components: { LoadingPage},
  data() {
    return {
      loading: false,
      movies: null,
      error: null,
      media:null
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

    if(!(this.isAdmin)){
        this.loading = true
        HTTP.get(`movies/media`)
        .then(response => {
           this.movies = response.data
         })
         .catch(err => {
           this.error = err.message
         })
        .finally(() => this.loading = false)}
    else{
    this.loading = true
    HTTP.get('movies')
    .then(response => {
       this.movies = response.data
     })
     .catch(err => {
       this.error = err.message
     })
    .finally(() => this.loading = false)
    }

    },

    eliminar(idMovie, name){
        HTTP.delete(`movies/${idMovie}`)
        .then(this._successHandler)
        .catch(this._errorHandler)
        Vue.notify({
               text: "The film "+ "'"+name+ "'"+" has been deleted",
               type: 'success'})
      },

    ocultar(idMovie,movie){
        movie.hidden = true
        HTTP.put(`movies/${idMovie}`, movie)
        .then(this._successHandler)
        .catch(this._errorHandler)
        Vue.notify({
               text: "The film "+ "'"+movie.name+ "'"+" has been hidden",
               type: 'success'})
      },

     mostrar(idMovie,movie){
        movie.hidden = false
        HTTP.put(`movies/${idMovie}`, movie)
        .then(this._successHandler)
        .catch(this._errorHandler)
        Vue.notify({
               text: "The film "+ "'"+movie.name+ "'"+" has been shown",
               type: 'success'})
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
    margin-top :50px;
    width:25%;
    height:15%;
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
    margin-right:30px;
    font-size: 20px;
    text-align: center;
    text-transform: uppercase;

      
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
    margin-left:15px;
    margin-top:10px;
    margin-bottom:30px;
    width:80%;
    padding:20px;
    height:60%;
    background-color: black; 
    border-radius: 20px 20px 20px 20px
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

.subtitle-tag {
      font-size: 10px;
      font-weight: 600;
      color: red;

    }

.media{
    margin-right:15px;
     float:right;
     width: 70px;
     height: 70px;
     border-radius: 50%;
     background: black;
}

.media1{
    margin-top:20px;
    width: 70px;
    height: 70px;
     
}

.ratingt{
   float:left;
   margin-left:35px;
   font-size: 15px;
}


</style>

