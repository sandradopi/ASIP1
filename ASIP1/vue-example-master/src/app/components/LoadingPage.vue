<template>
  <div>
    <div
      v-if="loading"
      class="loading">
      Loading...
    </div>

    <div
      v-else-if="error"
      class="error">
      <pre>{{ error }}</pre>
        <button
         class="btn btn-primary"
         @click="reload">Reload</button>
    </div>

    <slot v-else/>
  </div>
</template>

<script>

export default {
  name: 'LoadingPage',
  props: {
    loading: {
      type: Boolean,
      required: true
    },
    error: {
      type: [ String, Object ],
      default: null
    }
    },
   methods: {
     reload() {
       this.$router.go()
     }
  }
}
</script>
