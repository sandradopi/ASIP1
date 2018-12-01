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
      <slot/>
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
      type: [ String, Object, Error ],
      default: null
    }
  },
  methods: {
    
  }
}
</script>