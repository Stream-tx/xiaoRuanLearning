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
          <el-tab-pane label="题解">

          </el-tab-pane>
          <el-tab-pane label="提交记录">
            <div class="status" >
              <el-table
                  :data="tableData"
                  style="width: 100%;left: 15%;"
                  :row-class-name="tableRowClassName"
              >
                <el-table-column label="id" align="center" prop="id" v-if="false" />
                <el-table-column
                    prop="result"
                    label="提交结果"
                    width="100">
                </el-table-column>
                <el-table-column
                    prop="runtime"
                    label="执行用时"
                    sortable
                    width="100">
                </el-table-column>
                <el-table-column
                    prop="memory"
                    label="内存消耗"
                    sortable
                    width="100">
                </el-table-column>
                <el-table-column
                    prop="language"
                    label="语言"
                    width="50">
                </el-table-column>
                <el-table-column
                    prop="time"
                    label="提交时间"
                    sortable
                    width="150">
                </el-table-column>
              </el-table>
            </div>
          </el-tab-pane>
        </el-tabs>
      </el-aside>
      <el-main>
        <Submit/>
      </el-main>
    </el-container>
    <el-divider></el-divider>
    <el-footer>
      <el-row :gutter="20">
        <el-col :span="2" :offset="2"><div class="grid-content">
          <el-button size="middle" @click="random()">随机一题</el-button>
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
      maxPage:'200',
      tableData:[]
    };
  },
  components:{
    Submit,
  },
  methods: {
    random(){
      //这里需要改成题目总数！
      this.$router.push("/hdoj/bank/q/"+(Math.floor(Math.random() * 3 + 1)).toString());
    },
    // loaddata(){
    //   this.$axios
    //       .get("")
    //       .then(response => {
    //         let tableData1 = [];
    //         /**
    //          * 这里需要将拿到的对象转为数组，进行赋值，这样才不会类型错误
    //          */
    //         for(let i in response.data.data.XXX){
    //           tableData1.push(response.data.data.XXX[i]);
    //         }
    //         this.tableData = tableData1;
    //         //console.log(response);
    //       })
    //       .catch(error => {
    //         console.log(error);
    //       });
    // },
  },
  mounted() {
    this.id=this.$route.params.id;
    this.$refs.nowPage.$el.innerHTML=this.id;
    this.$refs.maxPage.$el.innerHTML=200;
    // this.loaddata();
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
