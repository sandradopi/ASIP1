<template>
  <LoadingPage
    :loading="loading"
    :error="error">
      <div
      v-if="error"
      class="error">
      <pre>{{ error }}</pre>
    </div>
     <b-btn
         :to="{ name: 'GenreCreate' }"
         variant="primary">New</b-btn>
    <br/>
    <h1 class="princ"> Genres</h1>
    <div class= "namegenre" v-for="genre in genres">
    <div class= "aux">
        {{ genre.type }}
    </div>
    <div class= "tittle">
        <router-link
         :to="{ name: ''}">
         <img class="imagen" src="genero.jpg">
        </router-link>
    </div>
    
  </LoadingPage>
</template>

<script>
import { HTTP } from '../../common/http-common'
import LoadingPage from '../../components/LoadingPage'
import auth from '../../common/auth'

export default {
  components: { LoadingPage},
  data() {
    return {
      loading: false,
      genres: null,
      error: null
    }
  },
  
  created() {
    this.fetchData()
    
  },
  methods: {
  fetchData() {
      this.loading = true
     HTTP.get(`genres`)
    .then(response => {
       this.genres = response.data
     })
     .catch(err => {
       this.error = err.message
     })
    .finally(() => this.loading = false)
    },
    
    _successHandler(response) {
      this.fetchData()
    },

    _errorHandler(err) {
      this.error = err.response.data.message
    }
  }
}
</script>

<style scoped lang="scss">

.princ{
    font-size: 40px;
    letter-spacing: 8px;
    color: red;
    text-align: center;
    text-transform: uppercase;
  }
  
  .namegenre {
    margin-top :30px;
    margin-left:10px;
    width:15%;
    font-size: big;
    float:left;  
   
  }
  
  .imagen{
    margin-top:10px;
    width:80%;
    margin-left:30px;
    border-radius: 20px 20px 20px 20px
    
    }

    .aux{
    font-size: 24px;
    color: white;
    text-align: center;
    margin-bottom:10px;
    text-transform: uppercase;
    margin-left:25px;
    }
   
</style>



