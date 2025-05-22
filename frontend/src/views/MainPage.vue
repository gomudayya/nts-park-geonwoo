<template>
  <BoardList :boards="boards" />
  <Pagination
    :page-number="pageNumber"
    :total-pages="totalPages"
    @page-changed="handlePageChange"
  />
</template>

<script>
import BoardList from '@/components/BoardList.vue';
import Pagination from '@/components/Pagination.vue';
import { fetchBoardsApi } from '@/api';

export default {
  data() {
    return {
      boards: [],
      pageNumber: 0,
      pageSize: 10,
      totalElements: 0,
      totalPages: 0,
    };
  },
  components: {
    BoardList,
    Pagination,
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
  },
};
</script>

<style scoped></style>
