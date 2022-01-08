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
          <el-tab-pane label="题目描述" style="text-align: left;">
            <div style="font-size:40px">
              {{this.id}}
              {{this.questionname}}
            </div>
            <el-divider></el-divider>
            {{this.questiondescription}}
            <el-divider></el-divider>
            题目难度：
            <el-tag v-for="tag in tags" :key="tag.name" :type="tag.type">
              {{tag.name}}
            </el-tag>
            <br><br>
            题目标签：
            <el-tag v-for="tag in tags0" :key="tag">
              {{tag}}
            </el-tag>
            <el-divider></el-divider>
            <div v-for="sample in samples">
              样例输入：<span style="color:orangered;width:300px; display:inline-block;">{{sample.input}}</span>
              输出：<span style="color:green; display:inline-block;">{{sample.output}}</span>
            </div>
          </el-tab-pane>
          <el-tab-pane label="题解">
            <Solution />
          </el-tab-pane>
          <el-tab-pane label="提交记录">
            <el-card style="">
              <el-card-content v-if="this.result!=''">
                <!-- 运行成功 -->
                <div v-if="this.result.code == 200">
                  执行结果： <span>通过</span><br>
                  执行用时： <span>{{this.result.data.time}}</span>ms<br>
                  通过测试用例： <span>{{this.result.data.allCases}}</span> / <span>{{this.result.data.allCases}}</span><br>
                </div>
                <!-- 运行失败 -->
                <div v-else>
                  执行结果： <span>未通过</span><br>
                  执行用时： <span>{{this.result.data.time}}</span><br>
                  通过测试用例： <span>{{this.result.data.testCases}}</span> / <span>{{this.result.data.allCases}}</span><br>
                  未通过用例： <span>{{this.result.data.input}}</span><br>
                  错误信息： <span>{{this.result.data.errorMessage}}</span><br>
                </div>
              </el-card-content>
              <el-card-content class="status">
                <el-table :data="tableData" style="width: 100%;left: 15%;" :row-class-name="tableRowClassName">
                  <el-table-column label="id" align="center" prop="id" v-if="false" />
                  <el-table-column prop="state" label="提交结果" width="100">
                  </el-table-column>
                  <el-table-column prop="language" label="语言" width="100">
                  </el-table-column>
                  <el-table-column prop="submitTime" label="提交时间" sortable width="150">
                  </el-table-column>
                </el-table>
              </el-card-content>
            </el-card>
          </el-tab-pane>
        </el-tabs>
        <el-button type="primary" plain v-if="this.current==true" @click="this.dialogNewVisible = true" size="mini"
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
          <div class="grid-content">
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
        <el-col :span="2">
          <div class="grid-content">
            <el-button size="middle" @click="saveCode">保存代码</el-button>
          </div>
        </el-col>
        <el-col :span="2">
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
        <div>标题：
          <el-input v-model="this.form.title" placeholder="请输入标题" maxlength="10" show-word-limit type="text" />
        </div>
        <div>语言：</div>
        <div>
          <el-select v-model="form.language" placeholder="请选择您使用的编程语言">
            <el-option label="Java" value="Java"></el-option>
            <el-option label="C++/C" value="C++/C"></el-option>
          </el-select>
        </div>
        <div>思路：
          <el-input v-model="this.form.content" placeholder="请输入题解的思路" autosize type="textarea" />
        </div>
        <div>代码：
          <el-input v-model="this.form.code" placeholder="请输入题解代码" autosize type="textarea" />
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
      myArgs: {},
      form: {},
      current: '',
      id: '',
      nowPage: '1',
      maxPage: '200',
      tableData: [],
      code: '',
      input: '',
      dialogVisible: false,
      dialogNewVisible: false,
      questionname: '',
      questiondescription: '',
      tags: [],
      tags0: [],
      samples: [],
      result: '',
      isPass: '通过'
    }
  },
  components: {
    Submit,
    Solution,
  },
  methods: {
    next () {
      var id = parseInt(this.id) + 1
      //console.log("router","/hdoj/bank/q/"+id)
      window.location.href = "/hdoj/bank/q/" + id
    },
    before () {
      var id = parseInt(this.id) - 1
      //console.log("router","/hdoj/bank/q/"+id)
      window.location.href = "/hdoj/bank/q/" + id
    },
    random () {
      //这里需要改成题目总数！
      var id = (parseInt(this.id) + (Math.floor(Math.random() * 30 + 1))) % 30
      //console.log("router","/hdoj/bank/q/"+id)
      window.location.href = "/hdoj/bank/q/" + id
    },
    loaddata () {
      var token = JSON.parse(localStorage.token)
      this.$http.post("http://localhost:8081/code/getCodeByUidAndQid?userId=" + token.id + "&questionId=" + this.id)
        .then(response => {
          console.log("response_new", response.data.data)
          let tableData1 = []
          for (let i in response.data.data) {
            tableData1.push(response.data.data[i])
          }
          this.tableData = tableData1
          console.log("table", this.tableData)
        })
        .catch(error => {
          console.log(error)
        })
    },
    changeTabs (tab, e) {
      console.log(tab.index)
      if (tab.index == 1)
        this.current = true
      else
        this.current = false
      console.log(this.current)
    },
    saveCode () {
      if (this.code == null) {
        alert("请写点代码再传好吗")
        return
      }
      this.$http.post("http://localhost:8081/code/saveCode", {
        "userId": JSON.parse(window.localStorage.getItem("token")).id,
        "codeId": '',
        'questionId': this.id,
        "content": this.code,
        "state": 0,
        "submitTime": '',
        "language": ''
      }).then(res => {
        console.log(res)
        alert("保存成功")
      }).catch(err => {
        console.log(err)
        alert("保存失败")
      })
    },
    submit () {
      console.log({
        "code": this.code,
        'questionId': this.id,
      })
      if (this.code == null) {
        alert("请写点代码再传好吗")
        return
      }
      this.$http.post("http://localhost:8081/question/check", {
        "code": this.code,
        'questionId': this.id,
      }).then(res => {
        console.log("aaaaaaa")
        console.log(res)
        document.getElementById("tab-2").click()
        this.result = res.data
        if (this.result.code == 200)
          this.isPass = '通过'
        else
          this.isPass = '未通过'
      }).catch(err => {
        console.log(err)
      })
    },
    newSolution () {
      if (this.form.title == '') {
        alert("标题不能为空")
        return
      }
      if (this.form.language == '') {
        alert("语言不能为空")
        return
      }
      if (this.form.content == '') {
        alert("思路不能为空")
        return
      }
      if (this.form.code == '') {
        alert("代码不能为空")
        return
      }
      this.$http.post("http://localhost:8081/solution/addSolution", {
        "userId": JSON.parse(window.localStorage.getItem("token")).id,
        "code": this.form.code,
        'content': this.form.content,
        'language': this.form.language,
        'title': this.form.title,
        'questionId': this.id,
        'likes': 0,
        'createdTime': '',
      }).then(res => {
        if (res.data.code == 200)
          alert("上传成功")
        else
          alert("由于未知原因，上传失败")
        this.dialogNewVisible = false
      }).catch(err => {
        alert("由于未知原因，上传失败")
        console.log(err)
      })
    },
    runCode () {
      console.log(this.code)
      this.$http.post("http://localhost:8081/question/submitTestCase", {
        "code": this.code,
        'input': this.input,
        'questionId': this.id
      }).then(res => {
        console.log(res.data.data)
        alert(res.data.data)
      }).catch(err => {
        console.log(err)
      })
      this.dialogVisible = false
      //上传code和input
    },
    handleCode (c) {
      this.code = c
    },
    questionquery () {
      var token = JSON.parse(localStorage.token)
      console.log("this.id", this.id)
      this.$http.post("http://localhost:8081/question/getQuestion?questionId=" + this.id)
        .then(res => {
          this.questionname = res.data.data.name
          this.questiondescription = res.data.data.description
          if (res.data.data.difficulty == "简单") this.tags[0] = { name: '简单', type: 'success' }
          else if (res.data.data.difficulty == "中等") this.tags[0] = { name: '中等', type: 'warning' }
          else if (res.data.data.difficulty == "困难") this.tags[0] = { name: '困难', type: 'danger' }
          this.tags0 = res.data.data.labels.split(",")
          this.myArgs = [res.data.data.returnType, res.data.data.argsType, token.id, this.id]
        })
    },
    samplequery () {
      this.$http.post("http://localhost:8081/sample/listSamples?questionId=" + this.id)
        .then(res => {
          this.samples = res.data.data
        })
    }
  },
  mounted () {
    this.id = this.$route.params.id
    this.$refs.nowPage.$el.innerHTML = this.id
    this.$refs.maxPage.$el.innerHTML = JSON.parse(window.localStorage.getItem("maxId"))
    window.localStorage.setItem("currentQuestionId", this.id)
    this.loaddata()
    this.questionquery()
    this.samplequery()
  }
}
</script>

<style>
.el-aside {
  height: auto;
}
.el-main {
  height: 77vh;
  /* background-color: lightgrey; */
}
.el-tag {
  margin-right: 10px;
  border-radius: 50%;
}
</style>
