<template>
   <form @submit.prevent="userLogin">
     <div
       v-if="error"
       class="error">
       <pre>{{ error }}</pre>
     </div>
      <div class ="contenido" align="middle">
	  	<h1 align="middle">Welcome to your Film Library! </h1>
	  </div>
     <div class="form-group">
       <label for="username">Username</label>
       <input
         id="username"
         v-model="username"
         name="username"
         type="text"
         size="20"
         placeholder="Write your login"
         class="form-control"
         required>
     </div>
     <div class="form-group">
       <label for="password">Password</label>
       <input
         id="password"
         v-model="password"
         type="password"
         size="20"
         placeholder="Write your password"
         class="form-control"
         name="password"
         required>
     </div>

     <div align="middle">
     <button
       type="submit"
       class="btn btn-primary">Log In</button> </div>
       <div align="middle">
       <b-btn
	          :to="{ name: 'Registro'}"
	          variant="sumit">If you don´t have an account, create it!</b-btn>
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
	
	
</style>

