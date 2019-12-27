import Vue from 'vue';

window.axios = require('axios')  // Import axios globally

new Vue({
    el: '#app',

    components: {
        //
    },

    mounted: function() {
        axios.get('https://jsonplaceholder.typicode.com/posts')
            .then(response => this.posts = response.data)
            .catch(error => this.posts = [{title: 'No data found'}])
            .finally(() => console.log("Data loading complete!"));

        axios.post('https://jsonplaceholder.typicode.com/posts')
            .then(response => console.log(response))
            .finally(() => console.log('Data post complete!'));
    },

    data: {
        posts: null
    }
});