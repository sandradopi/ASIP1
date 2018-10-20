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
      <b-btn
        variant="primary"
        @click="enter()">Enter</b-btn>
    </div>
</div><!--En vue solo podemos tener un unico elemento, por eso todo en un div-->
</template>

<style scoped lang="scss">
	.login{
		margin-top: 60px;
	}
	.contenido{
		margin-top: 60px;
	}
	.button{
		margin-top: 20px;
	}


</style>
