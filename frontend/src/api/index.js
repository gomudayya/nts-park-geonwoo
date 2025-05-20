import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080',
});

function signUp(userData) {
  return api.post('/users', userData);
}

export { signUp };
