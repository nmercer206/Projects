<template>
    <div class="profile-main">

        
       
        <h1 class="username" > {{ this.$store.state.user.username }}'s Profile </h1>

        <div class="profile-box">
            <tr class="profile-table">
                <h2>Contact Info</h2>
                <h2>Username: {{this.$store.state.user.username}}</h2>
                <h2>Name: {{this.$store.state.user.name}}</h2>
                <h2>Address: {{this.$store.state.user.address}}</h2>
                <h2>Phone Number: {{this.$store.state.user.phoneNumber}}</h2>
            </tr>
            
        </div>

    </div>
</template>
<script>
export default {
name: 'profile'
  
}
</script>
<style scoped>


div
{
    /* width: 600px;
    height: 100px;
    background: pink;
    cursor: url(https://cdn.custom-cursor.com/packs/3743/neon-money-pack.png) 25 15, auto; */
    
    color:black;
   
}

.profile-table {
    margin: auto
}

.profile-title{
color: black;
}

.profile-box h2{
    position: relative;
    display: flex;
    justify-content: center;
    margin: 20px;
    
   
    
}
.username {
    background-color: rgb(207, 41, 212);
    width:  40vw;
    border-color: black;
    margin-left: auto;
    margin-right: auto;
    margin-top: 80px;
    border: solid;
    border-width: 20px;
    display: flex;
    align-items: center;
    padding: 20px;
    justify-content: center;
    
    

}
.profile-box{
    background-color: rgb(207, 41, 212);
    width:  40vw;
    height: 40vw;
    margin-left: auto;
    margin-right: auto;
    margin-top: 40px;
    border: solid;
    border-width: 20px;
    display: flex;
    align-items: center;
    padding: 20px
    
    
    /* border-radius: 60%; */
}

</style>