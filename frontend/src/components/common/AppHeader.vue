<template>
  <header class="header">
    <router-link to="/" class="logo">NTS-BOARD</router-link>
    <div class="auth-buttons">
      <router-link to="/login" class="btn">로그인</router-link>
      <router-link to="/signup" class="btn">회원가입</router-link>
    </div>
  </header>
</template>

<script>
export default {};
</script>

<style scoped>
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 20px;
  background-color: #222; /* 어두운 배경 */
}

.logo {
  font-size: 24px;
  font-weight: 900;
  color: #7cfc00; /* 연두색 */
  text-decoration: none;
}

.auth-buttons {
  display: flex;
  gap: 12px;
}
</style>
