<template>
  <form class="board-form" @submit.prevent="handleSubmit">
    <div class="form-group">
      <label for="title">제목</label>
      <input
        id="title"
        v-model="form.title"
        placeholder="제목을 입력하세요"
        required
      />
    </div>

    <div class="form-group">
      <label for="content">내용</label>
      <textarea
        id="content"
        v-model="form.content"
        placeholder="내용을 입력하세요"
        rows="10"
        required
      ></textarea>
    </div>

    <div class="form-group">
      <label>해시태그</label>
      <div class="tag-input-wrapper" @click="focusInput">
        <span v-for="(tag, index) in hashtags" :key="index" class="tag">
          {{ tag }}
          <button type="button" @click.stop="removeTag(index)">×</button>
        </span>
        <input
          ref="tagInput"
          v-model="newTag"
          @keydown.enter.prevent="addTag"
          @keydown.delete="handleDelete"
          :placeholder="
            hashtags.length >= maxTags
              ? '해시태그는 최대 5개 입니다.'
              : '해시태그 입력 후 Enter'
          "
          :disabled="hashtags.length >= maxTags"
        />
      </div>
    </div>

    <div class="button-wrapper">
      <button type="submit" class="button">{{
        isEdit ? '수정' : '작성'
      }}</button>
    </div>
  </form>
</template>

<script>
export default {
  props: {
    boardData: Object,
    isEdit: Boolean,
  },
  data() {
    return {
      form: {
        title: this.boardData?.title || '',
        content: this.boardData?.content || '',
      },
      hashtags: this.boardData?.hashtags ? [...this.boardData.hashtags] : [],
      newTag: '',
      maxTags: 5,
    };
  },
  methods: {
    focusInput() {
      if (this.hashtags.length < this.maxTags) {
        this.$refs.tagInput.focus();
      }
    },
    addTag() {
      const tag = this.newTag.trim();
      if (tag && !this.hashtags.includes(tag)) {
        if (this.hashtags.length < this.maxTags) {
          this.hashtags.push(tag);
        }
      }
      this.newTag = '';
    },
    removeTag(index) {
      this.hashtags.splice(index, 1);
    },
    handleDelete(event) {
      if (this.newTag === '' && event.key === 'Delete') {
        this.hashtags.pop();
      }
    },
    handleSubmit() {
      const payload = {
        title: this.form.title,
        content: this.form.content,
        hashtags: this.hashtags,
      };

      if (this.isEdit) {
        this.$emit('update-board', payload);
      } else {
        this.$emit('create-board', payload);
      }
    },
  },
  watch: {
    boardData: {
      immediate: true,
      handler(newValue) {
        if (newValue) {
          this.form.title = newValue.title || '';
          this.form.content = newValue.content || '';
          this.hashtags = newValue.hashtags ? [...newValue.hashtags] : [];
        }
      },
    },
  },
};
</script>

<style scoped>
/* 스타일은 그대로 유지 */
.board-form {
  max-width: 700px;
  margin: 30px auto;
  padding: 24px;
  border: 1px solid #ccc;
  border-radius: 12px;
  background-color: #fafafa;
  color: #333;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.form-group {
  display: flex;
  flex-direction: column;
  margin-bottom: 20px;
}

label {
  font-weight: 600;
  margin-bottom: 8px;
  color: #444;
}

input,
textarea {
  padding: 10px;
  border: 1px solid #bbb;
  border-radius: 8px;
  font-size: 16px;
  transition: border-color 0.3s;
  background-color: #fff;
  color: #222;
}

input::placeholder,
textarea::placeholder {
  color: #999;
}

input:focus,
textarea:focus {
  outline: none;
  border-color: #555;
}

.button-wrapper {
  display: flex;
  justify-content: flex-end;
}

.tag {
  background-color: #ddd;
  color: #333;
  padding: 6px 12px;
  border-radius: 15px;
  margin-right: 6px;
  margin-bottom: 6px;
  display: flex;
  align-items: center;
  font-size: 14px;
  line-height: 1.4;
  user-select: none;
  transition: background-color 0.3s;
}

.tag button {
  background: transparent;
  border: none;
  color: #555;
  font-weight: bold;
  margin-left: 6px;
  cursor: pointer;
  font-size: 16px;
  line-height: 1;
  padding: 0;
  transition: color 0.3s;
}

.tag button:hover {
  color: #111;
}

.tag:hover {
  background-color: #bbb;
}
</style>
