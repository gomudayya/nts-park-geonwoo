<template>
  <div class="login-wrapper">
    <h2>로그인</h2>
    <LoginForm class="login-form" />
  </div>
</template>

<script>
import LoginForm from '@/components/LoginForm.vue';

export default {
  components: {
    LoginForm,
  },
};
</script>

<style scoped>
.login-wrapper {
  text-align: center;
  margin-bottom: 20px;
}

.login-wrapper > h2 {
  font-size: 2.5rem;
  margin-bottom: 40px;
}
</style>
