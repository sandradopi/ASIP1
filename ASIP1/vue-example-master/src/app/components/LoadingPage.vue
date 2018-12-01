<template>
  <div>
    <div
      v-if="loading"
      class="loading">
      Loading...
    </div>
    <slot/>
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