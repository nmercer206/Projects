import axios from 'axios';
import store from '../store/index.js'

const http = axios.create({
  baseURL: "http://localhost:9000"
});

export default {
    placeBid(bid) {
        const config = {
            headers: {
              Authorization: "Bearer " + store.state.token
            }
          };
        return http.post('/bids', bid, config)
    },

    getBidById(bidId) {
        const config = {
            headers: {
              Authorization: "Bearer " + store.state.token
            }
          };
        return http.get(`/bids/${bidId}`, config)
    },

    getBidsByUserId(userId) {
        const config = {
            headers: {
              Authorization: "Bearer " + store.state.token
            }
          };
        return http.get(`/bids/user/${userId}`, config)
    },
    deleteBidById(bidId) {
        const config = {
            headers: {
              Authorization: "Bearer " + store.state.token
            }
          };
        return http.delete(`/bids/${bidId}`, config)
    },

    getHighestBidAmountByAuctionId(auctionId) {
        const config = {
            headers: {
              Authorization: "Bearer " + store.state.token
            }
          };
        return http.get(`/highbid/${auctionId}`, config)
    }
}
