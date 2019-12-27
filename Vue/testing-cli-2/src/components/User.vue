<template>
    <h1>
        {{ $route.params.id}} <!-- Same id described in main.js /user/:id-->
    </h1>
</template>

<script>
    export default {
        name: "User"
    }
</script>

<style scoped>

</style>