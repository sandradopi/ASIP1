import VueRouter from 'vue-router'
import Vue from 'vue'
import auth from './common/auth'

import { MovieDetail, MovieForm, MovieList, ActorList, ActorForm,GenreList, GenreForm, DirectorList, DirectorForm, Registro,MovieListVistas,MovieListVistasVote,MovieListPendientes } from './entities'
import { Home, Login, NotFound } from './components'

const routes = [
  { name: 'Home', path: '/', component: Home, meta: { public: true } },
  { name: 'MovieCreate', path: '/movies/new', component: MovieForm, meta: { authority: 'ADMIN' } },
  { name: 'ActorCreate', path: '/actors/new', component: ActorForm, meta: { authority: 'ADMIN' } },
  { name: 'ActorList', path: '/actors', component: ActorList, meta: { authority: 'ADMIN' } },
  { name: 'GenreCreate', path: '/genres/new', component: GenreForm, meta: { authority: 'ADMIN' } },
  { name: 'GenreList', path: '/genres', component: GenreList, meta: { authority: 'ADMIN' } },
  { name: 'DirectorCreate', path: '/directors/new', component: DirectorForm, meta: { authority: 'ADMIN' } },
  { name: 'DirectorList', path: '/directors', component: DirectorList, meta: { authority: 'ADMIN' } },
  { name: 'MovieList', path: '/movies', component: MovieList, meta: { public: true }},
  { name: 'MovieListVistas', path: '/movies/vistas', component: MovieListVistas, meta: { authority: 'USER' }},
  { name: 'MovieListVistasVote', path: '/movies/vistasToVote', component: MovieListVistasVote, meta: { authority: 'USER' }},
  { name: 'MovieListPendientes', path: '/movies/pendientes', component: MovieListPendientes, meta: { authority: 'USER' }},
  { name: 'Registro', path: '/login/createAccount', component: Registro,meta: { public: true }},
  { name: 'MovieDetail', path: '/movies/:id', component: MovieDetail},
  { name: 'MovieUpdate', path: '/movies/:id/edit', component: MovieForm, meta: { authority: 'ADMIN' } },
  { name: 'Login', path: '/login', component: Login, meta: { public: true, isLoginPage: true }},
  { path: '*', component: NotFound, meta: { public: true }}
]

const router = new VueRouter({
   routes
 })

 router.beforeEach((to, from, next) => {
   // por defecto las rutas restringen el acceso a usuario autenticados
   const requiresAuth = !to.meta.public

   const requiredAuthority = to.meta.authority
   const userIsLogged = auth.user.logged
   const loggedUserAuthority = auth.user.authority

   if (requiresAuth) {
     if (userIsLogged) {
       if (requiredAuthority && requiredAuthority != loggedUserAuthority) {
         // usuario logueado pero sin permisos
         Vue.notify({
           text: 'Access is not allowed for the current user. Try to log again.',
           type: 'error'
         })
         auth.logout().then(() => next('/login'))
       } else {
         // usuario logueado y con permisos adecuados
         next()
       }
     } else { // usuario no está logueado
       Vue.notify({
         text: 'This page requires authentication.',
         type: 'error'
       })
       next('/login')
     }
   } else { // página pública
     if (userIsLogged && to.meta.isLoginPage) {
       // si estamos logueados no hace falta volver a mostrar el login
       next({ name: 'Home', replace: true })
     } else {
       next()
     }
   }
 })

 export default router