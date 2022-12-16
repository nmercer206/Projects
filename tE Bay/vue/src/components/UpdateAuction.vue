<template>
  <form v-on:submit.prevent>
    <div class="field">
      <label for="title">Title</label>
      <input type="text" name="title" v-model="auction.title" />
    </div>
    <div class="field">
      <label for="description">Description</label>
      <input type="text" name="description" v-model="auction.description" /> 
    </div>
    <div class="field">
      <label for="startingPrice">Starting Price</label>
      <input type="text" name="startingPrice" v-model="auction.startingPrice" />
    </div>
    <div class="field">
      <label for="endDate">End Date</label>
      <input type="datetime" name="endDate" v-model="auction.endDate" />
    </div>
    <div class="field">
      <label for="imagePath">Image Url</label>
      <input type="url" name="imagePath" v-model="auction.imagePath" />
    </div>
    <div class="actions">
      <button type="submit" v-on:click="updateThisAuction()">Save Auction</button>
    </div>
  </form>
</template>

<script> 
import auctionService from '../services/AuctionService';

export default {
  name: "update-auction",
  data() {
    return {
      auction: {
        title: "",
        description: "",
        startingPrice: 0,
        ownerId: this.$store.state.user.id,
        endDate: "",
        imagePath: "",
      }
    };
  },
  methods: {
    updateThisAuction() {
      auctionService.updateAuction(this.$route.params.id, this.auction).then(response => {
          if(response.status === 200) {
            this.$router.push({ name: 'home'});
          }
        
      })
    }
  },
  created() {
    auctionService.getAuctionById(this.$route.params.id).then(response => {
            this.auction = response.data;
            this.auction.endDate = this.auction.endDate.slice(0,-6);
    })
    
  }
};
</script>