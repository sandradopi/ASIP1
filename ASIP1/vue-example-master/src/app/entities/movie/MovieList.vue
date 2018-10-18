<template>
  <LoadingPage
    :loading="loading"
    :error="error">

    <div class="float-right">
      <b-btn
        :to="{ name: 'MovieCreate' }"
        variant="outline-primary">New</b-btn>
    </div>
    <div
      v-for="movie in movies"
      :key="movie.id">
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
