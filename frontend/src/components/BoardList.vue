<template>
  <div>
    <table>
      <thead>
        <tr>
          <th>제목</th>
          <th>작성자</th>
          <th>작성일시</th>
          <th>댓글 수</th>
          <th>조회수</th>
          <th>좋아요 수</th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="board in boards"
          :key="board.id"
          @click="goToBoardDetail(board.boardId)"
          style="cursor: pointer"
        >
          <td
            >{{ board.title }}
            <span v-if="isNewBoard(board)" class="new-badge">New</span>
          </td>
          <td>{{ board.writerNickname }}</td>
          <td>{{ formatDate(board.createdTime) }}</td>
          <td>{{ board.commentCount }}</td>
          <td>{{ board.viewCount }}</td>
          <td>{{ board.likeCount }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import router from '@/router';

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
      const date = new Date(dateStr);
      return date.toLocaleString(undefined, {
        month: 'numeric',
        day: 'numeric',
        hour: '2-digit',
        minute: '2-digit',
        hour12: false,
      });
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
table {
  width: 100%;
  border-collapse: collapse;
}
thead th {
  border-bottom: 1px solid #ddd;
  padding: 8px;
}
tbody td {
  border-bottom: 1px solid #eee;
  padding: 8px;
}
tbody tr:hover {
  background-color: #f9f9f9;
}
.new-badge {
  display: inline-block;
  margin-left: 6px;
  padding: 2px 6px;
  font-size: 0.75rem;
  background-color: #ff3b30; /* 빨강 */
  color: white;
  border-radius: 12px;
  font-weight: bold;
  vertical-align: middle;
}
</style>
