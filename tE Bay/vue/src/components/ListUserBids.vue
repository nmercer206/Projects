<template>
    <div class="bid-format">
        <h1>Your Bids</h1>
        <bid-format />
    </div>
</template>

<script>
import BidFormat from '../components/BidFormat'
export default {
    components: { BidFormat }
}
</script>

<style>
div.bid-format{
  justify-content: center;
  border-color: cornflowerblue;
  border-radius: 10px;
  border-style: solid;
  background-color: cornflowerblue;
}
</style>