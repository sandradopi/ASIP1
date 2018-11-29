<template>
  <LoadingPage
    :loading="loading"
    :error="error">
    <div
      v-if="error"
      class="error">
      <pre>{{ error }}</pre>
    </div>
  <div class= "datosUsuario" >
     <b-btn
                    class="editado"
                    v-if=" WhatLogin() ==this.user.login && this.control==false"
                    variant="primary"
                    @click="Editado()">Edit</b-btn>
      <b-btn
                    class="editado"
                    v-if="this.control==true "
                    variant="primary"
                    @click="Save()">Save</b-btn>

     <h1 class="title" :key="user.login"> {{user.login}}</h1>  
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

 <div class= "Noti" 
  v-if= "WhatLogin() ==this.user.login && this.control==true">
  <div class="multi">
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

  </LoadingPage>
</template>

<script>
import { HTTP } from '../../common/http-common' 
//LIBRERIA AXIOS (todas las peticiones al 8080 al servidor)
import LoadingPage from '../../components/LoadingPage'
import auth from '../../common/auth'
import Multiselect from 'vue-multiselect'


export default {
  components: { LoadingPage, Multiselect },
  data() {
    return { //datos que usamos
      loading: false,
      error: null,
      user:{},
      control:false,
      options: ['SMS','EMAIL'],

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

    this.loading = true
     HTTP.get(`users/detail/${this.$route.params.id}`) 
    .then(response => {
       this.user = response.data
       
     })
     .catch(err => {
       this.error = err.message
     })
    .finally(() => this.loading = false)


    },
     WhatLogin() {
      return auth.user.login
    },
    Editado() {
      this.control=true;
    },

     Save() {
      this.control=false;

          HTTP.put(`users/${this.$route.params.id}/${this.user.noti}`)
         .then(this._successHandler)
         .catch(err => {
           this.error = err.message
         })

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
    width:80%;
  }

  .subdatos{
    margin-top:30px;

  }
  .multi{
    width:40%;
    margin-top:20px;
  }

  .title{
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

</style>
