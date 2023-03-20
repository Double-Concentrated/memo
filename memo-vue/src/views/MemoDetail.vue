<template>
<div>
  <Header></Header>
  <div class="memo">
    <h2>{{memo.title}}</h2>
    <el-link icon="el-icon-edit" v-if="editMemo">
      <router-link :to="{name: 'MemoEdit', param: {memoId: memo.id}}">
        编辑
      </router-link>
    </el-link>
    <el-divider></el-divider>
    <div class="markdown-body" v-html="memo.content"></div>
  </div>
</div>
</template>

<script>
import Header from "@/components/Header";
import MarkdownIt from "markdown-it";
import "github-markdown-css"

export default {
  name: "MemoDetail",
  components: {Header},
  data(){
    return{
      memo: {
        id: '',
        title: '',
        content: '',
      },
      editMemo: false,
    }
  },
  created() {
    const memoId = this.$route.params.memoId;
    const _this = this;
    if(memoId) {
      this.$axios.get('/memo/' + memoId).then((res) => {
        const memo = res.data.data;
        _this.memo.id = memo.id;
        _this.memo.title = memo.title;
        let md = new MarkdownIt();
        _this.memo.content = md.render(memo.content);

        // 当前用户展示 编辑 按钮
        _this.editMemo = (memo.userId = _this.$store.getters.getUser.id)
      });
    }
  }
}
</script>

<style scoped>
.memo{
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  width: 100%;
  padding: 20px 20px;
}
</style>