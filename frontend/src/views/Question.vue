<template>
  <el-dialog title="输入参数" v-model="dialogVisible">
    <el-input v-model="input" autosize type="textarea" style="width: 70%" placeholder="请输入要测试的参数">
    </el-input>
    <div slot="footer" class="dialog-footer" style="padding-top: 30px">
      <el-button @click="dialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="runCode">确 定</el-button>
    </div>
  </el-dialog>
  <el-container>
    <el-container>
      <el-aside width="50%">
        <el-tabs type="border-card" style="height: 100%" @tab-click="changeTabs">
          <el-tab-pane label="题目描述">
            <div style="font-size:40px">
              {{this.id}}
              {{this.questionname}}
            </div>
            <el-divider></el-divider>
            {{this.questiondescription}}
            <el-divider></el-divider>
            题目难度：
            <el-tag
                v-for="tag in tags"
                :key="tag.name"
                :type="tag.type">
             {{tag.name}}
            </el-tag>
            <br>
            题目标签：
            <el-tag
                v-for="tag in tags0"
                :key="tag">
              {{tag}}
            </el-tag>
            <el-divider></el-divider>
            <div v-for="sample in samples">
              样例输入：<span style="color:orangered;width:300px; display:inline-block; text-align:center;">{{sample.input}}</span>
              输出：<span style="color:green; display:inline-block; text-align:center;">{{sample.output}}</span>
            </div>
          </el-tab-pane>
          <el-tab-pane label="题解">
            <Solution />
          </el-tab-pane>
          <el-tab-pane label="提交记录">
            <div class="status">
              <el-table :data="tableData" style="width: 100%;left: 15%;" :row-class-name="tableRowClassName">
                <el-table-column label="id" align="center" prop="id" v-if="false" />
                <el-table-column prop="result" label="提交结果" width="100">
                </el-table-column>
                <el-table-column prop="runtime" label="执行用时" sortable width="100">
                </el-table-column>
                <el-table-column prop="memory" label="内存消耗" sortable width="100">
                </el-table-column>
                <el-table-column prop="language" label="语言" width="50">
                </el-table-column>
                <el-table-column prop="time" label="提交时间" sortable width="150">
                </el-table-column>
              </el-table>
            </div>
          </el-tab-pane>
        </el-tabs>
        <el-button type="primary" plain v-if="this.current==true" @click="newSolution" size="mini"
          style='position:absolute;right:52%;top:9%;!important;'>上传题解</el-button>
      </el-aside>
      <el-main>
        <Submit :myArgs="this.myArgs" @code="handleCode" />
      </el-main>
    </el-container>
    <el-divider></el-divider>
    <el-footer>
      <el-row :gutter="20">
        <el-col :span="8">
          <div class="grid-content">
            <el-button size="middle" @click="random()">随机一题</el-button>
          </div>
        </el-col>
        <el-col :span="2" :offset="1">
          <div class="grid-content">
            <el-button size="middle" icon="el-icon-arrow-left" @click="before()">上一页</el-button>
          </div>
        </el-col>
        <el-col :span="2">
          <div class="grid-content ">
            <el-button-group>
              <el-button type="text" ref="nowPage"></el-button>
              <el-button type="text">/</el-button>
              <el-button type="text" ref="maxPage"></el-button>
            </el-button-group>
          </div>
        </el-col>
        <el-col :span="2">
          <div class="grid-content">
            <el-button size="middle" @click="next()">下一页<i class="el-icon-arrow-right el-icon--right"></i></el-button>
          </div>
        </el-col>
        <el-col :span="2" :offset="2">
          <div class="grid-content">
            <el-button size="middle" @click="dialogVisible=true">执行代码</el-button>
          </div>
        </el-col>
        <el-col :span="2" :offset="2">
          <div class="grid-content">
            <el-button size="middle" @click="submit">提交</el-button>
          </div>
        </el-col>
      </el-row>
    </el-footer>
  </el-container>

  <el-dialog title="上传题解" v-model="dialogNewVisible">
    <el-card style="width:650px;margin:auto;">
      <el-card-content style="text-align: left">
        <div>标题：<el-input
            v-model="this.form.title"
            placeholder="请输入标题"
            maxlength="10"
            show-word-limit
            type="text"
        />
        </div>
        <div>语言：</div><div><el-select v-model="form.language" placeholder="请选择您使用的编程语言">
          <el-option label="Java" value="Java"></el-option>
          <el-option label="C++/C" value="C++/C"></el-option>
        </el-select>
        </div>
        <div>思路：<el-input
            v-model="this.form.content"
            placeholder="请输入题解的思路"
            autosize
            type="textarea"
        />
        </div>
        <div>代码：<el-input
            v-model="this.form.code"
            placeholder="请输入题解代码"
            autosize
            type="textarea"
        />
        </div>
      </el-card-content>
    </el-card>
    <div slot="footer" class="dialog-footer" style="padding-top: 40px">
      <el-button @click="dialogNewVisible = false">取 消</el-button>
      <el-button type="primary" @click="newSolution()">确 定 上 传</el-button>
    </div>
  </el-dialog>
