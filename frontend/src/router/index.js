import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Main.vue'


const routes = [
  {
    path: '/',
    name: 'Login',
    component: () => import('../views/Login.vue'),
  },
  {
    path: '/miniSoft',
    name: 'miniSoft',
    redirect: '/miniSoft/bank',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/Main.vue'),
    children: [{
        path: '/miniSoft/bank',
        name:'Bank',
        //component: () => import('../views/mainPage.vue'),
        component: () => import('../views/Bank.vue'),
      },
      {
        path: '/miniSoft/bank/q/:id',
        name:'Question',
        //component: () => import('../views/mainPage.vue'),
        component: () => import('../views/Question.vue'),
      },
      {
        path: '/miniSoft/material',
        //component: () => import('../views/mainPage.vue'),
        component: () => import('../views/Material.vue'),
      },
      {
        path: '/miniSoft/online',
        //component: () => import('../views/mainPage.vue'),
        component: () => import('../views/Online.vue'),
      },
      {
        path: '/miniSoft/myInfo',
        //component: () => import('../views/mainPage.vue'),
        component: () => import('../views/MyInfo.vue'),
      },
      {
        path: '/miniSoft/otherInfo',
        //component: () => import('../views/mainPage.vue'),
        component: () => import('../views/OtherInfo.vue'),
      },
      {
        path: '/miniSoft/alterInfo',
        //component: () => import('../views/mainPage.vue'),
        component: () => import('../views/alterMyInfo.vue'),
      },{
        path: '/miniSoft/webIDE',
        //component: () => import('../views/mainPage.vue'),
        component: () => import('../views/WebIDE.vue'),
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
