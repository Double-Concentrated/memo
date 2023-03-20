<template>
<div>
  <el-container>
    <el-header>
      <div style="margin: auto; width: 200px;">有空一定找个图标。</div>
    </el-header>
    <el-main>
      <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="80px" class="ruleForm">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="ruleForm.username"></el-input>
        </el-form-item>
        <el-form-item label="密 码" prop="password">
          <el-input type="password" v-model="ruleForm.password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
          <el-button type="success" @click="register()">注册</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-main>
  </el-container>
</div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      ruleForm: {
        username: 'root',
        password: 'root',
      },
      rules: {
        username: [
          {
            require: true, message: "请输入用户名", trigger :"blur"
          },
          {
            min: 3, max: 15, message: "长度在3到15个字符",trigger: "change"
          }
        ],
        password: [
          {
            require: true, message: "请输入密码", trigger :"blur"
          },
        ]
      }
    }
  },
  methods: {
    register(){
      this.$router.push('/register')
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if(valid){
          const _this = this
          this.$axios.post("/login", this.ruleForm).then(res => {
                const code = res.data.code;
                const msg = res.data.msg;
                if (code === 200){
                  const userInfo = res.data.data;
                  this.$message({
                    message: msg,
                    type: 'success',
                    center: true
                  });
                  // 数据共享出去(存入浏览器的存储)
                  _this.$store.commit("SET_USERINFO", userInfo);
                  // 获取
                  // console.log(_this.$store.getters.getUser)
                  _this.$router.push('/memo/list')
                }else if (code === 400){
                  this.$message({
                    message: msg,
                    type: 'error',
                    center: true
                  });
                }
          }
          )
        }else {
          console.log("error");
          return false
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>

<style scoped>
.el-header, .el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  /*background-color: #E9EEF3;*/
  color: #333;
  text-align: center;
  line-height: 160px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
.ruleForm{
  max-width: 500px;
  margin: 0 auto;
}
</style>