<template>
  <div class="container">
    <div class="handle-box">
      <el-input v-model="searchUserWord" placeholder="筛选用户" class="search-input"></el-input>
      <el-input v-model="searchSongWord" placeholder="筛选歌曲" class="search-input"></el-input>
    </div>

    <el-table height="550px" border size="small" :data="data">
      <el-table-column label="ID" prop="id" width="50" align="center"></el-table-column>
      <el-table-column label="用户名" prop="username" width="120" align="center"></el-table-column>
      <el-table-column label="歌曲名称" prop="songName" width="200" align="center"></el-table-column>
      <el-table-column label="播放时间" width="180" align="center">
        <template v-slot="scope">
          <div>{{ formatDate(scope.row.playTime) }}</div>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      class="pagination"
      background
      layout="total, prev, pager, next"
      :current-page="currentPage"
      :page-size="pageSize"
      :total="tableData.length"
      @current-change="handleCurrentChange"
    >
    </el-pagination>
  </div>
</template>

<script lang="ts">
import { defineComponent, getCurrentInstance, watch, ref, reactive, computed } from "vue";
import { HttpManager } from "@/api";

export default defineComponent({
  setup() {
    const { proxy } = getCurrentInstance();

    const tableData = ref([]);
    const tempDate = ref([]);
    const pageSize = ref(10);
    const currentPage = ref(1);

    const data = computed(() => {
      return tableData.value.slice((currentPage.value - 1) * pageSize.value, currentPage.value * pageSize.value);
    });

    const searchUserWord = ref("");
    const searchSongWord = ref("");

    watch([searchUserWord, searchSongWord], () => {
      filterTableData();
    });

    function filterTableData() {
      let filteredData = tempDate.value;

      if (searchUserWord.value !== "") {
        filteredData = filteredData.filter((item) =>
          item.username.toLowerCase().includes(searchUserWord.value.toLowerCase())
        );
      }

      if (searchSongWord.value !== "") {
        filteredData = filteredData.filter((item) =>
          item.songName.toLowerCase().includes(searchSongWord.value.toLowerCase())
        );
      }

      tableData.value = filteredData;
      currentPage.value = 1;
    }

    function getData() {
      tableData.value = [];
      tempDate.value = [];
      HttpManager.getAllUserPlayRecord().then((res: any) => {
        if (res.data) {
          tableData.value = res.data;
          tempDate.value = res.data;
        }
      });
    }

    function handleCurrentChange(val) {
      currentPage.value = val;
    }

    function formatDate(dateStr) {
      if (!dateStr) return "";
      const date = new Date(dateStr);
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, "0");
      const day = String(date.getDate()).padStart(2, "0");
      const hours = String(date.getHours()).padStart(2, "0");
      const minutes = String(date.getMinutes()).padStart(2, "0");
      const seconds = String(date.getSeconds()).padStart(2, "0");
      return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
    }

    getData();

    return {
      searchUserWord,
      searchSongWord,
      tableData,
      data,
      pageSize,
      currentPage,
      handleCurrentChange,
      formatDate,
    };
  },
});
</script>

<style scoped>
.container {
  padding: 20px;
}

.handle-box {
  margin-bottom: 20px;
}

.search-input {
  width: 200px;
  margin-right: 10px;
}

.pagination {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}
</style>
