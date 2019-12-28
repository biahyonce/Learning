<template>
  <div class="about flex flex-col items-center">
    <div class="absolute inset-0 z-0" @click="closeFilter">
        <!-- When this area gets click, the filter is gone -->
    </div>

    <input type="text" class="bg-gray-300 px-4 py-2 z-10" autocomplete="off"
           v-model="state" @input="filterStates" @focus="showFilter=!showFilter">

    <div v-if="filteredStates && showFilter" class="z-10">
      <ul class="w-48 bg-gray-800 text-white">
        <li v-bind:key="filteredState" v-for="filteredState in filteredStates"
            class="py-2 border-b cursor-pointer"
            @click="setState(filteredState)">
            {{filteredState}}
        </li>
      </ul>
    </div>

  </div>
</template>

<script>
export default {
  data: function () {
    return {
      state: '',
      showFilter: false,
      states: [
        { id: 1, name: 'Flórida' },
        { id: 2, name: 'Alabama' },
        { id: 3, name: 'Alaska' },
        { id: 4, name: 'Texas' }
      ],
      filteredStates: []
    }
  },

  mounted () {
    this.filterStates()
  },

  methods: {
    filterStates () {
      if (this.state.isEmpty) this.filteredStates = this.states

      // First, map only the names (without the ID), then filter
      let result = this.states.map(state => state.name)
      this.filteredStates = result.filter(state => {
        return state.toLowerCase().startsWith(this.state.toLowerCase())
      })
    },

    setState (state) {
      this.state = state
      this.showFilter = false
    },

    closeFilter () {
      if (this.showFilter) this.showFilter = false
    }
  },

  watch: {
    state () {
      this.filterStates()
    }
  }
}
</script>
