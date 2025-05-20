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

      <button type="submit" class="button">로그인</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      username: '',
      password: '',
    };
  },
  methods: {
    submitForm() {
      // TODO: 로그인 API 호출
      console.log('로그인:', this.username, this.password);
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
</style>
