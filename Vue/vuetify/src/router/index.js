import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Table from "../views/Table";
import About from '../views/About'
import ExpandableTable from "../views/ExpandableTable";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'home',
        component: Home
    },
    {
        path: '/about',
        name: 'about',
        component: About
    },

    {
        path: '/table',
        name: 'table',
        component: Table
    },

    {
        path: '/expandableTable',
        name: 'Expandable Table',
        component: ExpandableTable
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
