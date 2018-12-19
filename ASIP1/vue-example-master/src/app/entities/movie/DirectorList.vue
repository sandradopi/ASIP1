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
      {{ director.name }} {{director.surname1}}
        <router-link
         :to="{ name: ''}">
         <img class="imagen" src="perfil.jpg">
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
           >Edit</b-btn>

     </div>
    
  </LoadingPage>
</template>

<script>
import { HTTP } from '../../common/http-common'
import LoadingPage from '../../components/LoadingPage'
import auth from '../../common/auth'
import Vue from 'vue'

export default {
  components: { LoadingPage},
  data() {
    return {
      loading: false,
      directors: null,
      error: null,
      noti:null
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

    eliminar(idDirector){
        HTTP.delete(`directors/${idDirector}`)
        .then(response => {
          this.noti = response.data
          return response
        })
        
        .then(() => { 
            if (this.noti=="fracaso"){
            Vue.notify({
               text: 'You can´t delete this director because He/She directs some movies',
               type: 'error'})
            }
            else if (this.noti=="exito"){
              Vue.notify({
               text: 'The director has been delete',
               type: 'success'})
            }

          })
        .then(this._successHandler)
        
        .catch(this._errorHandler)
      },

    _errorHandler(err) {
      this.error = err.response.data.message
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
    width:20%;
    height:20%;
    font-size: big;
    float:left;
    padding:8px;
    border-radius: 20px 20px 20px 20px
  }
  

  .imagen{
    margin-top:10px;
    width:50%;
    margin-left:7px;
    border-radius: 20px 20px 20px 20px
    
    }

  .tittle{
    font-size: 23px;
    color: white;
    text-align: center;
    margin-bottom:10px;
    text-transform: uppercase;
      
    }
   
.buttone{
    margin-left:30px;
    margin-top:20px;
    margin-bottom:30px;
    width:100%;
    background-color: black; 
    border-radius: 20px 20px 20px 20px
  }


.eliminado {
    margin-left:10px;
    margin-bottom:10px;
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



