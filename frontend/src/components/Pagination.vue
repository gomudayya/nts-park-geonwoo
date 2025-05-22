<template>
  <div class="pagination">
    <button
      @click="
        changePage(Math.floor(pageNumber / pageGroupSize) * pageGroupSize - 1)
      "
      :disabled="Math.floor(pageNumber / pageGroupSize) === 0"
    >
      이전
    </button>

    <button
      v-for="page in pageGroup"
      :key="page"
      @click="changePage(page - 1)"
      :class="{ active: page - 1 === pageNumber }"
    >
      {{ page }}
    </button>

    <button
      @click="
        changePage(
          Math.floor(pageNumber / pageGroupSize) * pageGroupSize + pageGroupSize
        )
      "
      :disabled="
        Math.floor(pageNumber / pageGroupSize) >=
        Math.floor((totalPages - 1) / pageGroupSize)
      "
    >
      다음
    </button>
  </div>
</template>

<script>
export default {
  props: {
    pageNumber: { type: Number, required: true },
    totalPages: { type: Number, required: true },
  },
  computed: {
    pageGroupSize() {
      return 5;
    },
    startPage() {
      return (
        Math.floor(this.pageNumber / this.pageGroupSize) * this.pageGroupSize +
        1
      );
    },
    endPage() {
      return Math.min(this.startPage + this.pageGroupSize - 1, this.totalPages);
    },
    pageGroup() {
      const pages = [];
      for (let i = this.startPage; i <= this.endPage; i++) {
        pages.push(i);
      }
      return pages;
    },
  },
  methods: {
    changePage(page) {
      if (page >= 0 && page < this.totalPages) {
        this.$emit('page-changed', page);
      }
    },
  },
};
</script>
<style scoped>
.pagination {
  display: flex;
  justify-content: center;
  gap: 6px;
  margin-top: 20px;
}
button {
  padding: 6px 12px;
  border: 1px solid #ccc;
  background: white;
  cursor: pointer;
}
button.active {
  background: #eee;
  font-weight: bold;
}
button:disabled {
  color: #aaa;
  cursor: not-allowed;
}
</style>
