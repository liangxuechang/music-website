<template>
  <div class="container">
    <div class="handle-box">
      <el-input v-model="searchUsername" placeholder="筛选用户名" style="width: 150px; margin-right: 10px;"></el-input>
      <el-input v-model="searchSongName" placeholder="筛选歌曲名称" style="width: 150px; margin-right: 10px;"></el-input>
      <el-button @click="searchData">搜索</el-button>
      <el-button @click="resetData">重置</el-button>
    </div>

    <el-table height="550px" border size="small" :data="data">
      <el-table-column label="ID" prop="id" width="60" align="center"></el-table-column>
      <el-table-column label="用户名" prop="username" width="120" align="center"></el-table-column>
      <el-table-column label="歌曲名称" prop="song_name" width="150" align="center"></el-table-column>
      <el-table-column label="歌手" prop="singer_name" width="120" align="center"></el-table-column>
      <el-table-column label="听歌时间" width="180" align="center">
        <template v-slot="scope">
          <div>{{ formatTime(scope.row.listen_time) }}</div>
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
import { defineComponent, ref, computed } from "vue";
import { HttpManager } from "@/api";

export default defineComponent({
  setup() {
    const tableData = ref([]);
    const tempDate = ref([]);
    const pageSize = ref(10);
    const currentPage = ref(1);
    const searchUsername = ref("");
    const searchSongName = ref("");

    const data = computed(() => {
      return tableData.value.slice((currentPage.value - 1) * pageSize.value, currentPage.value * pageSize.value);
    });

    function formatTime(time: string) {
      if (!time) return "";
      const date = new Date(time);
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, "0");
      const day = String(date.getDate()).padStart(2, "0");
      const hours = String(date.getHours()).padStart(2, "0");
      const minutes = String(date.getMinutes()).padStart(2, "0");
      const seconds = String(date.getSeconds()).padStart(2, "0");
      return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
    }

    async function getData() {
      const result = (await HttpManager.getListenRecordList("", "")) as ResponseBody;
      tableData.value = result.data || [];
      tempDate.value = result.data || [];
      currentPage.value = 1;
    }

    async function searchData() {
      const result = (await HttpManager.getListenRecordList(searchUsername.value, searchSongName.value)) as ResponseBody;
      tableData.value = result.data || [];
      currentPage.value = 1;
    }

    function resetData() {
      searchUsername.value = "";
      searchSongName.value = "";
      getData();
    }

    function handleCurrentChange(val) {
      currentPage.value = val;
    }

    getData();

    return {
      searchUsername,
      searchSongName,
      data,
      tableData,
      pageSize,
      currentPage,
      handleCurrentChange,
      formatTime,
      searchData,
      resetData,
    };
  },
});
</script>

<style scoped>
.handle-box {
  margin-bottom: 20px;
}
.pagination {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}
</style>
