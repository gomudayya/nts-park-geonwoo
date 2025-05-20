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
  },
});

export default store;
