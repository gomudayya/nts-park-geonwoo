import axios from 'axios';
import { setInterceptors } from '@/api/interceptors';

function createApiInstance() {
  const instance = axios.create({
    baseURL: 'http://localhost:8080',
  });

  return setInterceptors(instance);
}

const api = createApiInstance();

export function signUpApi(userData) {
  return api.post('/users', userData);
}

export function loginApi(loginData) {
  return api.post('/login', loginData);
}

export function getMyInfoApi() {
  return api.get('/users/me'); // 서버의 유저정보 조회 엔드포인트에 맞게 수정
}

export function fetchBoardsApi(pageParam) {
  return api.get('/boards', { params: pageParam });
}

export function createBoardApi(payload) {
  return api.post('/boards', payload);
}

export function updateBoardApi(boardId, payload) {
  return api.put(`/boards/${boardId}`, payload);
}

function fetchBoardDetailApi(boardId) {
  return api.get(`/boards/${boardId}`);
}

export function fetchLikeCount(boardId) {
  return api.get(`/boards/${boardId}/likes/count`);
}

export function fetchMyLikeStatus(boardId) {
  return api.get(`/boards/${boardId}/likes/me`);
}

export function createBoardLike(boardId) {
  return api.post(`/boards/${boardId}/likes`);
}

export function deleteBoardLike(boardId) {
  return api.delete(`/boards/${boardId}/likes`);
}

export function deleteBoard(boardId) {
  return api.delete(`/boards/${boardId}`);
}

export { fetchBoardDetailApi };
