<template>
  <BoardDetail
    v-if="board"
    :board="board"
    :like-count="likeCount"
    :like-status="likeStatus"
    @like="handleLikeClick"
    @delete="deleteBoard"
  ></BoardDetail>
</template>

<script>
import {
  fetchBoardDetailApi,
  fetchLikeCount,
  fetchMyLikeStatus,
  createBoardLike,
  deleteBoardLike,
  deleteBoard,
} from '@/api';
import BoardDetail from '@/components/BoardDetail.vue';
export default {
  data() {
    return {
      board: null,
      likeCount: 0,
      likeStatus: false,
    };
  },
  async mounted() {
    const boardId = this.$route.params.id;
    try {
      const boardResponse = await fetchBoardDetailApi(boardId);
      this.board = boardResponse.data.content;

      const likeCountResponse = await fetchLikeCount(boardId);
      this.likeCount = likeCountResponse.data.content.count;

      if (this.isLoggedIn) {
        const likeStatusResponse = await fetchMyLikeStatus(boardId);
        this.likeStatus = likeStatusResponse.data.content.liked;
      }
    } catch (error) {
      console.error('조회 API 호출중 에러 발생', error);
    }
  },
  methods: {
    async handleLikeClick() {
      try {
        const boardId = this.$route.params.id;
        if (this.likeStatus) {
          await deleteBoardLike(boardId);
          this.likeCount--;
        } else {
          await createBoardLike(boardId);
          this.likeCount++;
        }
        this.likeStatus = !this.likeStatus;
      } catch (error) {
        console.error('좋아요 처리중 에러 발생', error);
      }
    },

    async deleteBoard() {
      try {
        await deleteBoard(this.$route.params.id);
      } catch (error) {
        console.error('게시글 삭제중 에러 발생', error);
      }
    },
  },
  computed: {
    isLoggedIn() {
      return this.$store.getters.isLoggedIn;
    },
  },
  components: {
    BoardDetail,
  },
};
</script>

<style scoped>
.board-detail {
  padding: 20px;
  max-width: 800px;
  margin: 0 auto;
}
.content {
  margin-top: 16px;
  white-space: pre-wrap;
}
</style>
