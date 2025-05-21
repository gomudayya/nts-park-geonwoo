import axios from 'axios';
import { setInterceptors } from '@/api/interceptors';

function createApiInstance() {
  const instance = axios.create({
    baseURL: 'http://localhost:8080',
  });

  return setInterceptors(instance);
}

const api = createApiInstance();

export function signUp(userData) {
  return api.post('/users', userData);
}

export function login(loginData) {
  return api.post('/login', loginData);
}

export function fetchUserInfo() {
  return api.get('/users/me'); // 서버의 유저정보 조회 엔드포인트에 맞게 수정
}
