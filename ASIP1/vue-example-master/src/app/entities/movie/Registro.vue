<template>
  <div>
    <div class="float-right">
      <b-btn
        variant="primary"
        @click="back()">Back</b-btn>
      <b-btn
        variant="primary"
        @click="union()">Create</b-btn>
    </div>
<div>
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
          required
          placeholder="Enter a login"/>
      </b-form-group>

      <b-form-group
        label="Password:"
        label-for="password">
        <b-form-input
          id="password"
          v-model="user.password"
          type="text"
          required
          placeholder="Enter a password"/>
      </b-form-group>


       <b-form-group
        label="Email:"
        label-for="email">
        <b-form-input
          id="email"
          v-model="user.email"
          type="text"
          required
          placeholder="Enter your email"/>
      </b-form-group>
    </b-form>

</div>
</div>
</template>

<script>
import { HTTP } from '../../common/http-common'

export default {
  data() {
    return {
      user: {},
      error: null,
      loading: false,
    }
  },
 
   methods: {
    userLogin() {
      auth.login({
        login: user.login,
        password: user.password
      })
      .then((response => {this.$router.replace({ name: 'MovieList'})}))
      .catch(this._errorHandler)
      
    },
    save() {
        return HTTP.post('register', this.user)
        .then(this._successHandler)
        .catch(this._errorHandler)

      
    },
    union() {
        this.save().then().catch()
      
    },
    back() {
      this.$router.go(-1)
    },
    _successHandler(response) {
        this.userLogin().then().catch()
       
     },
     _errorHandler(err) {
       this.error = err.response.data.message
     }
  }
}
</script>

