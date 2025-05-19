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

<style scoped>
@import './css/reset.css';

.app {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

.app-contents {
  flex: 1;
  padding: 16px;
}
</style>
