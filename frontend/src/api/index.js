import axios from 'axios';
import { setInterceptors } from '@/api/interceptors';

function createApiInstance() {
  const instance = axios.create({
    baseURL: 'http://127.0.0.1:8080',
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

export function fetchBoardDetailApi(boardId) {
  return api.get(`/boards/${boardId}`);
}

export function fetchLikeCountApi(boardId) {
  return api.get(`/boards/${boardId}/likes/count`);
}

export function fetchMyLikeStatusApi(boardId) {
  return api.get(`/boards/${boardId}/likes/me`);
}

export function createBoardLikeApi(boardId) {
  return api.post(`/boards/${boardId}/likes`);
}

export function deleteBoardLikeApi(boardId) {
  return api.delete(`/boards/${boardId}/likes`);
}

export function deleteBoardApi(boardId) {
  return api.delete(`/boards/${boardId}`);
}

export function createCommentApi(boardId, comment) {
  return api.post(`/boards/${boardId}/comments`, {
    content: comment,
  });
}

export function fetchCommentsApi(boardId, page = 0, size = 5) {
  return api.get(`/boards/${boardId}/comments`, {
    params: {
      page,
      size,
    },
  });
}

export function deleteCommentApi(boardId, commentId) {
  return api.delete(`/boards/${boardId}/comments/${commentId}`);
}

export function fetchTotalCommentCountApi() {
  return api.get('/comments/count');
}

export function fetchTotalBoardCountApi() {
  return api.get('/boards/count');
}
