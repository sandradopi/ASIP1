<template>
  <LoadingPage
    :loading="loading"
    :error="error">

      <div class="new">
        <b-btn 
          :to="{ name: 'MovieCreate' }"
          variant="primary">New</b-btn>
      </div>
    <br/>
    <div class= "namemovie" v-for="movie in movies" :key="movie.idMovie">
        <router-link :to="{ name: 'MovieDetail', params: { id: movie.idMovie } }">
          {{ movie.name }}
        </router-link>
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
      loading: false,
      movies: null,
      error: null
    }
  },
  created() {
    this.loading = true
    HTTP.get('movies')
    .then(response => this.movies = response.data)
    .catch(err => this.error = err.response.data)
    .finally(() => this.loading = false)
  }
}
</script>

<style scoped lang="scss">
  .namemovie {
    margin-top :10px;
  }

</style>

