import Vue from 'vue'
import App from './App.vue'
import router from './router'
import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { dom } from '@fortawesome/fontawesome-svg-core'

// To import icons individually
//import { faSpinner } from '@fortawesome/free-solid-svg-icons'
//import { faVuejs } from '@fortawesome/free-brands-svg-icons'
//import { faCreditCard } from "@fortawesome/free-solid-svg-icons"

// To import entire style of icons
import { fas } from '@fortawesome/free-solid-svg-icons'
import { fab } from '@fortawesome/free-brands-svg-icons'

dom.watch() // Transform <i /> to <svg />
library.add(fas)
library.add(fab)
Vue.component('font-awesome-icon', FontAwesomeIcon)
Vue.config.productionTip = false

new Vue({
    router,
    render: h => h(App)
}).$mount('#app')
