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
         :to="{ name: 'DirectorCreate' }"
         variant="primary">New</b-btn>
    <br/>
     <h1 class="princ"> Directors</h1>
    <div class= "namedirector" v-for="director in directors" :key="director.idDirector">
    <div class= "tittle">
        <router-link
         :to="{ name: ''}">
          {{ director.name }} {{director.surname1}}
        </router-link>
    </div>
    <img class="imagen" src="director.jpg">
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
      directors: null,
      error: null
    }
  },
  
  created() {
    this.fetchData()
    
  },
  methods: {
  fetchData() {
      this.loading = true
     HTTP.get(`directors`)
    .then(response => {
       this.directors = response.data
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

.princ{
    font-size: 40px;
    letter-spacing: 8px;
    color: red;
    text-align: center;
    text-transform: uppercase;
  }
  
  .namedirector {
    margin-top :20px;
    width:28%;
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
    margin-left:70px;
    
    }

  .tittle{
    font-size: 20px;
    color: red;
    text-align: center;
    margin-bottom:10px;
    text-transform: uppercase;
      
    }

   


</style>



