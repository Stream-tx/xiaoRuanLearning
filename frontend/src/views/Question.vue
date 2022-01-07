<template>
  <el-dialog title="输入参数" v-model="dialogVisible">
    <el-input v-model="input"
              autosize
              type="textarea"
              style="width: 70%"
              placeholder="请输入要测试的参数">
    </el-input>
    <div slot="footer" class="dialog-footer" style="padding-top: 30px">
      <el-button @click="dialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="runCode">确 定</el-button>
    </div>
  </el-dialog>
  <el-container>
    <el-container>
      <el-aside width="50%" >
        <el-tabs type="border-card" style="height: 100%" @tab-click="changeTabs">
          <el-tab-pane label="题目描述">
            题目名字
            <el-divider></el-divider>
            题目内容
          </el-tab-pane>
          <el-tab-pane label="题解">
            <Solution/>
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
        <el-button type="primary" plain v-if="this.current==true" @click="newSolution" size="mini" style='position:absolute;right:52%;top:9%;!important;'>上传题解</el-button>
      </el-aside>
      <el-main>
        <Submit @code="handleCode"/>
      </el-main>
    </el-container>
    <el-divider></el-divider>
    <el-footer>
      <el-row :gutter="20">
        <el-col :span="8"><div class="grid-content">
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
          <el-button size="middle" @click="dialogVisible=true">执行代码</el-button>
        </div>
        </el-col>
        <el-col :span="2" :offset="2"><div class="grid-content">
          <el-button size="middle" @click="submit">提交</el-button>
        </div>
        </el-col>
      </el-row>
    </el-footer>
  </el-container>


  <el-dialog title="上传题解" v-model="dialogNewVisible">
    <el-form :model="form" :label-position="right">
      <el-form-item label="标题:" style="padding-left: 20%">
        <el-input v-model="form.title" autocomplete="off" style="width: 50%;alignment: left"></el-input>
      </el-form-item>
      <el-form-item label="语言:" style="padding-left: 20%">
        <el-select v-model="form.language" placeholder="请选择编程语言">
          <el-option label="Java" value="Java"></el-option>
          <el-option label="C++/C" value="C++/C"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="思路:" style="padding-left: 20%">
        <el-input v-model="form.content" autocomplete="off" style="width: 50%;alignment: left"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogNewVisible = false">取 消</el-button>
      <el-button type="primary" @click="newSolution()">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
import Solution from "../views/Solution"
import Submit from "../views/Submit.vue"
export default {
  data() {
    return {
      form:{},
      current:'',
      id:'',
      nowPage:'1',
      maxPage:'200',
      tableData:[],
      code:'',
      input:'',
      dialogVisible:false,
      dialogNewVisible:false,
    };
  },
  components:{
    Submit,
    Solution,
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
    changeTabs(tab,e){
      console.log(tab.index);
      if(tab.index==1)
        this.current=true;
      else
        this.current=false;
      console.log(this.current);
    },
    submit(){
      console.log(this.code);
      if(this.code==null)
        alert("请写点代码再传好吗");
      this.$http.post("http://localhost:8081/questions/check",{
        params:{
          "code": this.code,
          'questionId': this.id,
        }
      }).then(res =>{
        console.log(res);
        alert(res.data.data.result);
      }).catch(err => {
        console.log(err);
      })
    },
    newSolution(){
      this.dialogNewVisible=true;
    },
    runCode(){
      console.log(this.code);
      if(this.code==null)
        alert("请写点代码再传好吗");
      this.$http.post("http://localhost:8081/questions/check",{
        params:{
          "code": this.code,
          'input': this.input,
        }
      }).then(res =>{
        console.log(res);
        alert(res.data.data.result);
      }).catch(err => {
        console.log(err);
      })
      this.dialogVisible=false;
      //上传code和input
    },
    handleCode(c){
      this.code=c;
    }
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