<template>
  <LoadingPage
    :loading="loading"
    :error="error">

    <div class="float-right">
      <b-btn
        :to="{ name: 'PostCreate' }"
        variant="primary">New</b-btn>
    </div>
    <div
      v-for="post in posts"
      :key="post.id">
      <router-link :to="{ name: 'PostDetail', params: { id: post.id } }">
        {{ post.title }}
      </router-link>
    </div>
  </LoadingPage>
</template>

<script>
import { HTTP } from '../../common/http-common'
import LoadingPage from '../../components/LoadingPage'

export default {
  components: { LoadingPage },
  data() {
    return {
      loading: false,
      posts: null,
      error: null
    }
  },
  created() {
    this.loading = true

    HTTP.get('posts')
    .then(response => this.posts = response.data)
    .catch(err => this.error = err.response.data)
    .finally(() => this.loading = false)
  }
}
</script>
