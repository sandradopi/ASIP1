<template>
<div>
  <div class ="contenido" align="middle">
	  	<h1 align="middle">Welcome to your Film Library! </h1>
	  	<div class ="login">
		  <label for="login" class="control-label">Login: &nbsp </label>
		  <input type="text" size="16" id="login" placeholder="Write your login">
		</div>

		 <br/>

		<div class ="password">
		  <label for="password" class="control-label">Password: &nbsp</label>
		  <input type="password" id="password"size="20"placeholder="Write your password">
		</div>
  </div>

  <div class="button" align="middle">
	   <button
         class="btn btn-primary"
         @click="getUsers">Enter</button>

	   <br/>
	   <b-btn
	          :to="{ name: 'Registro'}"
	          variant="sumit">If you don´t have an account, create it!</b-btn>
  
  </div>
</div><!--En vue solo podemos tener un unico elemento, por eso todo en un div-->
</template>

<script>

export default {
  data() {
    return {
      allUsers: [],
      encontrado:null,
      valor:document.getElementById("login").value
    }
  },  
  
  methods: {
    getUsers() {
      HTTP.get('users')
      .then(response => this.allUsers = response.data)
      .catch(err => this.error = err.message)
      encontrado = allUsers.find(function(element) {
  		return element == valor;}); //si encontrado es undefined es que el usuario no existe
      if(encontrado != "undefined"){ 
      	this.$router.replace({ name: 'MovieListUsuarioNormal', params: { id: valor}})}
      else{this.$router.replace({ name: 'Home', params: { id: "Este login no "}})}
    },
  }
}

</script>

<style scoped lang="scss">
	.login{
		margin-top: 50px;
	}
	.contenido{
		margin-top: 60px;
	}
	.button{
		margin: 20px;
	}
	.registro{
		margin: 20px;
	}

</style>

