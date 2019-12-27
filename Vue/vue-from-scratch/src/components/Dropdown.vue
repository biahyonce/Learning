<template>
    <select name="dropdown" id="dropdown" v-model="selectedOption" v-on:change="optionChange">
        <!-- Instead of "v-on:change", it could be @change -->
        <option value="null" disabled>Please select an option</option>
        <option value="0">Option 0</option>
        <option value="1">Option 1</option>
        <option value="2">Option 2</option>
    </select>
</template>

<script>
    export default {
        name: "Dropdown",

        data: function() {
            return {
                selectedOption: null
            }
        },

        methods: {
            optionChange: function (value) {
                this.$emit('update:option', this.selectedOption);
            }
        }
    }
</script>

<style scoped>

</style>