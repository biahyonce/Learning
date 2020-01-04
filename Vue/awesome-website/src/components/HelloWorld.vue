<template>
    <div class="hello">
        <burger></burger>
        <sidebar></sidebar>
    </div>
</template>

<script>
import Burger from "./menu/Burger";
import Sidebar from "./menu/Sidebar";
export default {
    name: 'HelloWorld',
    components: {Sidebar, Burger},

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>

</style>