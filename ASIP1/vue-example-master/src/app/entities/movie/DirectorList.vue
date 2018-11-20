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
     <div class="buttone" >
     <b-btn
        class="eliminado"
        variant="primary"
        @click="eliminar(director.idDirector)">Delete</b-btn>

       <b-btn
          class="editado"
           :to="{ name: 'DirectorUpdate', params: { id: director.idDirector }}"
           variant="primary">Edit</b-btn>

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
    margin-left:100px;
    
    
    }

  .tittle{
    font-size: 20px;
    color: red;
    text-align: center;
    margin-bottom:10px;
    text-transform: uppercase;
      
    }

    .eliminado {
    margin-left:90px;
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

.editado {
    margin-left:10px;
    margin-bottom:10px;
    border: none;
    color: white;
    padding: 8px 29px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 12px;
    border-radius: 8px;
    -webkit-transition-duration: 0.4s;
    transition-duration: 0.4s;
    float:left

  }

  .editado:hover {
    color: white;
  
}

   


</style>



