<template>
  <div class="fondo">
      <div
      v-if="error"
      class="error">
      <pre>{{ error }}</pre>
    </div>
    <br/>
    <h1 class="princ" v-if="tipo != null"> {{tipo}}</h1>
     <h1 class="princ" v-if="tipo == null"> {{titulo}}</h1>
    <div class= "namemovie" v-for="movie in movies" :key="movie.idMovie">
    <div class= "tittle">
        {{ movie.name }}
        <router-link 
         :to="{ name: 'MovieDetail', params: { id: movie.idMovie }}">
           <img class="imagen" v-bind:src="'http://localhost:8080/api/movies/list/imagenes/'+ movie.idMovie">
            <img class="imagen"  v-if="(movie.idMovie==9 || movie.idMovie>9)" src="movie.jpg">
        </router-link>
         <div class="ratingt" >Average Rating:</div>
          <div class ="media" >
        <div class="media1" > {{ movie.media}}
        </div>
      </div>
    </div>
    </br>
    </div>
</div>
</template>

<script>
import { HTTP } from '../../common/http-common'
import LoadingPage from '../../components/LoadingPage'
import auth from '../../common/auth'

export default {
  components: { LoadingPage},
  props:{
    tipo:null
  },
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
    debugger
    this.fetchData()
    
  },
  methods: {
  fetchData() {
    

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
    margin-bottom:20px;
    width:100%;

  }

  .namemovie {
    margin-top :50px;
    margin-left :20px;
    width:18%;
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
    .subtitle-tag {
      font-size: 10px;
      font-weight: 600;
      color: red;

    }

.media{
    margin-right:15px;
    float:right;
     width: 40px;
     height: 40px;
     border-radius: 50%;
     background: black;
}

.media1{
    float:right;
    margin-top:5px;
    width: 50px;
    height: 50px;
     
}

.ratingt{
  margin-top:7px;
   float:left;
   margin-left:15px;
   font-size: 15px;
}


</style>



