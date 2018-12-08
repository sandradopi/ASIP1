<template>
<div>
    <div
      v-if="error"
      class="error">
      <pre>{{ error }}</pre>
    </div>

  <div class= "datosUsuario" >
     <b-btn
                    class="editado"
                    v-if=" WhatLogin() ==this.user.login && this.control==false"
                    :to="{ name: 'UserUpdate', params: { id: user.login }}"
                    variant="primary"
                    @click="Editado()">Edit</b-btn>
      <b-btn
                    class="editado"
                    v-if="this.control==true "
                    variant="primary"
                    @click="Save()">Save</b-btn>

     <h1 class="title" :key="user.login"> {{user.login}}</h1>  
    <img class="imagen" src="user.jpg ">
     <h4 class="subtitle">
      </br>
                  <div class="subdatos">
                  <p class="subtitle-tag">Email: {{user.email}} </p>
                  <p class="subtitle-tag"
                   v-if = "WhatLogin() ==this.user.login"> Notifications by: {{user.noti}} </p>
                  <p class="subtitle-tag">Fecha de registro: {{user.data}} </p>
                  <p class="subtitle-tag1">Peliculas pendientes: {{user.countPendiente}} |</p>
                  <p class="subtitle-tag1">Peliculas vistas: {{user.countVista}} |</p>
                  <p class="subtitle-tag1">Peliculas valoradas: {{user.countValoration}} </p>
                 </div>
     </h4>    
      
 </div>


</br>
 <div class= "Noti" 
  v-if= "WhatLogin() ==this.user.login && this.control==true">
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

  </div>
</template>

<script>
import { HTTP } from '../../common/http-common' 
import auth from '../../common/auth'
import Multiselect from 'vue-multiselect'
import Vue from 'vue'


export default {
  components: {  Multiselect },
  data() {
    return { //datos que usamos
      error: null,
      user:{},
      control:false,
      options: ['SMS','EMAIL'],
      statu:"false",
      aux:null,

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

     HTTP.get(`users/detail/${this.$route.params.id}`) 
    .then(response => {
       this.user = response.data
       return response
       
     })
     .then(response => {
        this.aux = response.data.noti
        return response
      })

    .then(() => { 
        if(this.aux!=null){
        this.statu="true";
      }

      else if(this.aux==null){
        this.statu="false";
      }
      })

     .catch(err => {
       this.error = err.message
     })

    },
     WhatLogin() {
      return auth.user.login
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

 .datosUsuario{
    background-color:black;
    padding:3%;
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
