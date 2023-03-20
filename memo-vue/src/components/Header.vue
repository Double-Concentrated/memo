<template>
<div class="content">
  <h3>普普通通的备忘录系统</h3>
  <div class="block">
    <el-avatar :size="50" :src="user.avatar"></el-avatar>
    <div>{{ user.username }}</div>
  </div>

  <div class="action">
    <span><el-link href="/memo/list">主页</el-link></span>
    <el-divider direction="vertical"></el-divider>
    <span><el-link type="success" href="/memo/add">新建备忘录</el-link></span>

    <el-divider direction="vertical"></el-divider>
    <span v-show="!hasLogin"><el-link type="primary" href="/login">登录</el-link></span>

    <span v-show="hasLogin"><el-link type="danger" @click="logout">退出</el-link></span>
  </div>
</div>
</template>

<script>
export default {
  name: "Header",
  data(){
    return {
      user:{
        username: '请先登录',
        avatar: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'
      },
      hasLogin: false
    }
  },
  methods:{
    getUserInfo(){
      try {
        this.user.username = this.$store.getters.getUser.username;
        this.hasLogin = true;
      }catch (e){
        // 所以没登陆直接push到首页并且弹出提示
        // 假装进行了登录拦截
        this.$message({
          message: '没有权限，请先登录',
          type: 'error',
          center: true
        });
        this.$router.push('/login');
      }
    },
    logout() {
      const _this = this;
      _this.$axios.get("/logout").then(res => {
        _this.$store.commit("REMOVE_INFO");

        const msg = res.data.msg;
        
        this.$message({
          message: msg,
          type: 'success',
          center: true
        });

        _this.$router.push("/login");
      })
    }
  },
  created() {
    this.getUserInfo()
  }
}
</script>

<style scoped>
.content{
  max-width: 960px;
  margin: 0 auto;
  text-align: center;
}
.action{
  margin: 10px 0;
}

</style>