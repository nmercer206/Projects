<template>
  <div>
        <div class="as" v-bind:key="auction.id">
            <h1 class="title">Name of Item: {{ auction.title }}</h1>
            <h2>This auction will end in:</h2>
            <h3 class = "remainingTime"> {{ timeRemaining.days + " Days " + timeRemaining.hours + " Hours " +  timeRemaining.minutes + " Minutes Remaining " }} </h3>  
            <img height="200px" v-bind:src="auction.imagePath" >
            <p> Starting Bid Price: $ {{ auction.startingPrice}}</p>
            <p> Details: {{ auction.description }} </p>
            <p> Buyout Price: $ {{ auction.buyout }} </p>
            <p class="seller"> Seller: {{auction.owner.username}} </p>
            
            <create-bid v-bind:auctionId="auction.id" />
            <table>
            <tr> 
                <th>Bidder:</th>
                <th>Bid Amount:</th>
            </tr>
            <tr v-for="bid in auction.bids" v-bind:key="bid.id">
                    <td> {{bid.username}} </td>
                    <td> {{bid.bidAmount}}</td>
                </tr>
            <!-- <div v-for="bid in auction.bids" v-bind:key="bid.id"> 
                <tr>
                    <td> {{bid.username}} </td>
                    <td> {{bid.bidAmount}}</td>
                </tr>
            </div> -->
            <p>{{this.$store.state.user.username}}</p>
            </table>
        
        </div>
  </div>
</template>

<script>
import auctionService from '../services/AuctionService'
import createBid from '../components/CreateBid.vue'

export default {
    components: {
        'create-bid': createBid
    },
    name: "individual-auction",
    //    props: {
    //         'id' : Number
    //     },
        
    data(){
            return{
                auctionId: parseInt(this.$route.params.id),
                auction: {
                            "id": null,
                            "owner": {
                                "id": null,
                                "username": null,
                                "authorities": []
                            },
                            "title": null,
                            "description": null,
                            "startingPrice": null,
                            "winningBid": null,
                            "startDate": null,
                            "endDate": null,
                            "bids": [],
                            "imagePath": null
                },
                previewImage: null
            }
        },
    created() {
        auctionService.getAuctionById(this.auctionId).then(response => {
            this.auction = response.data;
        })
    },
    computed: {
        timeRemaining(){

            var endDate = new Date(this.auction.endDate).getTime();
            var currentDate = Date.now();
            var days = Math.floor((endDate - currentDate) / (1000*60*60*24));
                var toHours = ((endDate - currentDate) / (1000*60*60*24)).toFixed(100);
            var hours = Math.floor((toHours - Math.floor(toHours)) * 24);
                var toMinutes = ((toHours - Math.floor(toHours)) * 24).toFixed(100);
            var minutes = Math.floor((toMinutes - Math.floor(toMinutes)) * 60);
                var toSeconds = ((toMinutes - Math.floor(toMinutes)) * 60).toFixed(100);
            var seconds = Math.floor((toSeconds - Math.floor(toSeconds)) * 60);

            return {
                days,
                hours,
                minutes,
                seconds
            }
        }
    }
}
</script>

<style>

.as {
display: inline-block;
justify-content: center;
background-color: #333;
margin-left: auto;
margin-right: auto;
margin-top: 50px;
color: white;
text-align: center;
width: 100%;
justify-content: center;
}
.remainingTime{
    font-style: italic;
    color: red;
}



</style>