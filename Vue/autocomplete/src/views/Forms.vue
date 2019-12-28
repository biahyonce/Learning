<template>
  <div>
    <Telephone template="(XXX) XXX-XXXX"></Telephone> <!-- That works even if it's not only 'X' -->
  </div>
</template>

<script>
import Telephone from '../components/Telephone'
export default {
  name: 'Forms',
  components: {
    Telephone
  }
}
</script>

<style scoped>

</style>
