<template>
    <div>
        <profile />
        <user-auctions />
    </div>
</template>
<script>
import Profile from '../components/Profile.vue';
import UserAuctions from '../components/UserAuctions.vue';
export default {
    components: {
        Profile,
        UserAuctions
      
    },
    setup() {
        
    },
}
</script>
<style scoped>

</style>