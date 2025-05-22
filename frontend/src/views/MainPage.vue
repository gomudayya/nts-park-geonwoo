<template>
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
import { fetchBoardsApi } from '@/api';
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
</style>
