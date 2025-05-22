<template>
  <div class="board-detail">
    <h2 class="title">{{ board.title }}</h2>
    <div class="meta">
      <span class="author">{{ board.writerNickname }}</span>
      <span class="date">{{ formatDate(board.createdTime) }}</span>
    </div>
    <div class="content" v-html="board.content"></div>
    <div class="hashtags" v-if="board.hashtags && board.hashtags.length">
      <span v-for="(tag, index) in board.hashtags" :key="index" class="hashtag">
        {{ tag }}
      </span>
    </div>
    <div class="actions">
      <button
        :class="['like-button', { liked: likeStatus }]"
        @click="$emit('like')"
        aria-pressed="likeStatus"
      >
        <span v-if="likeStatus">❤️</span>
        <span v-else>🤍</span>
        좋아요 ({{ likeCount }})
      </button>
    </div>
  </div>
  <div v-if="board.writerId === userId" class="edit-delete-buttons">
    <button class="edit-button" @click="goToEditPage">수정</button>
    <button class="delete-button" @click="onDeleteClick">삭제</button>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import { formatDateUtil } from '@/util/date';

export default {
  name: 'BoardDetail',
  props: {
    board: {
      type: Object,
      required: true,
    },
    likeCount: {
      type: Number,
      required: true,
    },
    likeStatus: {
      type: Boolean,
      required: true,
    },
  },
  emits: ['like'],
  methods: {
    formatDate(dateStr) {
      return formatDateUtil(dateStr);
    },
    goToEditPage() {
      this.$router.push(`/boards/edit/${this.board.boardId}`);
    },
    onDeleteClick() {
      if (confirm('정말 삭제하시겠습니까?')) {
        this.$emit('delete');
        this.$router.push('/');
      }
    },
  },
  computed: {
    ...mapGetters(['userId']),
  },
};
</script>

<style scoped>
.board-detail {
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
}
.title {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 8px;
}
.meta {
  font-size: 14px;
  color: #666;
  margin-bottom: 16px;
}
.content {
  font-size: 16px;
  line-height: 1.6;
  margin-bottom: 24px;
}
.date {
  margin-left: 15px;
}
.hashtags {
  margin-bottom: 16px;
}
.hashtag {
  display: inline-block;
  background-color: #f0f0f0;
  color: #333;
  padding: 4px 8px;
  margin-right: 8px;
  margin-bottom: 6px;
  border-radius: 12px;
  font-size: 14px;
}
.actions {
  text-align: right;
}
.like-button {
  padding: 6px 12px;
  border: none;
  background-color: #ffabb9;
  color: white;
  border-radius: 4px;
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.3s ease;
  display: flex;
  align-items: center;
  gap: 6px;
}
.like-button:hover {
  background-color: #f3657d;
}
/* 좋아요 눌린 상태 스타일 */
.like-button.liked {
  background-color: #ff1900;
}
.like-button.liked:hover {
  background-color: #fd1500;
}
.edit-button {
  padding: 6px 12px;
  border: none;
  background-color: #4caf50;
  color: white;
  border-radius: 4px;
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.3s ease;
}
.edit-button:hover {
  background-color: #45a049;
}
.delete-button {
  padding: 6px 12px;
  border: none;
  background-color: #f44336;
  color: white;
  border-radius: 4px;
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.3s ease;
}
.delete-button:hover {
  background-color: #da190b;
}

.edit-delete-buttons {
  display: flex;
  justify-content: flex-end;
  gap: 16px;
  margin-top: 16px;
}

.edit-button,
.delete-button {
  padding: 8px 16px;
  font-size: 20px;
  border: none;
  border-radius: 6px;
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.3s ease;
}
</style>
