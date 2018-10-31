<template>
  <LoadingPage
    :loading="loading"
    :error="error">
    <div
      v-if="error"
      class="error">
      <pre>{{ error }}</pre>
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

    <div>
      <b-form-checkbox v-if="!isAdmin" class="cheeck"
                       v-model="statu"
                       value="vista"
                       unchecked-value="novista"
                       @click.native="checkboxFuction">
       <div><strong>Marcar Pelicula como vista</strong></div>

      </b-form-checkbox>
  </div>
      <br>
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

export default {
  components: { LoadingPage },
  data() {
    return { //datos que usamos
      loading: false,
      movie: null, //un dato que es post todo lo que visualicemos va a estar alli
      error: null,
      statu: 'novista',
      status: null
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
      //hace una peticion a nuestro cliente rest, url del api a un id concreto
      .then(response => this.movie = response.data)
      //pilla la respuesta y le asocia el valor al post (nos llega un jason {"id":valor;})
      .catch(err => this.error = err.message)
      .finally(() => this.loading = false)
    },
    checkboxFuction(){
      HTTP.post(`status/movies/${this.$route.params.id}/${this.statu}`, this.movie)
        //.then(response => this.movie = response.data)
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
    float:left;
  }
  .actors {
     font-style: italic;
   }

   .directors {
     font-style: italic;
   }
   .cheeck{
    margin-left:10%;
   }

   .contenido{
    margin-top:50px;
   }

</style>
