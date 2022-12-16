import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import Auction from '../views/Auction.vue'
import store from '../store/index'
import NewAuction from '../views/NewAuction.vue'
import UpdateAuction from '../views/UpdateAuction.vue'
import Bids from '../views/Bids'
import Profile from '../views/Profile'
Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        title: "Home",
        requiresAuth: false
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        title: "Logout",
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        title: "Register",
        requiresAuth: false
      }
    },
    {
      path: "/create",
      name: "create",
      component: NewAuction,
      meta: {
        title: "Create",
        requiresAuth: true
      }
    },
    {
      path: "/auctions/:id",
      name: "auction",
      component: Auction,
      meta: {
        title: "Auction",
        requiresAuth: false
      }
    },
    {
      path: "/profile",
      name: "profile",
      component: Profile,
      meta: {
        title: "Profile",
        requiresAuth: true
      }
    },
    {
      path: "/auctions/:id/edit",
      name: "update",
      component: UpdateAuction,
      meta: {
        title: "Update Auction",
        requiresAuth: true
      }
    },
    {
      path: "/bids",
      name: "bids",
      component: Bids,
      meta: {
        title: "Bids",
        requiresAuth: true
      },
    }
   
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

router.beforeEach((to, from, next) => {
  document.title = `${to.meta.title} | Silent Auction `;
  next();
});

export default router;
