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
         v-if="isAdmin"
         :to="{ name: 'MovieCreate' }"
         variant="primary">New</b-btn>
    <br/>
    <div class= "namemovie" v-for="movie in movies" :key="movie.idMovie">
        <router-link
         :to="{ name: 'MovieDetail', params: { id: movie.idMovie }}">
          {{ movie.name }}
        </router-link>
    </div>
  </LoadingPage>
</template>

<script>
import { HTTP } from '../../common/http-common'
import LoadingPage from '../../components/LoadingPage'
import auth from '../../common/auth'

export default {
  components: { LoadingPage },
  data() {
    return {
      loading: false,
      movies: null,
      error: null
    }
  },
  computed: {
     isAdmin() {
       return auth.isAdmin()
     }
   },
  created() {
    this.loading = true
    HTTP.get('movies')
    .then(response => {
       this.movies = response.data
     })
     .catch(err => {
       this.error = err.message
     })
    .finally(() => this.loading = false)
  }
}
</script>

<style scoped lang="scss">
  .namemovie {
    margin-top :10px;
  }

</style>

