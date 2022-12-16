<template>
  <div class="create-bid">
      <button v-on:click="createBid(1)">Bid +$1</button>
      <button v-on:click="createBid(5)">Bid +$5</button>
      <button v-on:click="createBid(10)">Bid +$10</button>
      <button v-on:click="createBid(20)">Bid +$20</button>
  </div>
</template>

<script>
import bidService from '../services/BidService'

export default {
    name: "create-bid",
    props: {
            'auctionId' : Number
        },
    data() {
        return {
            bidAmount: 0
        }
    },
    methods: {
        createBid(amount) {
            bidService.getHighestBidAmountByAuctionId(this.auctionId).then(response => { // this part is fucked
                if(response.status == 200) {
                    this.bidAmount = response.data + amount;
                    let bid = {};
                    bid["auctionId"] = this.auctionId;
                    bid["userId"] = this.$store.state.user.id;
                    bid["bidAmount"] = parseFloat(this.bidAmount);
                    bidService.placeBid(bid).then(response => {
                        if(response.status == 201) {
                            alert('Bid placed Successfully!');
                         window.location.reload();
                        }
                        else {
                            alert('Something Went Wrong!');
                            window.location.reload();
                        }
            })
                }
                else {
                    alert('Something Went Wrong!');
                    window.location.reload();
                }
            });
        }
    }
}
</script>

<style scoped>
button {
    cursor:url("data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg'  width='40' height='48' viewport='0 0 100 100' style='fill:black;font-size:24px;'><text y='50%'>💵</text></svg>") 16 0,auto; /*!emojicursor.app*/
}
</style>