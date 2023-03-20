import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "@/views/Login";
import MemoEdit from "@/views/MemoEdit";
import MemoDetail from "@/views/MemoDetail";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Index',
    redirect:{name: 'Memo'}
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('@/views/Register')
  },
  {
    path: '/memo/list',
    name: 'Memo',
    component: () => import('@/views/Memo.vue')
  },
  {
    path: '/memo/add',
    name: 'MemoAdd',
    component: MemoEdit
  },
  {
    path: '/memo/:memoId',
    name: 'MemoDetail',
    component: MemoDetail
  },
  {
    path: '/memo/:memoId/edit',
    name: 'MemoEdit',
    component: MemoEdit
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
