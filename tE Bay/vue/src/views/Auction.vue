<template>
  <div >
    <individual-auction />
    <!-- v-bind:auctionID="parseInt(route.params.auctionID)"  -->
  </div>
</template>

<script>
import IndividualAuction from "../components/IndividualAuction";

export default {
  components: {
    IndividualAuction
  }
};
</script>
<style>
.wow{
display: inline-block;
justify-content: center;
background-color: #333;
margin: 20px;
color: white;
text-align: center;
width: 500px;
justify-content: center;
}
</style>