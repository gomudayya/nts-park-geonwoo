import { createStore } from 'vuex';

const store = createStore({
  state() {
    return {
      nickname: '',
      userId: '',
    };
  },
  mutations: {
    setNickname(state, nickname) {
      state.nickname = nickname;
    },
    clearUserInfo(state) {
      state.nickname = '';
      state.userId = '';
    },
    setUserId(state, userId) {
      state.userId = userId;
    },
  },
  getters: {
    isLoggedIn(state) {
      return !!state.nickname;
    },
    userId(state) {
      return state.userId;
    },
  },
});

export default store;
