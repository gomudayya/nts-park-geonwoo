import { createRouter, createWebHistory } from 'vue-router';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      component: () => import('@/views/MainPage.vue'),
    },
    {
      path: '/signup',
      component: () => import('@/views/SignupPage.vue'),
    },
    {
      path: '/login',
      component: () => import('@/views/LoginPage.vue'),
    },
    {
      path: '/signup-success',
      component: () => import('@/views/SignupSuccessPage.vue'),
    },
    {
      path: '/boards/create',
      component: () => import('@/views/BoardCreatePage.vue'),
    },
    {
      path: '/boards/edit/:id',
      component: () => import('@/views/BoardEditPage.vue'),
      props: true,
    },
    {
      path: '/boards/:id',
      component: () => import('@/views/BoardDetailPage.vue'),
    },
  ],
});

export default router;
