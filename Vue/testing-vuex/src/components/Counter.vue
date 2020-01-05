<template>
    <div>
        {{count}}
        {{abobrinha}}
    </div>
</template>

<script>
    import {mapState} from 'vuex'

    export default {
        name: "Counter",
        computed: mapState(['count'])
    }
</script>

<style scoped>

</style>