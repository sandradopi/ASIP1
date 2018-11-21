<template>
  <div id="app">
  	 <notifications
       :max="3"
       :width="400"
       position="top center"/>

    <MenuBar/>
    <router-view class="content"/>
  </div>
</template>

<script>
import auth from './common/auth'
import MenuBar from './components/MenuBar'


export default {
  name: 'App',
   components: { MenuBar },
   data() {
     return {
       // enlazamos el objeto donde vamos a guardar los datos de autenticación
       // de esta forma se activa el data-binding y los podemos usar como
       // propiedades públicas en los componentes
       storeAuth: auth
     }
   },
   created() {
     // nada más ejecutar la aplicación comprobamos si estamos autenticados
     if (auth.getToken()) {
       auth.authenticate().catch(() => auth.logout())
     }
   }
}
</script>

<style scoped lang="scss">
  #app {
    color:  #ffffff;
    
  }

  .notification {
     margin: 10px;
     margin-bottom: 0;
     border-radius: 3px;
     font-size: 13px;
     padding: 10px 20px;
     color: #495061;
     background: #EAF4FE;
     border: 1px solid #D4E8FD;}

     .error {
       background: #ffffff;
     }

     .success{
      background: #68CD86;
     }

     .notification-title {
       letter-spacing: 1px;
       text-transform: uppercase;
       font-size: 10px;
       color: #2589F3;
     }


   
</style>
