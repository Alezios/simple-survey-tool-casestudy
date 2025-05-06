import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/about',
      name: 'about',
      component: () => import('../views/AboutView.vue'),
    },
    {
      path: '/surveys',
      name: 'surveys',
      component: () => import('../views/surveys/SurveyList.vue'),
    },
    {
      path: '/survey/:id',
      name: 'survey',
      component: () => import('../views/surveys/Survey.vue'),
      props: true
    },
  ],
})

export default router
