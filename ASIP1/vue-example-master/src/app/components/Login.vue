<template>
  <form @submit.prevent="userLogin">
    
    
    <div class= "formulario">
      <div class ="contenido" align="middle">
      <h1 class= "titulo" align="middle">Sign in! </h1>
    </div>
    <div class="form-group">
      <label for="username">Username</label>
      <input
        id="username"
        v-model="username"
        name="username"
        placeholder="Write your login"
        type="text"
        class="form-control"
        required>
    </div>
    <div class="form-group">
      <label for="password">Password</label>
      <input
        id="password"
        v-model="password"
        type="password"
         placeholder="Write your password"
        class="form-control"
        name="password"
        required>
    </div>
    <div class="registro">
      <div align="middle">
       <button
         type="submit"
         class="btn btn-primary">Log In</button> </div>
         <div align="middle">
         <b-btn 
              :to="{ name: 'Registro'}"
              variant="link">If you don´t have an account, create it!</b-btn>
        </div>
    </div>
       
     </div>
  </form>
</template>

<script>
import auth from '../common/auth'
export default {
  data () {
    return {
      username: '',
      password: '',
      error: null
    }
  },
  methods: {
    userLogin() {
      auth.login({
        login: this.username,
        password: this.password
      })
      .then(response => {
        this.$router.go(-1)
      })
      .catch(err => {
        this.error = err.response.data.message
      })
    }
  }
}
</script>
<style scoped lang="scss">
.formulario{
  width:40%;
  margin-left:30%;
  margin-top:10%;

  
}

.registro{
   margin-top:5%;
}




</style>