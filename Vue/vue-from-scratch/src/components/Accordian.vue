<template>
    <div> 
        <p>{{item.title}}</p>
        <p @click="toggle = !toggle">Details</p>
        <p v-if="toggle">{{item.description}}</p>
    </div>
</template>

<script>
export default {
    name: "Accordian",

    props: ['item'],

    data: function() {
        return {
            toggle: false
        }
    }
}
</script>