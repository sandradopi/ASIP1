import Vue from 'vue'

import VueRouter from 'vue-router'
Vue.use(VueRouter)

import BootstrapVue from 'bootstrap-vue'
Vue.use(BootstrapVue)

import ToggleButton from 'vue-js-toggle-button'
Vue.use(ToggleButton)

import { library } from '@fortawesome/fontawesome-svg-core'
import { faSearch, faMinusSquare } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
library.add(faSearch)
library.add(faMinusSquare)
Vue.component('font-awesome-icon', FontAwesomeIcon)


import Notifications from 'vue-notification'
Vue.use(Notifications)

import App from './app/App'
import router from './app/AppRouter'

new Vue({
	
  render: h => h(App),
  router
}).$mount('#app')
