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
        label="Login:"
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
        label="Password:"
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
        label="Email:"
        label-for="email">
        <b-form-input
          id="email"
          v-model="user.email"
          type="email"
          autocomplete="off"
          required
          placeholder="Enter your email"/>
      </b-form-group>
    </b-form>
  </div>
     <div
      v-if="error"
      class="error"
      ifd= "error">
      <pre>{{ error }}</pre>
    </div>
     
</div>
</div>
</template>

<script>
import { HTTP } from '../../common/http-common'
import auth from '../../common/auth'

export default {
  data() {
    return {
      user: {},
      error: null
    }
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
    save() {
        return HTTP.post('register', this.user)
        .then(this.userLogin)
        .catch(this._errorHandler)

      
    },
    back() {
      this.$router.go(-1)
    },
    _successHandler(response) {
      this.$router.replace({ name: 'MovieList'})
    },
      _errorHandler(err) {
      this.error = err.response.data.message
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

