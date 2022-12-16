<template>
<div class = 'bid-format'>
    <div class="bid-card" v-for="bid in bids" v-bind:key="bid.id">
        <h1 class="auction-title">Title: {{ title }}</h1>
        <p> Price: $ {{ bid.amount }}</p>
        <button v-if="$store.state.token !== ''">
        <a href="#" v-on:click="deleteBid(bid.id)" onClick="window.location.reload()">Delete</a>
        </button>
    </div>
</div>
</template>

<script>
import auctionService from '../services/AuctionService';
import bidService from '../services/BidService';
export default {     
        data(){
            return{
                bids: {
                    auctionId: 0,
                    bidId: 0,
                    userId: 0,
                    amount: 0
                },
                title: ""
            }
        },
        created() {
            bidService.getBidsByUserId(this.user.id).then(response => {
                this.bids = response.data;
            }),
            auctionService.getAuctionById(this.bids.auctionId).then(response => {
                this.title = response.data.title;
            })
        },
        methods:{
            deleteBidById(id) {
                bidService.deleteBidById(id).then(response =>{
                    if(response.status === 200){
                        this.getBidsByUserId(this.user.id);
                }
            })
            }
        },
        
     }
</script>