</template>

<script>
import Solution from "../views/Solution"
import Submit from "../views/Submit.vue"
export default {
  data () {
    return {
      myArgs:'',
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
      questionname:'',
      questiondescription:'',
      tags:[],
      tags0:[],
      samples:[]
    };
  },
  components: {
    Submit,
    Solution,
  },
  methods: {
    next(){
      //this.id=this.id+1;
      var id=this.id+1;
      console.log("router","/hdoj/bank/q/"+id)
      this.$router.push("/hdoj/bank/q/"+id);
    },
    before(){
      if(this.id>0) this.$router.push("/hdoj/bank/q/"+this.id-1);
    },
    random () {
      //这里需要改成题目总数！
      this.$router.push("/hdoj/bank/q/" + (Math.floor(Math.random() * 3 + 1)).toString())
    },
    loaddata(){
      var token=JSON.parse(localStorage.token);
      this.$http.post("http://localhost:8081/code/getCodeByUidAndQid?userId="+token.id+"&questionId="+this.id)
          .then(response => {
            let tableData1 = [];
            /**
             * 这里需要将拿到的对象转为数组，进行赋值，这样才不会类型错误
             */
            for(let i in response.data.data.XXX){
              tableData1.push(response.data.data.XXX[i]);
            }
            this.tableData = tableData1;
            //console.log(response);
          })
          .catch(error => {
            console.log(error);
          });
    },
    changeTabs(tab,e){
      console.log(tab.index);
      if(tab.index==1)
        this.current=true;
      else
        this.current = false
      console.log(this.current)
    },
    submit () {
      console.log({
        "code": this.code,
        'questionId': this.id,
      })
      if (this.code == null)
        alert("请写点代码再传好吗")
      this.$http.post("http://localhost:8081/question/check", {
        "code": this.code,
        'questionId': this.id,
      }).then(res => {
        console.log(res)
        alert(res.data.data.result)
      }).catch(err => {
        console.log(err)
      })
    },
    newSolution () {
      this.dialogNewVisible = true
    },
    runCode () {
      console.log(this.code)
      if (this.code == null)
        alert("请写点代码再传好吗")
      this.$http.post("http://localhost:8081/question/submitTestCase", {
        "code": this.code,
        'input': this.input
      }).then(res => {
        console.log(res)
        alert(res.data.data.result)
      }).catch(err => {
        console.log(err)
      })
      this.dialogVisible = false
      //上传code和input
    },
    handleCode(c){
      this.code=c;
    },
    questionquery() {
      console.log("this.id", this.id)
      this.$http.post("http://localhost:8081/question/getQuestion?questionId=" + this.id)
          .then(res => {
            this.questionname=res.data.data.name;
            this.questiondescription=res.data.data.description;
            if(res.data.data.difficulty=="简单") this.tags[0]={name:'简单',type:'success'};
            else if(res.data.data.difficulty=="中等") this.tags[0]={name:'中等',type:'warning'};
            else if(res.data.data.difficulty=="困难") this.tags[0]={name:'困难',type:'danger'};
            this.tags0=res.data.data.labels.split(",");
          })
    },
    samplequery(){
      this.$http.post("http://localhost:8081/sample/listSamples?questionId=" + this.id)
          .then(res => {
            this.samples=res.data.data
          })
    }
  },
  mounted() {
    this.id=this.$route.params.id;
    this.$refs.nowPage.$el.innerHTML=this.id;
    this.$refs.maxPage.$el.innerHTML=200;
    window.localStorage.setItem("currentQuestionId",this.id);
    // this.loaddata();
    this.questionquery();
    this.samplequery();
  }
}
</script>

<style>
.el-aside {
  height: 77vh;
}
.el-main {
  height: 77vh;
}
</style>
