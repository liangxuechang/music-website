<template>
  <div class="container">
    <div class="handle-box">
      <span style="margin-right: 10px">用户名：</span>
      <el-input v-model="username" placeholder="请输入用户名" style="width: 200px; margin-right: 20px"></el-input>
      <span style="margin-right: 10px">歌曲名：</span>
      <el-input v-model="songName" placeholder="请输入歌曲名" style="width: 200px; margin-right: 20px"></el-input>
      <el-button type="primary" @click="search">搜索</el-button>
      <el-button @click="reset">重置</el-button>
    </div>

    <el-table height="550px" border size="small" :data="data">
      <el-table-column label="ID" prop="id" width="60" align="center"></el-table-column>
      <el-table-column label="用户ID" prop="userId" width="80" align="center"></el-table-column>
      <el-table-column label="歌曲ID" prop="songId" width="80" align="center"></el-table-column>
      <el-table-column label="歌曲名称" prop="songName" align="center" show-overflow-tooltip></el-table-column>
      <el-table-column label="听歌时间" prop="createTime" width="180" align="center">
        <template v-slot="scope">
          <div>{{ formatDateTime(scope.row.createTime) }}</div>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      class="pagination"
      background
      layout="total, prev, pager, next, jumper"
      :current-page="currentPage"
      :page-size="pageSize"
      :total="total"
      @current-change="handleCurrentChange"
    >
    </el-pagination>
  </div>
</template>

<script lang="ts">
import { defineComponent, getCurrentInstance, ref, reactive, onMounted, computed } from "vue";
import { HttpManager } from "@/api";

export default defineComponent({
  setup() {
    const tableData = ref([]); // 表格数据
    const pageSize = ref(10); // 每页条数
    const currentPage = ref(1); // 当前页
    const total = ref(0); // 总条数
    const username = ref(""); // 用户名搜索
    const songName = ref(""); // 歌曲名搜索

    // 计算当前表格中的数据（后端已分页，直接使用）
    const data = computed(() => {
      return tableData.value;
    });

    // 格式化日期时间
    function formatDateTime(dateTime) {
      if (!dateTime) return "-";
      const date = new Date(dateTime);
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, "0");
      const day = String(date.getDate()).padStart(2, "0");
      const hour = String(date.getHours()).padStart(2, "0");
      const minute = String(date.getMinutes()).padStart(2, "0");
      const second = String(date.getSeconds()).padStart(2, "0");
      return `${year}-${month}-${day} ${hour}:${minute}:${second}`;
    }

    // 获取听歌记录列表
    function getPlayRecordList() {
      HttpManager.getPlayRecordPage(
        currentPage.value,
        pageSize.value,
        username.value.trim(),
        songName.value.trim()
      ).then((res: any) => {
        // 后端返回的成功code是200
        if (res.code === 200 && res.success) {
          if (res.data && res.data.records) {
            tableData.value = res.data.records;
            total.value = res.data.total;
          } else {
            tableData.value = [];
            total.value = 0;
          }
        }
      });
    }

    // 搜索
    function search() {
      currentPage.value = 1;
      getPlayRecordList();
    }

    // 重置
    function reset() {
      username.value = "";
      songName.value = "";
      currentPage.value = 1;
      getPlayRecordList();
    }

    // 切换页码
    function handleCurrentChange(val: number) {
      currentPage.value = val;
      getPlayRecordList();
    }

    onMounted(() => {
      getPlayRecordList();
    });

    return {
      data,
      tableData,
      pageSize,
      currentPage,
      total,
      username,
      songName,
      search,
      reset,
      handleCurrentChange,
      formatDateTime,
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
  display: flex;
  align-items: center;
}
.pagination {
  margin-top: 20px;
  text-align: right;
}
</style>
