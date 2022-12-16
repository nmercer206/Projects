<template>
<div class = 'b'>
    <div>
        <div class="card a" v-for="auction in auctions" v-bind:key="auction.id">
            <h1 class="title">{{ auction.title }}</h1>
            <img height="200px" v-bind:src="auction.imagePath" >

            <p  class = "remainingTime"><strong>{{ timeRemaining(auction).days }} Days {{ timeRemaining(auction).hours }} Hours {{timeRemaining(auction).minutes}} Minutes Remaining</strong></p>
            <p  class = "remainingTime"><strong> for {{ auction.title }}</strong></p>

            <p> Starting Price: $ {{ auction.startingPrice}}</p>
            
            <p class="seller"> Seller: {{auction.ownerName}} </p>

            <button v-if="$store.state.token !== ''">
                <router-link v-bind:to="{name: 'auction', params: {id: auction.id}}" > Select </router-link>
            </button>

            <button v-if="$store.state.token !== ''">
                <router-link v-bind:to="{name: 'update', params: {id: auction.id}}">Edit</router-link>
            </button>

               <button v-if="$store.state.token !== ''" class="delete">
              <a href="#" v-on:click="deleteAuction(auction.id)" onClick="window.location.reload()" class="deleteLink">Delete</a>
              </button>
        </div>
    </div>
</div>

</template>

<script>
import auctionService from '../services/AuctionService';
export default {
        name:'imageUpload',
     
        data(){
            return{
                auctions: [],
               previewImage: null
            }
        },
        created() {
            auctionService.getAuctions().then(response => {
                this.auctions = response.data;
            })
        },
        methods:{
            deleteAuction(id) {
                auctionService.deleteAuction(id).then(response =>{
                    if(response.status === 200){
                        this.getAuctions();
                }
            })
            },
            timeRemaining(auction){
            //indented for clearer visibility
                var endDate = new Date(auction.endDate).getTime();
                var currentDate = Date.now();
                var days = Math.floor((endDate - currentDate) / (1000*60*60*24));
                    var toHours = ((endDate - currentDate) / (1000*60*60*24)).toFixed(100);
                var hours = Math.floor((toHours - Math.floor(toHours)) * 24);
                    var toMinutes = ((toHours - Math.floor(toHours)) * 24).toFixed(100);
                var minutes = Math.floor((toMinutes - Math.floor(toMinutes)) * 60);
                    var toSeconds = ((toMinutes - Math.floor(toMinutes)) * 60).toFixed(100);
                var seconds = Math.floor((toSeconds - Math.floor(toSeconds)) * 60);
                var test = 55;
               
                return {
                    days,
                    hours,
                    minutes,
                    seconds,
                    test
                }
            }
        },
        
     }  // missing closure added
</script>

<style scoped> 
button.delete, a.deleteLink {
    cursor:url("data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg'  width='40' height='48' viewport='0 0 100 100' style='fill:black;font-size:24px;'><text y='50%'>😱</text></svg>") 25 15,auto; /*!emojicursor.app*/
}
</style>

<style>

.uploading-image{
    display:flex;
}

.card{
    border-radius: 10px;
    border: 2px solid black;
    width: auto;
    height: auto;
    margin: 20px;
    background-color: #333;
    color: white;
    
}
h1{
    text-align: center;
}
.seller{
    align-items: center;
    
}
.center {
    display: block;
    margin-left: auto;
    margin-right: auto;
    width: 50%;   
}


.b {
    display: flex;
    display: inline-block;
   
}
.a {
    display: flex;
    display: inline-block;
    
   
}

.uploading-image {
    display: flex;
    margin-left: auto;
    margin-right: auto;
    width: 50%;
}
.remainingTime {
   margin-left: 1vw;
   margin-right: 1vw;
}

@keyframes colorChange {
  0% {
    box-shadow: 7.5px 6.5px 12px 0.5px #ff0000;
  }
  14% {
    box-shadow: 7.5px 6.5px 12px 0.5px #ffa500;
  }
  28% {
    box-shadow: 7.5px 6.5px 12px 0.5px #ffff00;
  }
  42% {
    box-shadow: 7.5px 6.5px 12px 0.5px lime;
  }
  56% {
    box-shadow: 7.5px 6.5px 12px 0.5px cyan;
  }
  70% {
    box-shadow: 7.5px 6.5px 12px 0.5px #0000ff;
  }
  84% {
    box-shadow: 7.5px 6.5px 12px 0.5px #4b0082;
  }
  100% {
    box-shadow: 7.5px 6.5px 12px 0.5px #ff0000;
  }

}

.title {
    color: rgb(207, 41, 212);
    
    margin: 5px;
    padding: 2px;
}

</style>