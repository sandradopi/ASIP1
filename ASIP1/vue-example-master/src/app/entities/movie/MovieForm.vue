<template>
  <LoadingPage
    :loading="loading"
    :error="error">
    <div
      v-if="error"
      class="error">
      <pre>{{ error }}</pre>
    </div>
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


   <b-form-group>
      <multiselect 
        v-model="movie.participantes" 
        tag-placeholder="Add this as new tag"
        :options="this.allparticipantes"
        :multiple="true"
        :searchable="true" 
        :clear-on-select="false" 
        :preserve-search="true"
        :close-on-select="false" 
        :show-labels="false"
        track-by="idActor"
        placeholder="Pick some actors"
        :custom-label="nameCustom">
      <template 
          slot="selection" 
          slot-scope="{ values, search, isOpen }">
          <span class="multiselect__single" 
          v-if="values.length &amp;&amp; !isOpen">
          {{ values.length }} options selected</span>
        </template>
      </multiselect>
      <link rel="stylesheet" href="https://unpkg.com/vue-multiselect@2.1.0/dist/vue-multiselect.min.css">
      <h5>Actors of the movie:</h5>
      <li v-for="actor in movie.participantes">
        {{ actor.name }} {{actor.surname1}}
       </li>
  </div>
  
  </b-form-group>

    </div>

      <b-form-group
        label="Genre:"
        label-for="genre">
        <b-form-select
          id="genre"
          :options="genres"
          v-model="movie.genre"
          required/>
      </b-form-group>


      <b-form-group label="Director:" label-for="Director">
      <multiselect
        v-model="movie.dirigentes" 
        :options="this.alldirigentes"
        :multiple="true"
        :searchable="true" 
        :clear-on-select="false" 
        :preserve-search="true"
        :close-on-select="false" 
        :show-labels="false" 
        track-by="idDirector"
        placeholder="Pick some directors"
        :custom-label="nameCustom">
      <template 
          slot="selection" 
          slot-scope="{ values, search, isOpen }">
          <span class="multiselect__single" 
          v-if="values.length &amp;&amp; !isOpen">
          {{ values.length }} options selected</span>
        </template>
      </multiselect>
      <h5>Directors of the movie:</h5>
      <li v-for="director in movie.dirigentes">
        {{ director.name }} {{director.surname1}}
       </li>
  </div>
  </b-form-group>

      <b-form-group
        label="Summary:"
        label-for="summary">
        <b-form-textarea
          id="summary"
          v-model="movie.summary"
          :rows="3"
          :max-rows="6"
          placeholder="Enter summary"/>
      </b-form-group>
    </b-form>
  </LoadingPage>
</template>

<script>
import { HTTP } from '../../common/http-common'
import LoadingPage from '../../components/LoadingPage'
import Multiselect from 'vue-multiselect'

export default {
  components: { LoadingPage, Multiselect},
  data() {
    return {
      movie: {},
      error: null,
      loading: false,
      allparticipantes: [],
      alldirigentes: [],
      allgenre: []
    }
  
  },
   computed: {
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
     this.getActors()
     this.getDirectors()
     this.getGenres()

      if (this.$route.params.id) {
        this.loading = true

        HTTP.get(`movies/${this.$route.params.id}`)
        .then(response => this.movie = response.data)
        .catch(err => this.error = err.message)
        .finally(() => this.loading = false)
    } else {
      this.movie = {}
    }
  },
  methods: {
    nameCustom ({ name, surname1 }) {
      return `${name}  ${surname1}`
    },
    getActors() {
        
       HTTP.get('actors')
      .then(response => this.allparticipantes = response.data)
      .catch(err => this.error = err.message)
    },
    getDirectors() {
        
       HTTP.get('directors')
      .then(response => this.alldirigentes = response.data)
      .catch(err => this.error = err.message)
    },

    getGenres() {
        
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
}

</script>
<style lang="sass" src="vue-multiselect/dist/vue-multiselect.min.css"></style>

