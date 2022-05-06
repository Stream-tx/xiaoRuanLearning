import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Main.vue'


const routes = [
  {
    path: '/',
    name: 'Login',
    component: () => import('../views/Login.vue'),
  },
  {
    path: '/hdoj',
    name: 'hdoj',
    redirect: '/hdoj/bank',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/Main.vue'),
    children: [{
        path: '/hdoj/bank',
        name:'Bank',
        //component: () => import('../views/mainPage.vue'),
        component: () => import('../views/Bank.vue'),
      },
      {
        path: '/hdoj/bank/q/:id',
        name:'Question',
        //component: () => import('../views/mainPage.vue'),
        component: () => import('../views/Question.vue'),
      },
      {
        path: '/hdoj/material',
        //component: () => import('../views/mainPage.vue'),
        component: () => import('../views/Material.vue'),
      },
      {
        path: '/hdoj/online',
        //component: () => import('../views/mainPage.vue'),
        component: () => import('../views/Online.vue'),
      },
      {
        path: '/hdoj/myInfo',
        //component: () => import('../views/mainPage.vue'),
        component: () => import('../views/MyInfo.vue'),
      },
      {
        path: '/hdoj/alterInfo',
        //component: () => import('../views/mainPage.vue'),
        component: () => import('../views/alterMyInfo.vue'),
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
