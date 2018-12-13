<template>
<div>
    <div
      v-if="error"
      class="error">
      <pre>{{ error }}</pre>
    </div>
<div class="botones">
  <div class="peliculas1">
    <b-btn class="personal"
          @click="bolAuxNeg()"
          variant="primary">Personal Information</b-btn> <!--bolAuxNeg sirve para controlar la variable bol que hace que si esta a false solo se vea la informacion personal de la persona, y si esta a true hace que se vea solo el componente MovieListAux, donde el contenido seran las pelis vistas o pendientes-->
</div>
 <div class="peliculas1">
    <b-btn class="viewed"
          @click="bolAux('vista')"
          variant="primary">Viewed Films</b-btn> <!--bolAux cambia la variable bol a true para que solo se vea el componente MovieListAux y le pasamos el tipo que luego le pasaremos al componente movieListAux para que sepa si listar pendientes o vistas-->
</div>

<div class="peliculas2">
    <b-btn class="pending"
          @click="bolAux('pendiente')"
          variant="primary">Pending Films</b-btn> 
</div>
</div>
  <div class= "datosUsuario" v-if="bol==false">
     <b-btn
                    class="editado"
                    v-if=" WhatLogin() ==this.user.login && this.control==false"
                    :to="{ name: 'UserUpdate', params: { id: user.login }}"
                    variant="primary"
                    @click="Editado()">Edit</b-btn> <!--Solo podemos editar las notificaciones del usuario que esta logueado-->
      <b-btn
                    class="editado"
                    v-if="this.control==true "
                    variant="primary"
                    @click="Save()">Save</b-btn>

     <h1 class="title" :key="user.login"> {{user.login}}</h1>  
    <img class="imagen" src="user.jpg ">
     <h4 class="subtitle">
      </br>
                  <div class="subdatos" >
                  <p class="subtitle-tag">Email: {{this.user.email}} </p>
                  <p class="subtitle-tag"
                   v-if = "WhatLogin() ==this.user.login"> Notifications by: {{this.user.noti}} </p>
                  <p class="subtitle-tag">Fecha de registro: {{this.user.data}} </p>
                  <p class="subtitle-tag1">Peliculas pendientes: {{this.user.countPendiente}} |</p>
                  <p class="subtitle-tag1">Peliculas vistas: {{this.user.countVista}} |</p>
                  <p class="subtitle-tag1">Peliculas valoradas: {{this.user.countValoration}} </p>
                 </div>
     </h4>    
      
 </div>



 <div class= "Noti" 
  v-if= "WhatLogin() ==this.user.login && this.control==true && bol==false"> <!--control controla que salga en la pantalla o el boton de edit o el de save-->
   <b-form-checkbox class="cheeck"
                       v-model="statu"
                       value= "true"
                       unchecked-value= "false"
                       @change="">
       <div><strong>Would you like to receive notifications?</strong></div>
      </b-form-checkbox>


  <div class="multi" v-if="this.statu=='true'">
  <h6>Notifications:</h6>
      <multiselect 
        v-model="user.noti" 
        :options= options
        :allow-empty="false"
        :searchable="false"
        :show-labels="false"
        placeholder="Select one">
      </multiselect>
      <link rel="stylesheet" href="https://unpkg.com/vue-multiselect@2.1.0/dist/vue-multiselect.min.css">
</div>
 </div >
 <MovieListAux v-if="bol== true" v-bind:login="this.$route.params.id" v-bind:tipo="this.tipo" class="aux"></MovieListAux>
</div>
  
</template>

<script>
import { HTTP } from '../../common/http-common' 
import auth from '../../common/auth'
import Multiselect from 'vue-multiselect'
import Vue from 'vue'
import MovieListAux from '../../entities/movie/MovieListAux'


export default {
  components: {  Multiselect, MovieListAux },
  data() {
    return {
      error: null,
      user:{},
      control:false, //Es una variable de control para saber cuando tiene que aparecer el boton de Editar y el de Save
      options: ['SMS','EMAIL'],
      statu:"false", //Estado del cheeckbox
      bol:false, //Variable de control para que se vea en pantalla o los datos del perfil o los listados
      tipo:"" //Tipo que le pasamos al componente MovieListAux para que sepa si listar vistas o pendientes


    }
  },
  watch: {
    '$route': 'fetchData',
    
  },
 
  created() { //se va a lanzar siempre en una clase de componentes
    this.fetchData()
  },
  methods: {
    fetchData() {

      if(this.$route.params.bol==false){
        this.bol=false;
       
      }

      if(this.bol!=true){

     HTTP.get(`users/detail/${this.$route.params.id}`) 
    .then(response => {
       this.user = response.data
       return response
       
     })

    .then(() => { 
        if(this.user.noti!=null){
        this.statu="true";
      }

      else if(this.user.noti==null){
        this.statu="false";
      }
      })

     .catch(err => {
       this.error = err.message
     })

  }
    },
     WhatLogin() {
      return auth.user.login
    },

    bolAux(tipologia){
      if (tipologia=='vista'){

        this.tipo='Viewed Movies';
      }else if (tipologia=='pendiente'){
        this.tipo='Pending Movies'
      }
      this.bol=true;
      
    },
    bolAuxNeg(){
      this.bol=false;
      this.tipo=''
    },

     Control() {

        if (this.user.noti == null){
          this.statu=="false"
        }else{
          this.statu=="true"
        }
    },

    Editado() {
      this.control=true;
    },
  

     Save() {

      if(this.statu=="true" && this.user.noti==null){
          Vue.notify({
               text: 'Select one option please',
               type: 'error'})
      }else{

      this.control=false;
      if(this.statu=="false"){
        this.user.noti="no";
      }

          HTTP.put(`users/${this.$route.params.id}/${this.user.noti}`)
         .then(this._successHandler)
         .catch(err => {
           this.error = err.message
         })
  }
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

.Noti{
  margin-top:20px;
  margin-left:80px;
}

.peliculas1{
  float:left;

}


.botones{
  margin-left:70px;
  margin-top:90px;
  width:32.6%;
  background-color:#007bff;

}

 .datosUsuario{
    margin-left:70px;
    background-color:black;
    padding:3%;
    width:90%;

  }

  .aux{
    background-color:black;
    margin-left:70px;
    width:90%;
  }

  .subdatos{
    margin-top:30px;

  }
  .multi{
    width:40%;
    margin-top:20px;
  }

  .title{
    margin-left:185px;
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

   

    .editado{
    float:right;
    margin-top:10px;
    margin-left:10px;
    border: none;
    color: white;
    padding: 8px 28px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 12px;
    border-radius: 8px;
    -webkit-transition-duration: 0.4s;
    transition-duration: 0.4s;
  }

  .editado:hover {
    color: white;
  
  }
  .imagen{
    float:left;
    width:15%;
    margin-right:20px;
    border-radius: 20px 20px 20px 20px
 
    }

</style>
