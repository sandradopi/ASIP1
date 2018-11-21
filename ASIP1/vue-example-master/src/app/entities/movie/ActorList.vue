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
     <h1 class="princ"> Actors</h1>
    <div class= "nameactor" v-for="actor in actors" :key="actor.idActor">
    <div class= "tittle">
        <router-link
         :to="{ name: ''}">
          {{ actor.name }} {{actor.surname1}}
        </router-link>
    </div>
    <div class="buttone" >
     <b-btn
        class="eliminado"
        variant="primary"
        @click="eliminar(actor.idActor)">Delete</b-btn>

      <b-btn
          class="editado"
           :to="{ name: 'ActorUpdate', params: { id: actor.idActor }}"
           variant="primary">Edit</b-btn>

     </div>
    <img class="imagen" src="perfil.jpg">
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
      actors: null,
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
    eliminar(idActor){
        HTTP.delete(`actors/${idActor}`)
        .then(response => {
          this.noti = response.data
          return response
        })
        
        .then(() => { 
            if (this.noti=="fracaso"){
            Vue.notify({
               text: 'You can´t delete this actor because He/She acts in somo movies',
               type: 'error'})
            }
            else if (this.noti=="exito"){
              Vue.notify({
               text: 'The actor has been delete',
               type: 'success'})
            }

          })
        .then(this._successHandler)
        
        .catch(this._errorHandler)
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
  
  .nameactor {
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
   
.buttone{
    margin-top:10px;
    width:30%;
    height:40%;
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



