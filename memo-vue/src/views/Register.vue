<template>
<div>
  <el-container>
    <el-header>
      <div style="margin: auto; width: 200px;">有空一定找个图标。</div>
    </el-header>
    <el-main>
      <el-form status-icon ref="ruleForm" :model="ruleForm" :rules="rules" label-width="80px">
        <el-form-item label="用户名" style="padding-top: 25px" prop="username">
          <el-input v-model="ruleForm.username" placeholder="请输入用户名"></el-input>
        </el-form-item>

        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="ruleForm.password" placeholder="请输入密码"></el-input>
        </el-form-item>

        <el-form-item label="确认密码" prop="confirm">
          <el-input type="password" v-model="ruleForm.confirm" placeholder="请再次输入密码"></el-input>
        </el-form-item>

        <el-form-item label="邮箱" prop="email">
          <el-input type="email" v-model="ruleForm.email" placeholder="请输入邮箱"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button round type="primary" @click="login()">登录</el-button>
          <el-button round type="success" @click="register('ruleForm')">注册</el-button>
          <el-button round @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-main>
  </el-container>
</div>
</template>

<script>
export default {
name: "Register",
  data() {
    let validatePassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.ruleForm.password !== '') {
          this.$refs.ruleForm.validateField('confirm');
        }
        callback();
      }
    };
    let validateConfirmPassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm.password) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };

    return {
      ruleForm: {
        username: '',
        password: '',
        confirm: '',
        email: '',
      },
      rules: {
        username: [
          { required: true, message: '用户名不能为空', trigger: 'blur' },
          { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, validator: validatePassword, trigger: 'blur' }
        ],
        confirm: [
          { required: true, validator: validateConfirmPassword, trigger: 'blur' }
        ],
        email: [
          {
            required: true, message: '邮箱不能为空', trigger: 'blur'
          },
          {
            type: 'email', message: '请输入正确的邮箱格式', trigger: 'change'
          }
        ]
      }
    }
  },
  methods:{
    login(){
      this.$router.push('/login');
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    register(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid){
          const _this = this;
          this.$axios.post('/register', this.ruleForm).then(res => {
            const code = res.data.code;
            const msg = res.data.msg;

            if (code === 200){
              this.$message({
                message: msg,
                type: 'success',
                center: true
              });
              _this.$router.push('/login');
            }else if (code === 400){
              this.$message({
                message: msg,
                type: 'error',
                center: true
              });
            }
          })
        }else {
          console.log('error');
          return false;
        }
      })
    },
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