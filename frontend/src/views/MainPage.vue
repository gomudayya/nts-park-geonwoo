<template>
  <div class="summary-info">
    전체 게시글 수: {{ totalBoardCount }}개 | 전체 댓글 수:
    {{ totalCommentCount }}개
  </div>
  <BoardList :boards="boards" />
  <template v-if="nickname">
    <div class="button-container">
      <router-link to="/boards/create" class="button">
        글 작성하기
      </router-link>
    </div>
  </template>

  <PageBox
    :page-number="pageNumber"
    :total-pages="totalPages"
    @page-changed="handlePageChange"
  />
</template>

<script>
import BoardList from '@/components/BoardList.vue';
import PageBox from '@/components/common/PageBox.vue';
import {
  fetchBoardsApi,
  fetchTotalCommentCountApi,
  fetchTotalBoardCountApi,
} from '@/api';
import { mapState } from 'vuex';

export default {
  computed: {
    ...mapState(['nickname']),
  },
  data() {
    return {
      boards: [],
      pageNumber: 0,
      pageSize: 10,
      totalElements: 0,
      totalPages: 0,
      totalBoardCount: 0,
      totalCommentCount: 0,
    };
  },
  components: {
    BoardList,
    PageBox,
  },
  watch: {
    '$route.query.pageNumber': {
      immediate: true,
      handler(newPage) {
        this.pageNumber = newPage && newPage > 0 ? newPage - 1 : 0;
        this.loadBoards();
      },
    },
  },
  methods: {
    async loadBoards() {
      const response = await fetchBoardsApi({
        page: this.pageNumber,
        size: 10,
      });
      const content = response.data.content;

      this.boards = content.content; // 게시글 리스트
      this.pageNumber = content.pageNumber;
      this.pageSize = content.pageSize;
      this.totalElements = content.totalElements;
      this.totalPages = content.totalPages;
    },
    handlePageChange(newPage) {
      this.$router.push({
        query: { ...this.$route.query, pageNumber: newPage + 1 },
      });
    },
    async loadBoardAndCommentCount() {
      try {
        let response = await fetchTotalBoardCountApi();
        this.totalBoardCount = response.data.content.count;

        response = await fetchTotalCommentCountApi();
        this.totalCommentCount = response.data.content.count;
      } catch (error) {
        console.log(error);
      }
    },
  },
  mounted() {
    this.loadBoardAndCommentCount();
  },
};
</script>

<style scoped>
.button-container {
  display: flex;
  justify-content: flex-end;
  margin: 16px 0;
}

.button {
  display: inline-block;
  padding: 8px 12px;
  min-width: 100px;
  max-width: fit-content;
  text-align: center;
  text-decoration: none;
  border-radius: 4px;
  font-weight: 600;
  cursor: pointer;
  user-select: none;
  white-space: nowrap;
  transition: background-color 0.3s ease;
}
.summary-info {
  font-size: 1rem;
  font-weight: 500;
}
</style>
