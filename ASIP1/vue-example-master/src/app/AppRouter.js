import VueRouter from 'vue-router'

import { MovieDetail, MovieForm, MovieList,MovieListUsuarioNormal, Registro } from './entities'
import Home from './components/Home'

const routes = [
  { path: '/', component: MovieList },
  { name: 'MovieCreate', path: '/movies/new', component: MovieForm },
  { name: 'MovieList', path: '/movies/admin', component: MovieList},
  { name: 'Registro', path: '/login/createAccount', component: Registro},
  { name: 'MovieDetail', path: '/movies/:id', component: MovieDetail },
  { name: 'MovieUpdate', path: '/movies/:id/edit', component: MovieForm },
  { name: 'Home', path: '/login', component: Home },
  { name: 'MovieListUsuarioNormal', path: '/movies', component: MovieListUsuarioNormal }
]

export default new VueRouter({
  routes
})
