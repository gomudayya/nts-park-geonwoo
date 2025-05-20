import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080',
});

export function signUp(userData) {
  return api.post('/users', userData);
}

export function login(loginData) {
  return api.post('/login', loginData);
}
