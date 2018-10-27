import Vue from 'vue'
import axios from 'axios'
import auth from './auth'

const HTTP = axios.create({
  baseURL: 'http://localhost:8080/api/'
})

const onUnauthorized = () => {
  Vue.notify({
    text: 'Access denied!',
    type: 'error'
  })
  auth.logout()
}
const onResponseSuccess = response => response

// si el servidor nos devuelve un 401 o 403,
// estamos intentando acceder a un recurso sin
// los permisos correctos
const onResponseFailure = err => {
  const status = err.response.status
  if (status == 401 || status == 403) {
    onUnauthorized()
  }
  return Promise.reject(err)
}

// en cada request hay que añadir el token de autenticación
// si es que lo tenemos
const onRequest = config => {
  const token = auth.getToken()
  if (token) {
    config.headers.Authorization = `Bearer ${token}`
  }
  return config
}

HTTP.interceptors.response.use(onResponseSuccess, onResponseFailure)
HTTP.interceptors.request.use(onRequest)

export { HTTP }