import VueRouter from 'vue-router'

import { MovieDetail, MovieForm, MovieList } from './entities'
import Home from './components/Home'
import Registro from './components/Registro'

const routes = [
  { path: '/', component: Home },
  { name: 'MovieCreate', path: '/movies/new', component: MovieForm },
  { name: 'Registro', path: '/movies/createAccount', component: Registro},
  { name: 'MovieDetail', path: '/movies/:id', component: MovieDetail },
  { name: 'MovieUpdate', path: '/movies/:id/edit', component: MovieForm },
  { name: 'MovieList', path: '/movies', component: MovieList }
]

export default new VueRouter({
  routes
})
