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
        label="Duration:"
        label-for="duration">
        <b-form-input
          id="duration"
          v-model="movie.duration"
          type="text"
          required
          placeholder="Enter duration"/>
      </b-form-group>

      <b-form-group
        label="Data:"
        label-for="Data">
        <b-form-input
          id="data"
          v-model="movie.data"
          type="text"
          required
          placeholder="Enter data"/>
      </b-form-group>

      <b-form-group
        label="Actors:"
        label-for="actor">
        <b-form-select
          id="actor"
          :options="actors"
          v-model="movie.participantes"
          required/>
      </b-form-group>

      <b-form-group
        label="Genre:"
        label-for="genre">
        <b-form-select
          id="genre"
          :options="genres"
          v-model="movie.genre"
          required/>
      </b-form-group>


      <!--<b-form-group
        label="Directors:"
        label-for="director">
        <b-form-select
          id="director"
          :options="dirigentes"
          v-model="movie.dirigentes"
          required/>
      </b-form-group>-->

      <b-form-group
        label="Summary:"
        label-for="summary">
        <b-form-textarea
          id="summary"
          v-model="movie.summary"
          :rows="3"
          :max-rows="6"
          required
          placeholder="Enter summary"/>
      </b-form-group>
    </b-form>
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
      allparticipantes: [],
      alldirigentes: [],
      allgenre: null
    }
  
  },
   computed: {
    actors() {
      return this.allparticipantes.map(actor => {
        return {
          text: actor.name + " " + actor.surname1,
          value: actor
        }
      })
    },

    directors() {
      return this.alldirigentes.map(director => {
        return {
          text: director.name + " " + director.surname1,
          value: director
        }
      })
    },

    genres() {
      return this.allgenre.map(genre => {
        return {
          text: genre.type ,
          value: genre
        }
      })
    }
  },
  created() {
      HTTP.get('actors')
      .then(response => this.allparticipantes = response.data)
      .catch(err => this.error = err.message)
      /*HTTP.get('directors')
      .then(response => this.alldirigentes = response.data)
      .catch(err => this.error = err.message)*/
      HTTP.get('genres')
      .then(response => this.allgenre = response.data)
      .catch(err => this.error = err.message)
  },

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

</script>