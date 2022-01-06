<template>
  <div>
<!--    <div class="background">-->
<!--      <img :src="imgSrc" class="imgSrc"/>-->
<!--    </div>-->
    <div class="login">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="登录" name="first">
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="用户名" prop="name">
              <el-input v-model="ruleForm.name"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="pass">
              <el-input type="password" v-model="ruleForm.pass" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
              <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="注册" name="second">
          <register></register>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Login",
  data(){
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    };
    return {
      //imgSrc: './static/coverage1.jpg',
      activeName: 'first',
      ruleForm: {
        name: '',
        pass: '',
        checkPass: '',
      },
      rules: {
        name: [
          { required: true, message: '请输入您的用户名', trigger: 'blur' }
        ],
        pass: [
          { required: true, validator: validatePass, trigger: 'blur' }
        ]
      },

    };
  },
  methods: {
    //选项卡切换
    handleClick(tab, event) {
    },
    //重置表单
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    //提交表单
    submitForm(formName) {
      this.loading= true;
      var param = {
        "username": this.ruleForm.name,
        "password": this.ruleForm.pass
      }
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post(
              "http://localhost:8081/account/login/", param
              )
              .then(response => {
                if (response.data.code === 200) {
                  //localStorage.setItem('token',response.data.data.token);
                  this.$alert('登录成功！');
                  this.$router.replace({path: '/hdoj/bank'});
                  console.log('登录成功');
                }
                else {
                  this.$alert('登录失败!', '密码或用户名错误!');
                  console.log('登录失败');
                  return false;
                }
              })
        } else {
          console.log('登录失败');
          return false;
        }
      });
    },
  },
}
</script>

<style scoped>
body {
  margin: 0;
  padding: 0;
}
.login {
  width: 400px;
  margin: 0 auto;
}
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 0;

}
.el-tabs__item {
  text-align: center;
  width: 60px;
}

.demo-ruleForm .el-form-item__label{
  color:#3491FA;
  font-weight:bold;
  font-size:15px
}
</style>
