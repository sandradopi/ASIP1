import VueRouter from 'vue-router'

import { PostDetail, PostForm, PostList } from './entities'
import Home from './components/Home'

const routes = [
  { path: '/', component: Home },
  { name: 'PostDetail', path: '/posts/:id', component: PostDetail },
  { name: 'PostCreate', path: '/posts/new', component: PostForm },
  { name: 'PostUpdate', path: '/posts/:id/edit', component: PostForm },
  { name: 'PostList', path: '/posts', component: PostList }
]

export default new VueRouter({
  routes
})
