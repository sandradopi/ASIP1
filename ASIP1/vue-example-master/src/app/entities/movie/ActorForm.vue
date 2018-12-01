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
      v-if="actor"
      class="app-form"
      @submit="save">

<div class="formulario">
      <h5>Name *</h5>
      <b-form-group
        label-for="Name">
        <b-form-input
          id="name"
          v-model="actor.name"
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
          v-model="actor.surname1"
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
          v-model="actor.surname2"
          type="text"
          autocomplete="off"
          required
          placeholder="Enter the second Surname"/>
      </b-form-group>

      <h5>Birthday</h5>
      <b-form-group
        label="(yyyy-MM-dd)"
        label-for="Data">
        <b-form-input
          id="data"
          v-model="actor.birthdate"
          type="date"
          autocomplete="off"
          required
          placeholder="Enter the Birthday"/>
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
      actor: {},
      error: null,
      loading: false,

    }
  
  },
   
  created() {
     if (this.$route.params.id) {
        this.loading = true
        HTTP.get(`actors/${this.$route.params.id}`)
        .then(response => this.actor = response.data)
        .catch(err => this.error = err.message)
        .finally(() => this.loading = false)
    } else {
      this.actor = {}
    }
  },
  methods: {
    
    save() {
      if (this.$route.params.id) {
        HTTP.put(`actors/${this.$route.params.id}`, this.actor)
        .then(this._successHandler)
        .catch(this._errorHandler)
      } else {
        HTTP.post('actors', this.actor)
        .then(this._successHandler)
        .catch(this._errorHandler)
      }
        
      
    },
    notification(){
      if (this.error=="actorDTO.name no puede estar vacío"){
        this.error= "Name is a required fields"
      }
      else if(this.error=="actorDTO.surname1 no puede estar vacío"){
        this.error= "Surname1 is a required fields"
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
     this.$router.replace({ name: 'ActorList'})
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


