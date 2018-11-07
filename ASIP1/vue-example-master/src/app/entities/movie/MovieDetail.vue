<template>
  <LoadingPage
    :loading="loading"
    :error="error">
    <div
      v-if="error"
      class="error">
      <pre>{{ error }}</pre>
    </div>
    <div class="bottone">
          <b-btn
              class="eliminado"
              v-if="isAdmin"
              variant="primary"
              @click="eliminar(movie.idMovie)">Delete</b-btn>

          <b-btn
              class="ocultado"
              v-if="isAdmin"
              variant="primary"
              @click="ocultar(movie.idMovie)">Hide</b-btn>
    </div>
    <div v-if="movie">
      <div class="float-right">
        <b-btn
          variant="primary"
          @click="back()">Back</b-btn>
          <b-btn
           v-if="isAdmin"
           :to="{ name: 'MovieUpdate', params: { id: movie.idMovie }}"
           variant="primary">Edit</b-btn>
          
      </div>
     <h3 class= "nameFilm">{{movie.name}}</h3> 
    <div v-if="!isAdmin">
      <b-form-checkbox  class="cheeck"
                       v-model="statu"
                       value="vista-novista"
                       unchecked-value="novista-vista"
                       @change="checkboxFuction">
       <div><strong>Marcar Pelicula como vista</strong></div>
      </b-form-checkbox>

      <div class= "puntuation" v-if = "statu == 'vista-novista'">
      <strong>Puntuation:</strong>
      <star-rating class="star"
        v-model="rating"
        @rating-selected= "puntuar()"
         v-bind:star-size="30"
      ></star-rating>
      </div>
     

  </div>
</br>
</br>
      <div class= "contenido">
          <h5>Release date: {{movie.data}}</h5>
          <h5>Duration: {{ movie.duration}}</h5>
          <h5>Genre: {{ movie.genre.type}}</h5>
          <h5>Actors:</h5>
           <div class="actors">{{ actorsAsString }}</div>
          </br>
          <h5>Directors:</h5>
           <div class="directors">{{ directorsAsString }}</div>
          <hr>
          <h5>Movie´s summary:</h5>
          <div class="movie">{{ movie.summary }}</div> 

      </div>
    </div>
  </LoadingPage>
</template>

<script>
import { HTTP } from '../../common/http-common' 
//LIBRERIA AXIOS (todas las peticiones al 8080 al servidor)
import LoadingPage from '../../components/LoadingPage'
import auth from '../../common/auth'
import StarRating from 'vue-star-rating'

export default {
  components: { LoadingPage ,  StarRating},
  data() {
    return { //datos que usamos
      loading: false,
      movie: null, //un dato que es post todo lo que visualicemos va a estar alli
      error: null,
      statu: "novista-vista",
      status: null,
      rating:null      //this.$refs.component.note
    }
  },
  computed: {
     isAdmin() {
       return auth.isAdmin()
     
   },
   actorsAsString() {
       return this.movie.participantes.map(t => t.name +" "+ t.surname1).join(', ')
     },
    directorsAsString() {
       return this.movie.dirigentes.map(t => t.name +" "+ t.surname1).join(', ')
     }
  },
  watch: {
    '$route': 'fetchData'
  },
  created() { //se va a lanzar siempre en una clase de componentes
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.error = this.movie = null
      this.loading = true

      HTTP.get(`movies/${this.$route.params.id}`) 
      .then(response => this.movie = response.data)
      .catch(err => this.error = err.message)
      .finally(() => this.loading = false)

      HTTP.get(`status/movies/${this.$route.params.id}`)
      .then(response => this.rating = response.data.valoration)
      //.then(response => this.status = response.data)
      .catch()

      /*if(this.status.type=="VISTA"){
        this.statu="vista-novista";
      }
      else{
        this.statu="novista-vista";
      }*/

      
    },

    puntuar(){
      HTTP.put(`status/movies/${this.$route.params.id}/${this.rating}`)
        .then(response => this.rating = response.data.valoration)
        .catch(this._errorHandler)
    },

    checkboxFuction(){
      if(this.statu =="novista-vista" ){
           HTTP.post(`status/movies/${this.$route.params.id}/${this.statu}`)
            .catch(this._errorHandler)}
      else if (this.statu =="vista-novista" ){
          HTTP.delete(`status/movies/${this.$route.params.id}/${this.statu}`)
          .catch(this._errorHandler)}
    },
    eliminar(idMovie){
        HTTP.delete(`movies/${idMovie}`)
        .then(this._successHandler)
        .catch(this._errorHandler)
      },

    _successHandler(response) {
      this.$router.replace({ name: 'MovieList'})
    },

    ocultar(idMovie){
        
      },

    back() {
      this.$router.go(-1)
    },
    _errorHandler(err) {
      this.error = err.response.data.message
    }
  }
}
</script>

<style scoped lang="scss">
  .movie {
    white-space: pre;
  }
  .nameFilm{
    margin-top:20px;

  }

  .actors {
     font-style: italic;
   }

   .directors {
     font-style: italic;
   }
   .cheeck{
    margin-top:20px;
   }

   .contenido{
    margin-top:5px;
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

  .bottone{
    float:right;
    margin-bottom:10px;
  }

  .puntuation{
    margin-top:10px;
    margin-left:25px;

  }

  .star{

  }

</style>
