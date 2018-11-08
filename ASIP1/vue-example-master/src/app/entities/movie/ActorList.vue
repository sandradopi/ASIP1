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
         :to="{ name: 'ActorCreate' }"
         variant="primary">New</b-btn>
    <br/>
    <div class= "nameactor" v-for="actor in actors" :key="actor.idActor">
    <div class= "tittle">
        <router-link
         :to="{ name: ''}">
          {{ actor.name }} {{actor.surname1}}
        </router-link>
    </div>
    <img class="imagen" src="movie.jpg">
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
      actors: null,
      error: null
    }
  },
  
  created() {
    this.fetchData()
    
  },
  methods: {
  fetchData() {
      this.loading = true
     HTTP.get(`actors`)
    .then(response => {
       this.actors = response.data
     })
     .catch(err => {
       this.error = err.message
     })
    .finally(() => this.loading = false)
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
  .nameactor {
    margin-top :20px;
    width:28%;
    height:20%;
    font-size: big;
    float:left;
    padding:20px;
    float-left:20px;
    margin-left :10px;
    background-color: #ffffff;
    border-radius: 35px 35px 35px 35px
  }

  .imagen{
    width:40%;
    height:auto;
    float:left;
    
    }

  .tittle{
    float:right;
    text-align:middle;
    font-weight: bold;
    font-family: sans-serif;
    font: 150% sans-serif;
    font-size: 1.5vw;
      
    }
   


</style>



