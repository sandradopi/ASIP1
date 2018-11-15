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
      v-if="genre"
      class="app-form"
      @submit="save">

<div class="formulario">
      <h5>Type *</h5>
      <b-form-group
        label-for="Name">
        <b-form-input
          id="name"
          v-model="genre.type"
          type="text"
          autocomplete="off"
          required
          placeholder="Enter a type"/>
      </b-form-group>
</div>
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
      genre: {},
      error: null,
      loading: false,

    }
  
  },
   
  created() {
      this.genre = {}
  },
  methods: {
    
    save() {
        HTTP.post('genres', this.genre)
        .then(this._successHandler)
        .catch(this._errorHandler)
      
    },
    back() {
      this.$router.go(-1)
    },

    _successHandler(response) {
     this.$router.replace({ name: 'GenreList'})
    },
    _errorHandler(err) {
      this.error = err.response.data.message
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


