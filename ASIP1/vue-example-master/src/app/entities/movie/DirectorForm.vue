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
      v-if="director"
      class="app-form"
      @submit="save">

<div class="formulario">
      <h5>Name *</h5>
      <b-form-group
        label-for="Name">
        <b-form-input
          id="name"
          v-model="director.name"
          type="text"
          autocomplete="off"
          required
          placeholder="Enter a name"/>
      </b-form-group>

      <h5>First Surname *</h5>
      <b-form-group
        label-for="surname1">
        <b-form-input
          id="surname1"
          v-model="director.surname1"
          type="text"
          autocomplete="off"
          required
          placeholder="Enter the first Surname"/>
      </b-form-group>

      <h5>Second Surname</h5>
      <b-form-group
        label-for="surname2">
        <b-form-input
          id="surname2"
          v-model="director.surname2"
          type="text"
          autocomplete="off"
          required
          placeholder="Enter the second Surname"/>
      </b-form-group>
  </div>
    </b-form>
  </LoadingPage>
</template>

<script>
import { HTTP } from '../../common/http-common'
import LoadingPage from '../../components/LoadingPage'
import Vue from 'vue'


export default {
  components: { LoadingPage},
  data() {
    return {
      director: {},
      error: null,
      loading: false,

    }
  
  },
   
  created() {
     if (this.$route.params.id) {
        this.loading = true
        HTTP.get(`directors/${this.$route.params.id}`)
        .then(response => this.director = response.data)
        .catch(err => this.error = err.message)
        .finally(() => this.loading = false)
    } else {
      this.director = {}
    }
  },
  methods: {
    
    save() {
      if (this.$route.params.id) {
        HTTP.put(`directors/${this.$route.params.id}`, this.director)
        .then(this._successHandler)
        .catch(this._errorHandler)
      } else {
        HTTP.post('directors', this.director)
        .then(this._successHandler)
        .catch(this._errorHandler)
      }

      
    },
    back() {
      this.$router.go(-1)
    },
    notification(){
      if (this.error=="directorDTO.name no puede estar vacío"){
        this.error= "Name is a required field"
      }
      else if(this.error=="directorDTO.surname1 no puede estar vacío"){
        this.error= "First Surname is a required field"
      }else{
        this.error= "Please, complete all the required fields"
      }
      Vue.notify({
               text: this.error,
               type: 'error'})

    },

    _successHandler(response) {
     this.$router.replace({ name: 'DirectorList'})
    },
    _errorHandler(err) {
      this.error = err.response.data.message
      this.notification()
    }
  }
}


</script>

<style scoped lang="scss">

.formulario{
  width:50%;
  margin-left:25%;

}

</style>


