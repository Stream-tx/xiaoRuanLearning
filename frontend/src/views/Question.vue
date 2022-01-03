<template>
  <el-container>
    <el-container>
      <el-aside width="50%" >
        <el-tabs type="border-card" style="height: 100%">
          <el-tab-pane label="题目描述">
            题目描述
            <el-divider></el-divider>
            题目内容
          </el-tab-pane>
          <el-tab-pane label="题解">题解</el-tab-pane>
          <el-tab-pane label="提交记录">提交记录</el-tab-pane>
        </el-tabs>
      </el-aside>
      <el-main>
        <Submit/>
      </el-main>
    </el-container>
    <el-divider></el-divider>
    <el-footer>
      <el-row :gutter="20">
        <el-col :span="4"><div class="grid-content">
          <el-button size="middle">题目列表</el-button>
        </div></el-col>
        <el-col :span="2" :offset="2"><div class="grid-content">
          <el-button size="middle">随机一题</el-button>
        </div></el-col>
        <el-col :span="2" :offset="1"><div class="grid-content">
          <el-button size="middle" icon="el-icon-arrow-left">上一页</el-button>
        </div></el-col>
        <el-col :span="2"><div class="grid-content ">
          <el-button-group>
            <el-button type="text" ref="nowPage"></el-button>
            <el-button type="text">/</el-button>
            <el-button type="text" ref="maxPage"></el-button>
          </el-button-group>
        </div>
        </el-col>
        <el-col :span="2"><div class="grid-content">
          <el-button size="middle">下一页<i class="el-icon-arrow-right el-icon--right"></i></el-button>
        </div></el-col>
        <el-col :span="2" :offset="2"><div class="grid-content">
          <el-button size="middle">执行代码</el-button>
        </div>
        </el-col>
        <el-col :span="2" :offset="2"><div class="grid-content">
          <el-button size="middle">提交</el-button>
        </div>
        </el-col>
      </el-row>
    </el-footer>
  </el-container>
</template>

<script>
import Submit from "../views/Submit.vue"
export default {
  data() {
    return {
      id:'',
      nowPage:'1',
      maxPage:'200'
    };
  },
  components:{
    Submit,
  },
  methods: {
  },
  mounted() {
    this.id=this.$route.params.id;
    this.$refs.nowPage.$el.innerHTML=this.id;
    this.$refs.maxPage.$el.innerHTML=200;
  }
}
</script>

<style>

.el-aside {
  height: 77vh;
}
.el-main{
  height: 77vh;
}
</style>