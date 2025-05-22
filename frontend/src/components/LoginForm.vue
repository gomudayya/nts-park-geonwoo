<template>
  <div class="login-form">
    <form @submit.prevent="submitForm">
      <div class="form-group">
        <label for="username">아이디</label>
        <input
          id="username"
          v-model="username"
          type="text"
          placeholder="아이디를 입력하세요"
          required
        />
      </div>

      <div class="form-group">
        <label for="password">비밀번호</label>
        <input
          id="password"
          v-model="password"
          type="password"
          placeholder="비밀번호를 입력하세요"
          required
        />
      </div>
      <p v-if="logMessage" class="message error">
        {{ logMessage }}
      </p>
      <button type="submit" class="button">로그인</button>
    </form>
  </div>
</template>

<script>
import { loginApi } from '@/api/index';

export default {
  data() {
    return {
      username: '',
      password: '',
      logMessage: '',
    };
  },
  methods: {
    async submitForm() {
      const request = {
        username: this.username,
        password: this.password,
      };

      try {
        const response = await loginApi(request);
        const accessToken = response.headers['authorization'];
        localStorage.setItem('accessToken', accessToken);
        this.$store.commit('setNickname', response.data.content.nickname);
        this.$router.push('/');
        this.init();
      } catch (e) {
        console.error(e);
        this.logMessage = '회원정보가 올바르지 않습니다.';
        this.password = '';
      }
    },

    init() {
      this.username = '';
      this.password = '';
      this.logMessage = '';
    },
  },
};
</script>

<style scoped>
.login-form {
  max-width: 500px;
  margin: 0 auto;
  padding: 1.5rem;
  border: 1px solid #ccc;
  border-radius: 8px;
  font-size: 1.1rem;
}
.form-group {
  margin-bottom: 1.5rem;
}
label {
  display: block;
  margin-bottom: 0.7rem;
  font-weight: 600;
}
input {
  width: 100%;
  padding: 0.75rem;
  box-sizing: border-box;
  font-size: 1rem;
  border: 1px solid #bbb;
  border-radius: 4px;
}

.message.error {
  margin-top: 1rem;
  padding: 0.75rem;
  border-radius: 4px;
  font-size: 1rem;
  text-align: center;
  font-weight: 600;
  word-break: keep-all;
  background-color: #f8d7da; /* 연한 빨간색 배경 */
  color: #721c24; /* 진한 빨간색 글자색 */
  border-color: #f5c6cb;
}
</style>
