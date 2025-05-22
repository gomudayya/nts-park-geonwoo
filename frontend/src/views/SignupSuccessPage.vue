<template>
  <div class="signup-success">
    <h2>🎉 회원가입이 완료되었습니다!</h2>
    <p>이제 로그인을 진행해 주세요.</p>
    <div class="button-wrapper">
      <router-link to="/login" class="button">로그인하러 가기</router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SignupSuccess',
};
</script>

<style scoped>
.signup-success {
  max-width: 500px;
  margin: 3rem auto;
  padding: 2rem;
  text-align: center;
  border: 1px solid #ccc;
  border-radius: 8px;
  font-size: 1.2rem;
}
.button-wrapper {
  margin-top: 2rem;
}
</style>
