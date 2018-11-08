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
    
    <div v-if="actor">
      <div class="float-right">
        <b-btn
          variant="primary"
          @click="back()">Back</b-btn>
          <b-btn
           :to="{ name: 'ActorUpdate', params: { id: actor.idActor}}"
           variant="primary">Edit</b-btn>
          
      </div>
     <h3 class= "nameFilm">{{actor.name}}</h3> 
</br>
</br>
      <div class= "contenido">
          <h5>First Surname: {{actor.surname1}}</h5>
          <h5>Second Surname: {{actor.surname2}}</h5>
          <h5>Birthdate: {{ actor.birthdate}}</h5>

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
      actor: null, //un dato que es post todo lo que visualicemos va a estar alli
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
      this.error = this.actor = null
      this.loading = true

      HTTP.get(`actors/${this.$route.params.id}`) 
      .then(response => {
        this.actor = response.data
      })
      .catch(err => this.error = err.message)
      .finally(() => this.loading = false)

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
  .actor {
    white-space: pre;
  }
  .nameFilm{
    margin-top:20px;

  }

   .contenido{
    margin-top:5px;
   }


</style>