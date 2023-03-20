<template>
<div class="content">
  <Header></Header>
  <div class="block">
    <el-timeline>
      <el-timeline-item v-for="memo in memos" :timestamp="memo.created" :key="memo.memoId" placement="top">
        <el-card>
          <h4>
            <router-link :to="{name: 'MemoDetail', params: {memoId: memo.memoId}}">
              {{ memo.title }}
            </router-link>
            <div style="float: right">
              <el-button v-if="memo.status === 0" type="success" icon="el-icon-check" round>已完成</el-button>
              <el-button v-if="memo.status === 1" type="warning" icon="el-icon-close" round>未完成</el-button>
            </div>
          </h4>
          <p>{{ memo.description }}</p>
          <span>
          </span>
          <div style="float: right;height: 55px">
              <el-button type="primary" plain round @click="handleModify(memo.memoId)">修改状态</el-button>
            <el-button type="danger" icon="el-icon-delete" plain round @click="handleDelete(memo.memoId)">删除</el-button>
          </div>
        </el-card>
      </el-timeline-item>
    </el-timeline>

    <el-pagination class="page"
        background
        layout="prev, pager, next"
                   :current-page="currentPage"
                   :page-size="pageSize"
                   :total="total"
                   @current-change="page">
    </el-pagination>
  </div>

</div>
</template>

<script>
import Header from "@/components/Header";
export default {
  name: "Memo",
  components: {Header},
  data() {
    return {
      memos: {},
      currentPage: 1,
      total: 0,
      pageSize: 5,
    }
  },
  comments:{Header},
  methods: {
    page(currentPage) {
      const _this = this;
      const id = this.$store.getters.getUser.id;

      _this.$axios.get(`/memo/list/${id}/${currentPage}`).then((res) => {

        _this.memos = res.data.data.records;
        _this.currentPage = res.data.data.current;
        _this.total = res.data.data.total;
        _this.pageSize = res.data.data.size;
      })
      // _this.$axios.post("/memo/list/",{id, currentPage}).then((res) => {
      //   console.log(res)
      //   _this.memos = res.data.data.records;
      //   _this.currentPage = res.data.data.current;
      //   _this.total = res.data.data.total;
      //   _this.pageSize = res.data.data.size;
      // })
    },
    handleModify(id){
      this.$axios.get(`/memo/modify/${id}/`).then( res => {
        const code = res.data.code;
        const msg = res.data.msg;
        if (code === 200){
          this.$notify({
            title: '成功',
            message: msg,
            type: 'success',
          });
          this.page(this.currentPage);
        }
      })
    },
    handleDelete(id){
      const _this = this;
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then( () => {
        _this.$axios.get(`/memo/delete/${id}/`).then((res) => {
          const code = res.data.code;
          const msg = res.data.msg;
          if (code === 200){
            this.$notify({
              title: '成功',
              message: msg,
              type: 'success',
            });
            this.page(this.currentPage);
          }
        })
      })
    }
  },
  created() {
    this.page(1)
  }
}
</script>

<style scoped>
.page{
  margin: 0 auto;
  text-align: center;
}
</style>