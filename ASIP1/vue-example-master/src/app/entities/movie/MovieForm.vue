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

    <div class= "formulario">
    <b-form
      v-if="movie"
      class="app-form"
      @submit="save">

      <h5>Tittle *</h5>
      <b-form-group
        label-for="title">
        <b-form-input
          id="title"
          v-model="movie.name"
          type="text"
          autocomplete="off"
          required
          placeholder="Enter title"/>
      </b-form-group>

      <h5>Duration *</h5>
      <b-form-group
        label-for="duration">
        <b-form-input
          id="duration"
          v-model="movie.duration"
          type="number"
          autocomplete="off"
          required
          placeholder="Enter duration"/>
      </b-form-group>

      <h5>Data *</h5>
      <b-form-group
        label="(yyyy-MM-dd)"
        label-for="Data">
        <b-form-input
          id="data"
          v-model="movie.data"
          type="date"
          autocomplete="off"
          required
          placeholder="Enter data"/>
      </b-form-group>


   <b-form-group>
   <h5>Actors:</h5>
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
      </multiselect>
      <link rel="stylesheet" href="https://unpkg.com/vue-multiselect@2.1.0/dist/vue-multiselect.min.css">
      
  
  </b-form-group>
      <h5>Genre *</h5>
      <b-form-group
        label-for="genre">
        <b-form-select
          id="genre"
          :options="genres"
          v-model="movie.genre"
          required/>
      </b-form-group>


      <b-form-group >
      <h5>Directors *</h5>
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
      </multiselect>


  
  </b-form-group>
      <h5>Summary</h5>
      <b-form-group
        label-for="summary">
        <b-form-textarea
          id="summary"
          v-model="movie.summary"
          :rows="3"
          :max-rows="6"
          autocomplete="off"
          placeholder="Enter summary"/>
      </b-form-group>
    </b-form>
</div>
  </LoadingPage>
</template>

<script>
import { HTTP } from '../../common/http-common'
import LoadingPage from '../../components/LoadingPage'
import Multiselect from 'vue-multiselect'
import Vue from 'vue'

export default {
  components: { LoadingPage, Multiselect},
  data() {
    return {
      movie: {},
      error: null,
      loading: false,
      allparticipantes: [],
      alldirigentes: [],
      allgenre: [],
      selected: []
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
    notification(){
      if (this.error=="movieDTO.duration no puede ser null"){
        this.error= "Duration is a required fields"
      }
      else if(this.error=="movieDTO.name no puede estar vacío"){
        this.error= "Title is a required fields"
      }
      else if (this.error=="movieDTO.data no puede ser null"){
        this.error= "Complete the data"
      }else if (this.error=="movieDTO.genre no puede ser null"){
        this.error= "Genre is a required fields"
      }else{
        this.error= "Please, complete all the required fields"
      }
      Vue.notify({
               text: this.error,
               type: 'error'})

    },
    back() {
      this.$router.go(-1)
    },
    _successHandler(response) {
      this.$router.replace({ name: 'MovieDetail', params: { id: response.data.idMovie }})
    },
    _errorHandler(err) {
      this.error = err.response.data.message
      this.notification()
    }
  }
}

</script>
<style scoped lang="scss" src="vue-multiselect/dist/vue-multiselect.min.css">

.formulario{
  width:50%;
  margin-left:25%;

}

</style>

