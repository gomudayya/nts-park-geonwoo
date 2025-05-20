<template>
  <div class="signup-wrapper">
    <h2>회원가입</h2>
    <SignupForm class="signup-form" />
  </div>
</template>

<script>
import SignupForm from '@/components/SignupForm.vue';

export default {
  components: {
    SignupForm,
  },
};
</script>

<style scoped>
.signup-wrapper {
  text-align: center;
  margin-bottom: 20px;
}

.signup-wrapper > h2 {
  font-size: 2.5rem;
  margin-bottom: 40px;
}
</style>
