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
    <div class= "namegenre" v-for="genre in genres" :key="genre.idGenre">
    <div class= "tittle">
        <router-link
         :to="{ name: ''}">
          {{ genre.type }}
        </router-link>
    </div>
    <img class="imagen" src="genero.jpg">
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
  .namegenre {
    margin-top :20px;
    width:28%;
    height:20%;
    font-size: big;
    float:left;
    padding:20px;
    float-left:20px;
    margin-left :10px;
    background-color: #ffffff;
    border-radius: 35px 35px 35px 35px
  }

  .imagen{
    width:40%;
    height:auto;
    float:left;
    
    }

  .tittle{
    float:right;
    text-align:middle;
    font-weight: bold;
    font-family: sans-serif;
    font: 150% sans-serif;
    font-size: 1.5vw;
      
    }

   


</style>



