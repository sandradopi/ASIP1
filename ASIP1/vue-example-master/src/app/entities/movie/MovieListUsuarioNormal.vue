<template>
  <LoadingPage
    :loading="loading"
    :error="error">

      <ul class="menu">
        <li><a href="#">Vistas</a></li>
        <li><a href="#">Pendientes</a></li>
      </ul>
    </br>
    </br>
    <hr>
    <div class= "namemovie"
      v-for="movie in movies"
      :key="movie.idMovie">
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
    margin :10px;
  }
  ul.menu{
    padding:0;
    margin:0;
    float: left;
  }

  ul.menu li{
    display:block;
    float:left;
    padding: 10px;
    color:#ffff;
  }
.botton{
  float:right;
}

</style>

