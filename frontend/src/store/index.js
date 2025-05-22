import { createStore } from 'vuex';

const store = createStore({
  state() {
    return {
      nickname: '',
    };
  },
  mutations: {
    setNickname(state, nickname) {
      state.nickname = nickname;
    },
    clearNickname(state) {
      state.nickname = '';
    },
  },
});

export default store;
