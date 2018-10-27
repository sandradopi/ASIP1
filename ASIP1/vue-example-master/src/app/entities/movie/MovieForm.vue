<template>
  <LoadingPage
    :loading="loading"
    :error="error">

    <div class="float-right">
      <b-btn
        variant="primary"
        @click="back()">Back</b-btn>
      <b-btn
        variant="primary"
        @click="save()">Submit</b-btn>
    </div>

    <div class= "namegenre" v-for="genre in genres" :key="genre.idGenre">
          {{ genre.type }} </div>

    <b-form
      v-if="movie"
      class="app-form"
      @submit="save">

      <b-form-group
        label="Title:"
        label-for="title">
        <b-form-input
          id="title"
          v-model="movie.name"
          type="text"
          required
          placeholder="Enter title"/>
      </b-form-group>

      <b-form-group
        label="Date:"
        label-for="date">
        <b-form-input
          id="date"
          v-model="movie.data"
          type="text"
          required
          placeholder="Enter a date"/>
      </b-form-group>

      <b-form-group
        label="Duration:"
        label-for="duration">
        <b-form-input
          id="duration"
          v-model="movie.duration"
          type="text"
          required
          placeholder="Enter a duration"/>
      </b-form-group>


      <b-form-group
        label="Summary:"
        label-for="summary">
        <b-form-textarea
          id="summary"
          v-model="movie.summary"
          :rows="3"
          :max-rows="6"
          required
          placeholder="Enter a Summary"/>
      </b-form-group>
    </b-form>
     <b-form-group
        label="Genre:"
        label-for="genre">
          <select name="genre">
             <option v-for="genre in genres" :key="genre.idGenre">
                {{ genre.type }}
             </option> 
          </select>
     </b-form-group>

      <b-form-group
        label="Actors:"
        label-for="actors">
        <b-form-input
          id="actors"
          v-model="movie.participantes"
          type="text"
          required
          placeholder="Enter actors"/>
      </b-form-group>


      <b-form-group
        label="Directors:"
        label-for="directors">
        <b-form-input
          id="directos"
          v-model="movie.dirigentes"
          type="text"
          required
          placeholder="Enter directors"/>
      </b-form-group>

  </LoadingPage>
</template>

<script>
import { HTTP } from '../../common/http-common'
import LoadingPage from '../../components/LoadingPage'

export default {
  components: { LoadingPage },
  data() {
    return {
      movie: {},
      error: null,
      loading: false,
      genres: null,
    }
  },
 
  created() {
      if (this.$route.params.id) {
      this.loading = true

      HTTP.get(`movies/${this.$route.params.id}`)
      .then(response => this.movie = response.data)
      .catch(err => this.error = err.message)
      .finally(() => this.loading = false)
    } else {
      this.movie = {}
    }
        HTTP.get('genres')
        .then(response => this.genres = response.data)
        .catch(err => this.error = err.response.data)
  },
   methods: {
    save() {
      if (this.$route.params.id) {
        HTTP.put(`movies/${this.$route.params.id}`, this.movie)
        .then(this._successHandler)
        .catch(this._errorHandler)
      } else {
        HTTP.post('movies', this.movie)
        .then(this._successHandler)
        .catch(this._errorHandler)
      }
    },

    back() {
      this.$router.go(-1)
    },
    _successHandler(response) {
       this.$router.replace({ name: 'MovieDetail', params: { id: response.data.idMovie }})
     },
     _errorHandler(err) {
       this.error = err.response.data.message
     }
  }
}
</script>

<style scoped lang="scss">
  
</style>
