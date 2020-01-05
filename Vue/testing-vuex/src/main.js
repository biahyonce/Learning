import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

Vue.config.productionTip = false

// Here, the store is injected into all child components
// It can be accessed by using this.$store

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
