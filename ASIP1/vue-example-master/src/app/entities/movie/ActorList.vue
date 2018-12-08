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
    {{ actor.name }} {{actor.surname1}}
        <router-link
         :to="{ name: ''}">
        <img class="imagen" src="perfil.jpg">
        </router-link>
    </div>
    <div class="buttone" >
     <b-btn
        class="eliminado"
        variant="primary"
        @click="eliminar(actor.idActor, actor.name, actor.surname1)">Delete</b-btn>

      <b-btn
          class="editado"
           :to="{ name: 'ActorUpdate', params: { id: actor.idActor }}"
           variant="primary">Edit</b-btn>

     </div>
    
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
    eliminar(idActor,name, surname1){
        HTTP.delete(`actors/${idActor}`)
        .then(response => {
          this.noti = response.data
          return response
        })
        
        .then(() => { 
            if (this.noti=="fracaso"){
            Vue.notify({
               text: 'You can´t delete this actor because he/she acts in somo movies',
               type: 'error'})
            }
            else if (this.noti=="exito"){
              Vue.notify({
               text: 'The actor '+ "'"+name +" " +surname1+ "'"+' has been delete',
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



