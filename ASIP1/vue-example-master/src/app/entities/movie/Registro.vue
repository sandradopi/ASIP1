<template>
<LoadingPage
    :loading="loading"
    :error="error">
    <div class="float-right">
      <b-btn
        variant="primary"
        @click="back()">Back</b-btn>
      <b-btn
        variant="primary"
        @click="save()">Create</b-btn>
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
        label="Name:"
        label-for="name">
        <b-form-input
          id="name"
          v-model="user.name"
          type="text"
          required
          placeholder="Enter your name"/>
      </b-form-group>

      <b-form-group
        label="First surname:"
        label-for="surname1">
        <b-form-input
          id="surname1"
          v-model="user.surname1"
          type="text"
          required
          placeholder="Enter your first surname"/>
      </b-form-group>

      <b-form-group
        label="Second surname:"
        label-for="surname2">
        <b-form-input
          id="surname2"
          v-model="user.surname2"
          type="text"
          required
          placeholder="Enter your second surname"/>
      </b-form-group>

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
        label="Phone number:"
        label-for="phone">
        <b-form-input
          id="phone"
          v-model="user.phone"
          type="text"
          required
          placeholder="Enter your phone"/>
      </b-form-group>

    </b-form>
</div>
</LoadingPage>
</template>

<script>
import { HTTP } from '../../common/http-common'
import LoadingPage from '../../components/LoadingPage'

export default {
  components: { LoadingPage },
  data() {
    return {
      user:null,
      error: null,
      loading: false,
    }
  },
 
  created() {
      this.user = {}
    
  },
   methods: {
    save() {
 
        HTTP.post('users', this.user)
        .then(this._successHandler)
        .catch(this._errorHandler)
      
    },
    back() {
      this.$router.go(-1)
    },
    _successHandler(response) {
       this.$router.replace({ name: 'MovieListUsuarioNormal', params: { id: response.data.login }})
     },
     _errorHandler(err) {
       this.error = err.response.data.message
     }
  }
}
</script>

