<template>
  <LoadingPage
    :loading="loading"
    :error="error">
    <div
      v-if="error"
      class="error">
      <pre>{{ error }}</pre>
    </div>
    <div class="buttone" v-if="isAdmin">
     <b-btn
        class="eliminado"
        variant="primary"
        @click="eliminar(movie.idMovie)">Delete</b-btn>

    <div class= "oculto" v-if= "this.hide==false">
     <b-btn
        class="ocultado"
        variant="primary"
        @click="ocultar(movie.idMovie,movie)">Hide</b-btn>
    </div>

    <div class= "mostra" v-if= "this.hide==true">
     <b-btn
        class="mostrar"
        variant="primary"
        @click="mostrar(movie.idMovie,movie)">Show</b-btn>
    </div>
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
       
      <b-form-checkbox class="cheeck"
                       v-model="statu"
                       v-if="this.rating==null && statup=='nopendiente-pendiente'"
                       value="vista-novista"
                       unchecked-value="novista-vista"
                       @change="checkboxFuction">
       <div><strong>Marcar Pelicula como vista</strong></div>
      </b-form-checkbox>
      <b-form-checkbox class="cheeck1"
                       v-if="statu=='novista-vista'"
                       v-model="statup"
                       value="pendiente-nopendiente"
                       unchecked-value="nopendiente-pendiente"
                       @change="checkboxFuctionPen">
       <div><strong>Marcar Pelicula como pendiente</strong></div>
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
          <div>{{this.statu}}</div> 
          <div>{{this.aux}}</div> 


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
      statup: "nopendiente-pendiente",
      hide:null,
      aux: "",
      rating:null    
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
      .then(response => {
        this.movie = response.data
        return response
      })
      .then(response => {this.hide= response.data.hidden})
      .catch(err => this.error = err.message)
      .finally(() => this.loading = false)

      HTTP.get(`status/movies/${this.$route.params.id}`)
      .then(response => {
        this.rating = response.data.valoration
        return response
      })
      .then(response => {
        this.aux = response.data.type
        return response
      })
      .then(() => { 
        if(this.aux=="VISTA"){
        this.statu="vista-novista";}
      else if(this.aux=="PENDIENTE"){
        this.statup="pendiente-nopendiente";}
      })

      .catch()

    },
    comprobarValoracion(){
      
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
          HTTP.delete(`status/movies/${this.$route.params.id}`)
          .catch(this._errorHandler)}
    },

    checkboxFuctionPen(){
      if(this.statup =="nopendiente-pendiente" ){
           HTTP.post(`status/movies/${this.$route.params.id}/${this.statup}`)
            .catch(this._errorHandler)}
      else if (this.statup =="pendiente-nopendiente" ){
          HTTP.delete(`status/movies/${this.$route.params.id}`)
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

     ocultar(idMovie,movie){
        this.hide=false
        movie.hidden = true
        HTTP.put(`movies/${idMovie}`, movie)
        .catch(this._errorHandler)
      },

      mostrar(idMovie,movie){
        this.hide=false
        movie.hidden = false
        HTTP.put(`movies/${idMovie}`, movie)
        .catch(this._errorHandler)
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

  .puntuation{
    margin-top:10px;
    margin-left:25px;

  }

  .star{

  }

</style>
