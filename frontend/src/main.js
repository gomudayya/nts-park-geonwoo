import { createApp } from 'vue';
import App from './App.vue';
import router from '@/router';
import store from '@/store';
import { getMyInfoApi } from '@/api';

async function initApp() {
  const accessToken = localStorage.getItem('accessToken');

  if (accessToken) {
    try {
      const userInfo = await getMyInfoApi();
      store.commit('setNickname', userInfo.data.content.nickname);
      store.commit('setUserId', userInfo.data.content.userId);
    } catch (error) {
      console.warn('토큰 만료 또는 유효하지 않음:', error);
      localStorage.removeItem('accessToken');
    }
  }

  const app = createApp(App);
  app.use(router);
  app.use(store);
  app.mount('#app');
}

initApp();
