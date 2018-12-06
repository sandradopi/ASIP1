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
    </br>
    </br>
    </br>
     <div class="datosPelicula">
     <div class= "botoncillos" v-if="!isAdmin">
       
      <b-form-checkbox class="cheeck"
                       v-model="statu"
                       v-if="this.rating==null && statup=='nopendiente-pendiente'"
                       value="vista-novista"
                       unchecked-value="novista-vista"
                       @change="checkboxFuction">
       <div><strong>Viewed Film</strong></div>
      </b-form-checkbox>
      <b-form-checkbox class="cheeck1"
                       v-if="statu=='novista-vista'"
                       v-model="statup"
                       value="pendiente-nopendiente"
                       unchecked-value="nopendiente-pendiente"
                       @change="checkboxFuctionPen">
       <div><strong>Pending Film</strong></div>
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
 <div class= "contenido">
              <h1 class="title">
                {{movie.name}}
              </h1>
              <h4 class="subtitle">
                <p class="subtitle-tag">{{ movie.duration}} minutes |</p>
                <p class="subtitle-tag">{{ movie.genre.type}} | </p>
                <p class="subtitle-tag">{{movie.data}} |</p>
                <p class="subtitle-tag">Approximate average Rating: {{this.media}} </p>
              </h4>
              <p class="description">{{ movie.summary }}</p>
              <h4>
              </br>
              <p class="actors">Actors: {{ actorsAsString }}</p>
              <p class="directors">Directors: {{ directorsAsString }}</p>
            </h4>
          </div>
<img class="imagen"  v-if="!isAdmin" v-bind:src="'http://localhost:8080/api/movies/list/imagenes/'+ movie.idMovie">
           
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
      rating:null,
      media:null  
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
        this.statup="pendiente-nopendiente";
      }
      })

      .catch()

      HTTP.get(`movies/media/${this.$route.params.id}`) 
      .then(response => {
        this.media = response.data
      })
      .catch(err => this.error = err.message)
      .finally(() => this.loading = false)



    },
   

    puntuar(){
      HTTP.put(`status/movies/${this.$route.params.id}/${this.rating}`)
        .then(response => {this.rating = response.data.valoration
          return response})
        .then(this.fetchData)
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
        .then(this.fetchData)
        .catch(this._errorHandler)
      },

      mostrar(idMovie,movie){
        this.hide=false
        movie.hidden = false
        HTTP.put(`movies/${idMovie}`, movie)
        .then(this.fetchData)
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

  .datosPelicula{
    background-color:black;
    padding:3%;
    width:80%;
    height:100%;
    margin-left:120px;

  }
  .oculto{
     width:40%;
  }

  .mostra{
     width:40%;
  }


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
    margin-bottom:20px;
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
    float:left
  }

  .eliminado:hover {
    background-color: #f44336;
    color: white;
  
}
.ocultado {
    margin-left:10px;
    margin-bottom:20px;
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
    float:left

  }

  .ocultado:hover {
    background-color: #555555;
    color: white;
  
}



.mostrar{
    margin-left:10px;
    margin-bottom:20px;
    background-color: green; 
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
    float:left

}

  .puntuation{
    margin-top:10px;
    margin-left:25px;

  }

  .subtitle {
      font-size: 15px;
      font-weight: 600;
      color: red;


      .subtitle-tag {
       display: inline;
        }
    }

  .description {
      font-size: 15px;
      margin-bottom: 24px;
      max-width: 450px;
      font-style:italic;
    }

  .actors{
    font-size: 15px;
    margin-bottom: 24px;
    max-width: 450px;
  }

  .directors{
    font-size: 15px;
    margin-bottom: 24px;
    max-width: 450px;
    
  }
  .imagen{

    width:30%;
    height:10%;
 
    }

    .botoncillos{
      margin-top:30px;
      float:right;
      width:60%;
      height:50%;



    }



    .contenido{
      margin-top:40px;
      float:right;
      margin-right:120px;
      


    }

</style>
