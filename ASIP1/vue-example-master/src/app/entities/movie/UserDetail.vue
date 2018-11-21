<template>
  <LoadingPage
    :loading="loading"
    :error="error">
    <div
      v-if="error"
      class="error">
      <pre>{{ error }}</pre>
    </div>

  <div class= "datosUsuario">
     <h1 class="title"> {{user.login}}</h1>  
     <h4 class="subtitle">
                  <div class="subdatos">
                  <p class="subtitle-tag">Email: {{user.email}} </p>
                  <p class="subtitle-tag1">Peliculas pendientes: {{this.count1}} |</p>
                  <p class="subtitle-tag1">Peliculas vistas: {{this.count}} |</p>
                  <p class="subtitle-tag1">Peliculas valoradas: {{this.count2}} </p>
                 </div>
     </h4>    
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
      error: null,
      user:null,
      count:null,
      count1:null,
      count2:null,

    }
  },
 
  created() { //se va a lanzar siempre en una clase de componentes
    this.fetchData()
  },
  methods: {
    fetchData() {

    this.loading = true
     HTTP.get(`users/${this.$route.params.id}`) 
    .then(response => {
       this.user = response.data
       return response
     })

    .then(this.contarVistas)
    .then(this.contarPendientes)
    .then(this.contarValoradas)
     .catch(err => {
       this.error = err.message
     })
    .finally(() => this.loading = false)


    },

    contarVistas(){

     HTTP.get(`status/movies/vistas/${this.user.login}`)
    .then(response => {
       this.count = response.data
       return response
     })
  },

  contarValoradas(){

     HTTP.get(`status/movies/valoration/${this.user.login}`)
    .then(response => {
       this.count2 = response.data
       return response
     })
  },

  contarPendientes(){

     HTTP.get(`status/movies/pendientes/${this.user.login}`)
    .then(response => {
       this.count1 = response.data
       return response
     })
  },

    _successHandler(response) {
      this.fetchData()
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

 .datosUsuario{
    background-color:black;
    padding:3%;
    width:80%;
  }

  .subdatos{
    margin-top:30px;

  }

  .title{
    font-size: 50px;
    font-weight: 700;
    color: white;
  }

  .subtitle {
      font-size: 15px;
      font-weight: 600;
      color: red;
      .subtitle-tag1 {
       display: inline;
        }
    }

</style>
