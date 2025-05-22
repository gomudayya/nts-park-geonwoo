import store from '@/store/index';
import router from '@/router/index';

export function setInterceptors(instance) {
  // Add a request interceptor
  instance.interceptors.request.use(
    function (config) {
      config.headers.Authorization = localStorage.getItem('accessToken');
      return config;
    },
    function (error) {
      // Do something with request error
      return Promise.reject(error);
    }
  );

  // Add a response interceptor
  instance.interceptors.response.use(
    function (response) {
      return response;
    },
    function (error) {
      if (error.response?.status === 401) {
        localStorage.removeItem('AccessToken');
        store.commit('clearNickname');
        router.push('/login');
      }
      return Promise.reject(error);
    }
  );
  return instance;
}
