<template>
  <div class="board-table-container">
    <table class="board-table">
      <thead>
        <tr>
          <th class="title-col">제목</th>
          <th class="writer-col">작성자</th>
          <th class="date-col">작성일시</th>
          <th class="count-col">댓글</th>
          <th class="count-col">조회</th>
          <th class="count-col">좋아요</th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="board in boards"
          :key="board.id"
          @click="goToBoardDetail(board.boardId)"
          class="table-row"
        >
          <td class="title-col title-cell">
            {{ board.title }}
            <span v-if="isNewBoard(board)" class="new-badge">New</span>
          </td>
          <td class="writer-col text-center">{{ board.writerNickname }}</td>
          <td class="date-col text-center">{{
            formatDate(board.createdTime)
          }}</td>
          <td class="count-col text-center">{{ board.commentCount }}</td>
          <td class="count-col text-center">{{ board.viewCount }}</td>
          <td class="count-col text-center">{{ board.likeCount }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import router from '@/router';
import { formatDateUtil } from '@/util/date';
export default {
  props: {
    boards: {
      type: Array,
      required: true,
    },
  },
  methods: {
    goToBoardDetail(boardId) {
      router.push(`/boards/${boardId}`);
    },
    formatDate(dateStr) {
      return formatDateUtil(dateStr);
    },
    isNewBoard(board) {
      const created = new Date(board.createdTime);
      const now = new Date();
      const diffMs = now - created;
      const diffDays = diffMs / (1000 * 60 * 60 * 24);
      return diffDays <= 3;
    },
  },
};
</script>

<style scoped>
.board-table-container {
  width: 100%;
  overflow-x: auto;
  padding: 12px;
}

.board-table {
  width: 100%;
  border-collapse: collapse;
  table-layout: fixed;
  font-size: 0.95rem;
}

thead th {
  background-color: #f1f3f5;
  padding: 10px;
  text-align: center;
  font-weight: bold;
  border-bottom: 2px solid #dee2e6;
}

tbody td {
  padding: 12px;
  border-bottom: 1px solid #f1f3f5;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.table-row:hover {
  background-color: #f8f9fa;
  cursor: pointer;
}

/* 각 컬럼 너비 및 정렬 조정 */
.title-col {
  width: 40%;
  text-align: left;
}
.writer-col {
  width: 12%;
  text-align: center;
}
.date-col {
  width: 20%;
  text-align: center;
}
.count-col {
  width: 9%;
  text-align: center;
}

.title-cell {
  font-weight: 500;
  color: #212529;
}

.text-center {
  text-align: center;
}

.new-badge {
  display: inline-block;
  margin-left: 6px;
  padding: 2px 6px;
  font-size: 0.75rem;
  background-color: #ff3b30;
  color: white;
  border-radius: 12px;
  font-weight: bold;
  vertical-align: middle;
}
</style>
