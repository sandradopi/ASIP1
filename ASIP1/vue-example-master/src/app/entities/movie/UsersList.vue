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
    <div class= "namemovie" v-for="user in users" >
    <div class= "tittle">
      {{ user.login }} 
        <router-link
         :to="{ name: 'UserDetail', params: { id: user.login }}">
         <img class="imagen" src="user.jpg ">
        </router-link>

        <div class="ratingt" >Viewed Films:</div>
      <div class ="media" >
        <div class="media1" > {{user.countVista}}
        </div>
      </div>

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
    margin-top :50px;
    margin-left:25px;
    width:15%;
    float:left;

  }



  .imagen{
    width:90%;
    margin-bottom:10px;
    margin-top:15px;
    background-color: black;
    border-radius: 20px 20px 20px 20px

    
    }

  .tittle{
    margin-right:30px;
    font-size: 20px;
    text-align: center;
    text-transform: uppercase;

      
    }
    .media{
    margin-right:15px;
     float:right;
     width: 30px;
     height: 30px;
     border-radius: 50%;
     background: #804c51;
}

.media1{
 
    width: 30px;
    height: 30px;
     
}

.ratingt{
   float:left;
   margin-left:18px;
   margin-top:5px;
   font-size: 13px;
}

  



</style>

