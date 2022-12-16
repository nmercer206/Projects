<template>
<div class="pin"> 
  
  <div>
    <h2 class="page_title"> List of Auctions </h2>
  </div>
  
  <card-format />
 
</div>
</template>

<script>
import CardFormat from './CardFormat.vue'
import AuctionService from '../services/AuctionService'

export default {
  components: { CardFormat },
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