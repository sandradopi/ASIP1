<template>
  <div>
    <div v-if="loading" class="loading">
      <spinner 
        message="Loading..." 
       size="huge" 
        line-fg-color="#ff0000">
     </spinner>
    </div>
    <slot/>
  </div>
</template>


<script>
  import Vue from 'vue'
  import Spinner from 'vue-simple-spinner'
export default {
  name: 'LoadingPage',
  components: {
    Spinner
  },

  props: {
    loading: {
      type: Boolean,
      required: true
    },
    error: {
      type: [ String, Object, Error ],
      default: null
    }
  },
  methods: {
    
  }
}
</script>

<style scoped lang="scss">



</style>