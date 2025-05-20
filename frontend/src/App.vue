<template>
  <div class="app">
    <AppHeader />
    <div class="app-contents">
      <router-view />
    </div>
  </div>
</template>

<script>
import AppHeader from '@/components/common/AppHeader.vue';

export default {
  components: {
    AppHeader,
  },
};
</script>

<style>
@import './css/reset.css';
@import './css/common.css';

.app {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}
.app-contents {
  flex: 1;
  padding: 16px;
  color: #000000; /* 밝은 연두 느낌 */
}
</style>
