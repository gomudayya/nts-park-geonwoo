<template>
  <div class="board-page">
    <h2 class="board-title">게시글 수정하기</h2>
    <BoardForm :boardData="board" :isEdit="true" @update-board="handleSubmit" />
  </div>
</template>

<script>
import BoardForm from '@/components/BoardForm.vue';
import { updateBoardApi, fetchBoardDetailApi } from '@/api';
export default {
  components: {
    BoardForm,
  },
  data() {
    return {
      board: null,
    };
  },
  async created() {
    try {
      const id = this.$route.params.id;
      const res = await fetchBoardDetailApi(id);
      this.board = res.data.content;
    } catch (err) {
      console.error('게시글 불러오기 실패:', err);
    }
  },
  methods: {
    async handleSubmit(formData) {
      try {
        const id = this.$route.params.id;
        await updateBoardApi(id, formData);
        this.$router.push(`/boards/${id}`);
      } catch (err) {
        console.error('게시글 수정 실패:', err);
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
  color: #000000;
}
</style>
