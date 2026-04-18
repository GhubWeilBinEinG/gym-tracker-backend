<template>
  <div class="home">
    <img alt="Tracker" src="../assets/Tracker.png">
    <HelloWorld msg="Gym Tracker"/>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'HomeView',
  components: {
    HelloWorld
  }
}
</script>
