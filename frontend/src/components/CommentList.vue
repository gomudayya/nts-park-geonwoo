<template>
  <div class="comment-list">
    <div
      v-for="comment in comments"
      :key="comment.commentId"
      class="comment-item"
    >
      <p
        ><strong>{{ comment.writerNickname }}</strong></p
      >
      <p>{{ comment.content }}</p>
      <small>{{ comment.createdTime }}</small>
      <div v-if="comment.writerId === currentUserId" class="comment-actions">
        <button @click="deleteComment(comment.commentId)">삭제</button>
      </div>
    </div>
  </div>
  <button v-if="hasNext" @click="loadMore" class="load-more-btn">더보기</button>
  <div class="comment-input" v-if="isLoggedIn">
    <textarea
      v-model="commentText"
      placeholder="댓글을 작성하세요..."
      rows="3"
    ></textarea>
    <button @click="submitComment" :disabled="!commentText.trim()">작성</button>
  </div>
</template>

<script>
import { fetchCommentsApi, deleteCommentApi } from '@/api';

export default {
  emits: ['submitComment'],
  data() {
    return {
      comments: [],
      commentText: '',
      pageNumber: 0,
      pageSize: 5,
      hasNext: false,
    };
  },
  mounted() {
    this.loadComments();
  },
  methods: {
    async submitComment() {
      if (!this.commentText.trim()) return;
      this.$emit('submitComment', this.commentText.trim());
      this.commentText = '';
      this.$router.go(0);
    },
    loadMore() {
      this.loadComments();
    },
    async loadComments() {
      try {
        const response = await fetchCommentsApi(
          this.$route.params.id,
          this.pageNumber,
          this.pageSize
        );
        const content = response.data.content;
        this.comments = [...this.comments, ...content.content];
        this.hasNext = content.hasNext;
        this.pageNumber = content.pageNumber + 1;
      } catch (error) {
        console.log('댓글 조회 실패', error);
      }
    },
    async deleteComment(commentId) {
      if (!confirm('댓글을 정말 삭제하시겠습니까?')) {
        return;
      }

      try {
        await deleteCommentApi(this.$route.params.id, commentId);
        alert('댓글이 삭제되었습니다.');
      } catch (error) {
        console.error('댓글 삭제 실패:', error);
      } finally {
        this.$router.go(0);
      }
    },
  },
  computed: {
    isLoggedIn() {
      return this.$store.getters.isLoggedIn;
    },
    currentUserId() {
      return this.$store.getters.userId; // 스토어에 맞게 키 이름 확인하세요
    },
  },
};
</script>

<style scoped>
.comment-input {
  display: flex;
  flex-direction: column;
  gap: 8px;
}
textarea {
  resize: vertical;
  padding: 8px;
  font-size: 16px;
}
button {
  align-self: flex-end;
  padding: 6px 12px;
  font-weight: bold;
  cursor: pointer;
}
button:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}
.comment-list {
  width: 100%;
  padding: 24px 16px;
  background-color: #f9fafb;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  box-sizing: border-box;
}

.comment-item {
  background: white;
  border-radius: 8px;
  padding: 20px 24px;
  margin-bottom: 20px;
  box-shadow: 0 3px 8px rgba(0, 0, 0, 0.1);
  transition: background-color 0.2s ease;
  font-size: 18px;
}

.comment-item:hover {
  background-color: #e6f9f0; /* 연한 녹색 배경 */
}

.comment-item strong {
  font-weight: 700;
  color: #166534; /* 진한 녹색 */
  font-size: 20px;
}

.comment-item p {
  margin: 12px 0;
  color: #2f855a; /* 중간 녹색 */
  line-height: 1.6;
}

.comment-item small {
  display: block;
  color: #6b7280; /* 회색 톤 */
  font-size: 0.9em;
  margin-top: 8px;
}

.comment-input {
  width: 100%;
  padding: 24px 16px;
  display: flex;
  flex-direction: column;
  gap: 12px;
  box-sizing: border-box;
  background-color: #fff;
  border-top: 1px solid #c6f6d5; /* 연한 녹색 테두리 */
}

textarea {
  width: 100%;
  resize: vertical;
  min-height: 100px;
  padding: 16px 20px;
  font-size: 18px;
  border: 2px solid #68d391; /* 밝은 녹색 */
  border-radius: 10px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  transition: border-color 0.3s ease;
}

textarea:focus {
  outline: none;
  border-color: #2f855a; /* 중간 녹색 */
  box-shadow: 0 0 8px rgba(47, 133, 90, 0.4);
}

button {
  align-self: flex-end;
  padding: 12px 28px;
  font-weight: 700;
  background-color: #2f855a; /* 중간 녹색 */
  border: none;
  color: white;
  border-radius: 10px;
  font-size: 16px;
  cursor: pointer;
  user-select: none;
  transition: background-color 0.3s ease;
}

button:hover:not(:disabled) {
  background-color: #276749; /* 진한 녹색 */
}

button:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}

button:focus-visible {
  outline: 3px solid #276749;
  outline-offset: 2px;
}

.load-more-btn {
  display: block;
  margin: 24px auto;
  padding: 14px 36px;
  font-size: 16px;
  font-weight: 700;
  border: 2px solid #2f855a;
  border-radius: 10px;
  background-color: white;
  color: #2f855a;
  cursor: pointer;
  transition:
    background-color 0.3s ease,
    color 0.3s ease;
  user-select: none;
}

.load-more-btn:hover {
  background-color: #2f855a;
  color: white;
}
.comment-actions {
  display: flex;
  justify-content: flex-end;
  margin-top: 8px;
}

.comment-actions button {
  background-color: #e53e3e; /* 빨간 삭제색 */
  color: white;
  border: none;
  padding: 6px 12px;
  font-size: 14px;
  border-radius: 6px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  user-select: none;
}

.comment-actions button:hover {
  background-color: #9b2c2c;
}
.comment-input {
  width: 100%;
  max-width: 720px; /* 최대 너비 제한 */
  margin: 0 auto; /* 가운데 정렬 */
  padding: 24px 16px;
  display: flex;
  flex-direction: column;
  gap: 12px;
  box-sizing: border-box;
  background-color: #fff;
  border-top: 1px solid #c6f6d5;
}
textarea {
  width: 100%;
  max-width: 720px;
  resize: vertical;
  min-height: 100px;
  padding: 16px 20px;
  font-size: 18px;
  border: 2px solid #68d391;
  border-radius: 10px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  transition: border-color 0.3s ease;
  box-sizing: border-box;
}
.load-more-btn {
  display: block;
  margin: 16px auto 32px; /* 위쪽 간격 줄이고 아래쪽 간격 늘림 */
  padding: 18px 48px; /* 버튼 패딩 키움 */
  font-size: 18px; /* 글자 크기 키움 */
  font-weight: 700;
  border: 2px solid #2f855a;
  border-radius: 10px;
  background-color: white;
  color: #2f855a;
  cursor: pointer;
  transition:
    background-color 0.3s ease,
    color 0.3s ease;
  user-select: none;
}

.load-more-btn:hover {
  background-color: #2f855a;
  color: white;
}
</style>
