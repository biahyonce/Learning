<template>
    <div class="hello">
        <h1>{{ msg }}</h1>
        <div id="app">
            <div @click="iconClick" class="fa-3x">
                <i class="fas fa-circle-notch fa-spin" />
                <i class="fas fa-bars fa-spin" />
            </div>

            <div>
                <font-awesome-icon :icon="['fab', 'vuejs']" />
            </div>

            <div>
                <font-awesome-icon icon="credit-card" />
            </div>

        </div>
    </div>
</template>

<script>
    export default {
        name: 'HelloWorld',
        props: {
            msg: String
        },

        methods: {
            iconClick() {
                alert("Method alert!!")
            }
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    h3 {
        margin: 40px 0 0;
    }
    ul {
        list-style-type: none;
        padding: 0;
    }
    li {
        display: inline-block;
        margin: 0 10px;
    }
    a {
        color: #42b983;
    }
</style>
