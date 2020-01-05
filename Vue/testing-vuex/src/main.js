import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Counter from "./components/Counter";

Vue.config.productionTip = false

// Here, the store is injected into all child components
// It can be accessed by using this.$store

new Vue({
  router,
  store,
  components: {Counter},
  render: h => h(App)
}).$mount('#app')
