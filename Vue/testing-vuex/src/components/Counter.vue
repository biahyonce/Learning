<template>
    <div>
        <h1>{{countAlias}}</h1>
        <h1>{{count}}</h1>
        <h1>{{msg}}</h1>
        <h1>{{teste}}</h1>
    </div>
</template>

<script>
    import {mapState} from 'vuex'

    export default {
        name: "Counter",
        computed: {
            ...mapState(
                {countAlias: 'count'}
            ),
            ...mapState(['msg', 'count']),
            teste() {
                return "teste"
            }
        }
    }
</script>

<style scoped>

</style>