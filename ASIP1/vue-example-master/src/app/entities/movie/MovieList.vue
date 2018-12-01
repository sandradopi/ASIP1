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
        <router-link
         :to="{ name: 'MovieDetail', params: { id: movie.idMovie }}">
          {{ movie.name }}
        </router-link>
    <img class="imagen"  v-if="!isAdmin" src="movie.jpg">
    </br>
     <p class="subtitle-tag">Approximate average Rating:{{ movie.media}}</p>
    </div>
    </div>
    <div class="buttone" v-if="isAdmin">
     <b-btn
        class="eliminado"
        variant="primary"
        @click="eliminar(movie.idMovie)">Delete</b-btn>

    <div class= "oculto" v-if= "movie.hidden==false">
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
      this.loading = true
    HTTP.get(`movies/media`)
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

  .princ{
    font-size: 40px;
    letter-spacing: 8px;
    color: red;
    text-align: center;
    text-transform: uppercase;
  }


  .namemovie {
    margin-top :20px;
    width:20%;
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
    margin-left:10px;
    margin-bottom:10px;
    margin-top:10px;

    
    }

  .tittle{
    width:100%;
    height:100%;
    font-size: 20px;
    color: red;
    text-align: center;
    margin-bottom:10px;
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

.subtitle-tag {
      font-size: 10px;
      font-weight: 600;
      color: red;

    }


</style>

