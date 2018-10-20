<template>
  <LoadingPage
    :loading="loading"
    :error="error">

    <div v-if="movie">
      <div class="float-right">
        <b-btn
          variant="primary"
          @click="back()">Back</b-btn>
        <b-btn
          :to="{ name: 'MovieUpdate', params: { id: movie.idMovie}}"
          variant="primary">Edit</b-btn>
      </div>
      <h3>{{ movie.name}}</h3> <!-- el que definimos en la parte javaScript -->
      <br>
      <h5>Release date: {{ movie.data}}</h5>
      <h5>Duration: {{ movie.duration}}</h5>
      <hr>
      <h5>Movie´s summary:</h5>
      <div class="movie">{{ movie.summary }}</div>
    </div>
  </LoadingPage>
</template>

<script>
import { HTTP } from '../../common/http-common' 
//LIBRERIA AXIOS (todas las peticiones al 8080 al servidor)
import LoadingPage from '../../components/LoadingPage'

export default {
  components: { LoadingPage },
  data() {
    return { //datos que usamos
      loading: false,
      movie: null, //un dato que es post todo lo que visualicemos va a estar alli
      error: null
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
    back() {
      this.$router.go(-1)
    }
  }
}
</script>

<style scoped lang="scss">
  .movie {
    white-space: pre;
  }
</style>
