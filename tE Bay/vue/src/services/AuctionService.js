import axios from 'axios';
import store from '../store/index.js'

const http = axios.create({
  baseURL: "http://localhost:9000"
});

export default {
  getAuctions(){
    return http.get('/auctions');
  },
  getAuctionById(auctionID){
    const config = {
      headers: {
        Authorization: "Bearer " + store.state.token
      }
    };
    return http.get(`/auctions/${auctionID}`, config)
  },
  addAuction(auction) {
    const config = {
      headers: {
        Authorization: "Bearer " + store.state.token
      }
    };
    return http.post('/auctions', auction, config)
  },
  updateAuction(auctionID, auction) {
    const config = {
      headers: {
        Authorization: "Bearer " + store.state.token
      }
    };
    return http.put(`/auctions/${auctionID}`, auction, config)
  },
  deleteAuction(id) {
    const config = {
      headers: {
        Authorization: "Bearer " + store.state.token
      }
    };
    return http.delete(`/auctions/${id}`, config)
  }
  
}