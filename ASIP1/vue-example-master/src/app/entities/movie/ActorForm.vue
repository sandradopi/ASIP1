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
      v-if="actor"
      class="app-form"
      @submit="save">

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
  
    </b-form>
  </LoadingPage>
</template>

<script>
import { HTTP } from '../../common/http-common'
import LoadingPage from '../../components/LoadingPage'


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
      this.actor = {}
  },
  methods: {
    
    save() {
        HTTP.post('actors', this.actor)
        .then(this._successHandler)
        .catch(this._errorHandler)
      
    },
    back() {
      this.$router.go(-1)
    },

    _successHandler(response) {
     this.$router.replace({ name: 'ActorList'})
    },
    _errorHandler(err) {
      this.error = err.response.data.message
    }
  }
}


</script>


