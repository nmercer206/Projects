<template>
  <div class="home">

   <list-of-auctions />
     
  </div>
</template>

<script>
import ListOfAuctions from '../components/ListOfAuctions.vue';
export default {
  components: {
    ListOfAuctions
  },
  name: "home"
};
</script>


