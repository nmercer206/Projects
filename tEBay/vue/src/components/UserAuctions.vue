<template>
<div class="users-auctions"> 
  <div>
    <h1 class="page_title"> Your Auctions </h1>
  </div>
  
  <user-card-format />
 
</div>
</template>

<script>
import UserCardFormat from '../components/UserCardFormat.vue'
import AuctionService from '../services/AuctionService'

export default {
  components: { UserCardFormat },
  methods: {
     deleteTopic(id) {
      AuctionService.deleteAuction(id).then(response =>{
        if(response.status === 200){
        this.getAuctions();
        }
      })
    }

  }


}

</script>

<style>
.page_title{
text-align: center;

}
.pin{
  text-align: center;
}
</style>