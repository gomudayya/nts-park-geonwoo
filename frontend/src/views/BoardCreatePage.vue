<template>
  <div class="board-page">
    <h2 class="board-title">게시글 작성하기</h2>
    <BoardForm @create-board="handleSubmit" />
  </div>
</template>
<script>
import BoardForm from '@/components/BoardForm.vue';
import { createBoardApi } from '@/api'; // API 함수 임포트

export default {
  components: {
    BoardForm,
  },
  methods: {
    async handleSubmit(formData) {
      try {
        await createBoardApi(formData);
        this.$router.push('/');
      } catch (error) {
        console.error('게시글 작성 실패:', error);
        alert('게시글 작성 중 문제가 발생했습니다.');
      }
    },
  },
};
</script>

<style scoped>
.board-page {
  max-width: 700px;
  margin: 0 auto;
  padding: 30px 20px;
}

.board-title {
  text-align: center;
  margin-bottom: 24px;
  font-size: 24px;
  font-weight: bold;
  color: #333;
}
</style>
