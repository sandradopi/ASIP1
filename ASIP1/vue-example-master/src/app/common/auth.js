import { HTTP } from './http-common'

 // aquí guardaremos los datos de autenticación y una serie de métodos helpers públicos
 const store = {
   user: {
     authority: '',
     login: '',
     logged: false
     // token: null
   },
   login,
   logout,
   getToken,
   isAdmin,
   authenticate
 }

 // usamos localStorage para guardar el token, de forma
 // que sea persistente (se inhabilita con el tiempo o
 // al hacer logout)
 function saveToken(token) {
   localStorage.setItem('token', token)
 }

 function removeToken() {
   localStorage.removeItem('token')
 }

 function getToken() {
   return localStorage.getItem('token')
 }

 // si tenemos el token guardado, esta petición se hará
 // con el filtro definido en http-common y por tanto nos
 // devolverá el usuario logueado
 function authenticate() {
   return HTTP.get('account').then(response => {
     store.user.login = response.data.login
     store.user.authority = response.data.authority
     store.user.logged = true
     return store.user
   })
 }

 function login(credentials) {
   return HTTP.post('authenticate', credentials)
   .then(response => {
     saveToken(response.data.token)
     return authenticate()
   })
 }

 function logout() {
   removeToken()
   store.user.login = ''
   store.user.authority = ''
   store.user.logged = false
 }
 function isAdmin() {
   return store.user.authority == 'ADMIN'
 }

 export default store