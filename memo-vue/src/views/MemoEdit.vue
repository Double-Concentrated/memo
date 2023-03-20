<template>
<div>
  <Header></Header>
  <div class="content">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="标题" prop="title">
        <el-input v-model="ruleForm.title"></el-input>
      </el-form-item>
      <el-form-item label="摘要" prop="description">
        <el-input type="textarea" v-model="ruleForm.description"></el-input>
      </el-form-item>
      <el-form-item label="内容" prop="content">
        <mavon-editor v-model="ruleForm.content"></mavon-editor>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</div>
</template>

<script>
import Header from "@/components/Header";
export default {
  name: "MemoEdit",
  components: {Header},
  data() {
    return {
      ruleForm: {
        memoId: '',
        userId: '',
        title: '',
        description: '',
        content: '',
      },
      rules: {
        title: [
          { required: true, message: '请输入标题', trigger: 'blur' },
          { min: 2, max: 50, message: '长度在 2 到 50 个字符', trigger: 'blur' }
        ],
        description: [
          { required: true, message: '请输入摘要', trigger: 'blur' }
        ],
        content: [
          { required: true, message: '请选择内容', trigger: 'blur' }
        ],
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const _this = this;
          _this.$axios.post('/memo/edit', this.ruleForm).then( res => {
            const code = res.data.code;
            const msg = res.data.msg;
            if (code === 200){
              this.$notify({
                title: '成功',
                message: msg,
                type: 'success',
              });
              _this.$router.push("/memo/list")
            }
          } )
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  },
  created() {
    const memoId = this.$route.params.memoId;
    const _this = this;
    _this.ruleForm.userId = this.$store.getters.getUser.id;
    if(memoId) {
      this.$axios.get('/memo/' + memoId).then((res) => {
        const memo = res.data.data;
        _this.ruleForm.memoId = memo.memoId;
        _this.ruleForm.title = memo.title;
        _this.ruleForm.description = memo.description;
        _this.ruleForm.content = memo.content;
      });
    }
  },
}
</script>

<style scoped>

</style>