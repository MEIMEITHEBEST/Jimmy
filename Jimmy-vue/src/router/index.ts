import { createRouter, createWebHistory } from 'vue-router'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/welcome',
      name: 'Welcome',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/WelcomeVue.vue'),
    },
    {
      path: '/index',
      name: 'Index',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/Index.vue'),
        children: [
            {
                path: '/welcome',
                name: 'Welcome',
                component: () => import('../views/WelcomeVue.vue'),
            },
            {
                path: '/admin',
                name: 'Admin',
                component: () => import('../views/admin/List.vue'),
            },
        ],

    },
  ],
})

export default router
