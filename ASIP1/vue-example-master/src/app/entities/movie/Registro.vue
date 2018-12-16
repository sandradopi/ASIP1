<template>
  <div>
    <div class="float-right">
      <b-btn
        variant="primary"
        @click="back()">Back</b-btn>
      <b-btn
        variant="success"
        @click="save()">Create</b-btn>
    </div>
<div>

  <div class="formulario">
  <div class ="contenido" align="middle">
      <h1 align="middle">Create your account </h1>
  </div>
  <b-form
      v-if="user"
      class="app-form"
      @submit="save">

      
      <b-form-group
        label="Login: *"
        label-for="login">
        <b-form-input
          id="title"
          v-model="user.login"
          type="text"
          autocomplete="off"
          required
          placeholder="Enter a login"/>
      </b-form-group>

      <b-form-group
        label="Password: *"
        label-for="password">
        <b-form-input
          id="password"
          v-model="user.password"
          type="password"
          autocomplete="off"
          required
          placeholder="Enter a password"/>
      </b-form-group>



       <b-form-group
        label="Email: *"
        label-for="email">
        <b-form-input
          id="email"
          v-model="user.email"
          type="email"
          autocomplete="off"
          required
          placeholder="Enter your email"/>
      </b-form-group>
      <b-form-checkbox class="cheeck"
                       v-model="statu"
                       value= "true"
                       unchecked-value= "false"
                       @change="">
       <div><strong>Would you like to receive notifications?</strong></div>
      </b-form-checkbox>

    </br>
    </br>
    <b-form-group v-if="statu=='true'">
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
      
  
  </b-form-group>
    </b-form>



  </div>
     
</div>
</div>
</template>

<script>
import { HTTP } from '../../common/http-common'
import auth from '../../common/auth'
import Multiselect from 'vue-multiselect'
import Vue from 'vue'
export default {
  components: {  Multiselect},
  data() {
    return {
      user: {},
      error: null,
      statu:null, //Estado del cheeck box
      options: ['SMS','EMAIL'],
      users:{} //Nos traemos los logins y emails unicamente al principio para luego comprobar si estan repetidos
    }

  },
  created() { 
    this.fetchData()
  },
 
   methods: {
    userLogin() {
      auth.login({
        login: this.user.login,
        password: this.user.password
      })
      .then(this._successHandler)
      .catch(this._errorHandler)
      
    },
     fetchData() {
      HTTP.get(`users/LoginEmail`) 
    .then(response => {
       this.users = response.data
     })
     .catch(err => {
       this.error = err.message
     })

    },


    checkForm () {
      if (!this.user.login) {
        this.errors = "Login is a required field."
        return false;
      } else{
         for(var i=0; i< this.users.length; i+=1){
          if (this.users[i].login == this.user.login){
            this.errors= "Login already exists "
            return false
          }
        }
      }

      if (!this.user.password) {
        this.errors ="Password is a required field. "
        return false;
      } else if (this.user.password.length <4 ) {
        this.errors ="The password is too short. Minimum 4 letters please"
        return false;
      }

      if (!this.user.email) {
        this.errors= "Email is a required field. "
        return false;
      }else{
        for(var i=0; i< this.users.length; i+=1){
          if (this.users[i].email == this.user.email){
            this.errors= "Email already exists "
            return false
          }
        }
      }


      var expr = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
      if (!expr.test(this.user.email)) {
        this.errors= "The email: "+ this.user.email +" don´t have the good format, review it "
        return false;
      }
      if (this.user.login && this.user.password && this.user.email) {
        return true;
      }
    },
    save() {

       if (this.checkForm() == true) {
    
        if(this.statu=='false'){
          this.user.noti=null;
        }
          return HTTP.post('register', this.user)
          .then(this.userLogin)
          .catch(this._errorHandler)
      }else{
        
        Vue.notify({
          text: this.errors,
          type: 'error'})
    
      }
      
    },
    notification(){ 
      Vue.notify({
               text: this.error,
               type: 'error'})
    },
    back() {
      this.$router.go(-1)
    },
    _successHandler(response) {
      this.$router.replace({ name: 'MovieList'})
    },
      _errorHandler(err) {
      this.error = err.response.data.message
      this.notification()
     }
  }
}
</script>

<style scoped lang="scss">
  .error{
    font: 150% sans-serif;
    font: message-box;
  }
  .formulario{
  width:40%;
  margin-left:30%;
  
}
 
</style>