<template>
  <header class="header">
    <router-link to="/" class="logo">NTS-BOARD</router-link>
    <div class="auth-container">
      <template v-if="nickname">
        <span class="nickname">👋{{ nickname }}님 안녕하세요?</span>
        <a href="javascript:;" @click="logoutUser" class="header-button">
          로그아웃
        </a>
      </template>
      <template v-else>
        <router-link to="/login" class="header-button">로그인</router-link>
        <router-link to="/signup" class="header-button">회원가입</router-link>
      </template>
    </div>
  </header>
</template>

<script>
import { mapState } from 'vuex';

export default {
  computed: {
    ...mapState(['nickname']),
  },
  methods: {
    logoutUser() {
      this.$store.commit('clearNickname');
      localStorage.removeItem('accessToken');
      this.$router.push('/');
    },
  },
};
</script>

<style scoped>
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 20px;
  background-color: #222;
}
.logo {
  font-size: 24px;
  font-weight: 900;
  color: #7cfc00;
  text-decoration: none;
}
.auth-container {
  display: flex;
  gap: 12px;
}
.header-button {
  color: white;
  text-decoration: none;
  font-weight: 600;
  padding: 6px 14px;
  border-radius: 4px;
  background-color: rgba(124, 252, 0, 0.2);
  transition: background-color 0.3s ease;
}
.header-button:hover {
  background-color: #7cfc00;
  color: black;
}
.nickname {
  color: white;
  font-weight: 600;
  font-size: 1rem;
}
</style>
