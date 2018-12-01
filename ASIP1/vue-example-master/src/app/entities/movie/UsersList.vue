<template>
  <LoadingPage
    :loading="loading"
    :error="error">
      <div
      v-if="error"
      class="error">
      <pre>{{ error }}</pre>
    </div>
    <br/>
    <h1 class="princ"> Users</h1>
    <div class= "namemovie" v-for="user in users" :key="user.idUser">
    <div class= "tittle">
        <router-link
         :to="{ name: 'UserDetail', params: { id: user.login }}">
         {{ user.login }} {{user.countVista}}
        </router-link>
    </div>
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
      users: null,
      error: null,
      count:null,

    }
  },
  
  created() {
    this.fetchData()
    
  },
  methods: {
    fetchData() {

    this.loading = true
    HTTP.get('users/contadores') 
    .then(response => {
       this.users = response.data
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
    },    
    
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


  .namemovie {
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


  .tittle{
    font-size: 20px;
    color: red;
    text-align: center;
    margin-bottom:10px;
    text-transform: uppercase;

      
    }
   
  



</style>

