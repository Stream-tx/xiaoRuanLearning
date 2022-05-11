<template>
  <div class="solution">
    <el-dialog :title="solutions[currentIndex].title" v-model="dialogSolutionVisible">
      <el-row>
        <el-col :span="8">
          <div class="grid-content ">
            作者:{{solutions[currentIndex].userName}}
          </div>
        </el-col>
        <el-col :span="8">
          <div class="grid-content">
            语言:{{solutions[currentIndex].language}}
          </div>
        </el-col>
        <el-col :span="8">
          <div class="grid-content ">
            时间:{{solutions[currentIndex].time}}
          </div>
        </el-col>
      </el-row>
      <el-collapse>
        <el-collapse-item title="思路" name="1">
          <div style="text-align: left;white-space:pre-line">{{solutions[currentIndex].content}}</div>
        </el-collapse-item>
        <el-collapse-item title="代码" name="2">
          <pre style="text-align: left">{{solutions[currentIndex].code}}</pre>
        </el-collapse-item>
        <el-collapse-item title="评论" name="3">
          <el-card style="background-color: #444444" class="box-card" v-for="item in comments" :key="item.id"
            >
            <h5 style="text-align: left;font-size: larger;color:floralwhite;margin:10px 18px;">{{item.userName}}</h5>
            <div style="color:floralwhite;font-size: larger;text-align: left;margin-left:18px;margin-bottom:10px;">
              <span>{{item.content}}</span>
            </div>
            <el-row :gutter="20">
              <el-col :span="4">
                <div class="grid-content" style="color:floralwhite">
                  <Star style="height: 15px;width: 15px" v-if="!item.isThumbed" @click="thumbComment(item)" />
                  <StarFilled style="height: 15px;width: 15px" v-if="item.isThumbed" />
                  点赞数:{{item.likes}}
                </div>
              </el-col>
              <el-col :span="17">
                <div class="grid-content" style="color:floralwhite">
                  时间:{{item.time}}
                </div>
              </el-col>
              <el-col :span="3">
                <el-button  type="info" size="small" v-if="item.userId == this.userId" @click="deleteComment(item.id)">删除
                </el-button>
              </el-col>
            </el-row>
          </el-card>
        </el-collapse-item>
      </el-collapse>
      <div slot=" footer" class="dialog-footer">
        <el-button type="primary" @click="uploadComment" style="margin-top: 20px">发布评论</el-button>
        <el-button type="primary" @click="dialogSolutionVisible = false" style="margin-top: 20px">关闭
        </el-button>
        <el-button v-if="this.clickUserId == this.userId" type="danger" @click="deleteSolution"
          style="margin-top: 20px">删除</el-button>
      </div>
    </el-dialog>
    <div class="solutionHeader">

    </div>
    <el-button type="primary" plain size="mini" @click="this.dialogNewVisible = true"
               style='position:absolute;right:15px;z-index: 2000 !important;top:2px;'>上传题解</el-button>
    <el-card style="margin-top:20px;background-color: #f6f5f5" class="box-card" v-for="item in solutions" :key="item.id"
             @click="view(item)">
      <div slot="header" class="clearfix">
        <el-row :gutter="20" style="margin-top: 7px;margin-bottom: 15px;">
          <el-col :span="1">
            <img :src="item.headImgSrc" width="28" height="28"  style="border-radius:50%;margin-right:1px">
          </el-col>
          <el-col :span="8">
            <p style="margin:1px;font-size: larger;text-align: left">{{item.userName}}</p>
          </el-col>
          <el-col :span="7">
            <p style="margin:1px;font-size: larger;font-weight:bold;text-align: left">{{item.title}}</p>
          </el-col>

        </el-row>


<!--        <img :src="headImgSrc" width="30" height="30" style="border-radius:50%;margin-top:5px;margin-left:5px">-->
<!--        <h5 style="margin:6px;font-size: larger;text-align: left">[{{item.userName}}]:{{item.title}}</h5>-->
      </div>
      <el-row :gutter="20">
        <el-col :span="4">
          <div class="grid-content ">
            <Star style="height: 15px;width: 15px" v-if="!item.isThumbed" @click="thumbSolution(item)" />
            <StarFilled style="height: 15px;width: 15px" v-if="item.isThumbed" />
            点赞数:{{item.likes}}
          </div>
        </el-col>
        <el-col :span="8">
          <div class="grid-content ">
            语言:{{item.language}}
          </div>
        </el-col>
        <el-col :span="12">
          <div class="grid-content ">
            时间:{{item.time}}
          </div>
        </el-col>
      </el-row>
    </el-card>
  </div>

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
import { Star, StarFilled } from '@element-plus/icons-vue'
import { ElMessageBox, ElMessage } from 'element-plus'
export default {
  components: {
    Star,
    StarFilled,
    ElMessageBox,
    ElMessage
  },
  data() {
    return {
      //headImgSrc: 'https://tva2.sinaimg.cn/large/9bd9b167ly1fzjxyujrpaj20b40b40ta.jpg',

      dialogSolutionVisible: false,
      dialogNewVisible: false,

      form: {},

      comments: [{
        userId: '1',
        userName: '--',
        id: '1',
        sId: '1',
        content: '暂无数据',
        likes: '0',
        time: '--',
        isThumbed: false
      }],
      solutions: [{
        id: '0',
        userName: '--',
        content: '暂无数据',
        userid: '1',
        qid: '1',
        time: '--',
        language: '--',
        title: '暂无题解',
        code: '--',
        likes: '0',
        isThumbed: false,
        headImgSrc: ''
      }],
      userId: '0',
      clickUserId: '0',
      currentIndex: '0'
    }
  },
  methods: {
    updateHeadImg (currentID) {
      let avatar = ''
      if (currentID % 7 == 1) {
        avatar = 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fup.enterdesk.com%2F2021%2Fedpic%2F0b%2F17%2F04%2F0b1704a9741f4e7ddd07939877dd3590_1.jpg&refer=http%3A%2F%2Fup.enterdesk.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1644142234&t=4f1fa0c40c07873cb747d04474178241'
      }
      else if ( currentID % 7 == 2) {
        avatar = 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fup.enterdesk.com%2Fedpic%2F92%2F4a%2F72%2F924a726144487f372ac97057dbb01e81.jpg&refer=http%3A%2F%2Fup.enterdesk.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1644142269&t=289155d2b0f39b19ed108a8b0eb24f8d'
      } else if ( currentID % 7 == 3) {
        avatar = 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201704%2F05%2F20170405213655_uSEiT.jpeg&refer=http%3A%2F%2Fb-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1644142269&t=0f75b074995a35b8c4e818116dd79fa8'
      }else if( currentID % 7 == 4){
        avatar = 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi-1.lanrentuku.com%2F2020%2F11%2F5%2Fdef6ed04-6d34-402e-99c8-366266f627dd.png%3FimageView2%2F2%2Fw%2F500&refer=http%3A%2F%2Fi-1.lanrentuku.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654488342&t=1d16040e17cf13f9d1af6d1be128cbb8'
      }else if( currentID % 7 == 5){
        avatar = 'https://img1.baidu.com/it/u=2839692202,2605797378&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=498'
      }else if( currentID % 7 == 6){
        avatar = 'https://img2.baidu.com/it/u=331441483,3335069502&fm=253&fmt=auto&app=138&f=PNG?w=500&h=497'
      }else if( currentID % 7 == 0){
        avatar= 'https://img2.baidu.com/it/u=84120346,2663335055&fm=253&fmt=auto&app=138&f=JPEG?w=400&h=400'
      }
      return avatar
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
      this.$http.post("http://localhost:8082/api/oj/solution/addSolution", {
        "userId": JSON.parse(window.localStorage.getItem("token")).id,
        "code": this.form.code,
        'content': this.form.content,
        'language': this.form.language,
        'title': this.form.title,
        'questionId':  window.localStorage.getItem("currentQuestionId"),
        'likes': 0,
        'createdTime': '',
      },{
        headers:{"satoken":localStorage.getItem("satoken")}
      }).then(res => {
        if (res.data.code == 200)
          alert("上传成功")
        else
          alert("由于未知原因，上传失败")
        this.dialogNewVisible = false

        this.reFresh()
      }).catch(err => {
        alert("由于未知原因，上传失败")
        console.log(err)
      })
    },
    uploadComment() {
      ElMessageBox.prompt('请输入评论内容', 'Comment', {
        confirmButtonText: '上传',
        cancelButtonText: '取消',
        inputErrorMessage: '输入不能为空',
        inputValidator: (value) => {       // 点击按钮时，对文本框里面的值进行验证
          if (!value) {
            return '输入不能为空'
          }
        }
      })
          .then(({value}) => {
            this.$http.post("http://localhost:8082/api/community/comment/addComment", {
              "userId": JSON.parse(window.localStorage.getItem("token")).id,
              "solutionId": this.solutions[this.currentIndex].id,
              'commentId': '',
              'content': value,
              'likes': 0,
              'commentTime': '',
            },{
              headers:{"satoken":localStorage.getItem("satoken")}
            }).then(res => {
              if (res.data.code == 200)
                ElMessage({
                  type: 'success',
                  message: `上传成功`,
                })
              else
                ElMessage({
                  type: 'fail',
                  message: `内容包含敏感词，上传失败`,
                })
              this.dialogNewVisible = false
              this.reFreshComment()
            }).catch(err => {
              alert("由于未知原因，上传失败")
              console.log(err)
            })

          })
          .catch(() => {
            ElMessage({
              type: 'info',
              message: '取消操作',
            })
          })
    },
    thumbSolution(row) {
      row.isThumbed = true
      row.likes++
      this.$http.post("http://localhost:8082/api/oj/solution/likesIncrement?solutionId="
          + row.id,{
        headers:{"satoken":localStorage.getItem("satoken")}
      })
          .then(res => {
            console.log(res)
          })
          .catch(err => {
            console.log(err)
          })
    },
    thumbComment(row) {
      row.isThumbed = true
      row.likes++
      this.$http.post("http://localhost:8082/api/community/comment/likesIncrement?commentId="
          + row.id,{
        headers:{"satoken":localStorage.getItem("satoken")}
      })
          .then(res => {
            console.log(res)
          })
          .catch(err => {
            console.log(err)
          })
    },
    reFreshComment(){
      this.$http.post("http://localhost:8082/api/community/comment/listComments?solutionId=" + this.solutions[this.currentIndex].id,{
        headers:{"satoken":localStorage.getItem("satoken")}
      })
          .then(res => {
            console.log(res)
            this.comments.splice(0, this.comments.length)
            for (let i = 0; i < res.data.data.length; i++) {
              this.comments.push({
                'id': res.data.data[i].commentId,
                'userName': '',
                'content': res.data.data[i].content,
                'userId': res.data.data[i].userId,
                'sid': res.data.data[i].solutionId,
                'time': res.data.data[i].commentTime,
                'likes': res.data.data[i].likes,
                'isThumbed': false
              })
              this.$http.post("http://localhost:8082/api/account/user/getUserInfo?userId="
                  + this.comments[i].userId,{
                headers:{"satoken":localStorage.getItem("satoken")}
              })
                  .then(res => {
                    this.comments[i].userName = res.data.data
                  }).catch(err => {
                console.log(err)
              })
            }
          }).catch(err => {
        console.log(err)
      })
    },
    view(row) {
      this.userId = JSON.parse(window.localStorage.getItem("token")).id
      this.dialogSolutionVisible = true
      for (let i = 0; i < this.solutions.length; i++) {
        if (row.id == this.solutions[i].id) {
          this.currentIndex = i
          break
        }
      }
      this.$http.post("http://localhost:8082/api/oj/solution/getSolutionById?solutionId=" + this.solutions[this.currentIndex].id,{
        headers:{"satoken":localStorage.getItem("satoken")}
      })
          .then(res => {
            this.clickUserId = res.data.data
          }).catch(err => {
        console.log(err)
      })
      this.reFreshComment()
      // this.$http.post("http://localhost:8082/api/community/comment/listComments?solutionId=" + this.solutions[this.currentIndex].id)
      //     .then(res => {
      //       console.log(res)
      //       this.comments.splice(0, 1)
      //       for (let i = 0; i < res.data.data.length; i++) {
      //         this.comments.push({
      //           'id': res.data.data[i].commentId,
      //           'userName': '',
      //           'content': res.data.data[i].content,
      //           'userId': res.data.data[i].userId,
      //           'sid': res.data.data[i].solutionId,
      //           'time': res.data.data[i].commentTime,
      //           'likes': res.data.data[i].likes,
      //           'isThumbed': false
      //         })
      //         this.$http.post("http://localhost:8082/api/account/user/getUserInfo?userId="
      //             + this.comments[i].userId)
      //             .then(res => {
      //               this.comments[i].userName = res.data.data
      //             }).catch(err => {
      //           console.log(err)
      //         })
      //       }
      //     }).catch(err => {
      //   console.log(err)
      // })
    },
    deleteSolution() {
      this.$http.post("http://localhost:8082/api/oj/solution/deleteSolution?solutionId=" + this.solutions[this.currentIndex].id,{
        headers:{"satoken":localStorage.getItem("satoken")}
      })
          .then(res => {
            this.reFresh()

          }).catch(err => {
        console.log(err)
      })
    },
    deleteComment(commentId) {
      console.log(commentId)
      this.$http.post("http://localhost:8082/api/community/comment/deleteComment?commentId=" + commentId,{
        headers:{"satoken":localStorage.getItem("satoken")}
      })
          .then(res => {
            this.reFreshComment()

          }).catch(err => {
        console.log(err)
      })
    },

  reFresh() {
    this.$http.post("http://localhost:8082/api/oj/solution/listSolutions?questionId=" + window.localStorage.getItem("currentQuestionId"),{
      headers:{"satoken":localStorage.getItem("satoken")}
    })
        .then(res => {
          console.log(res)
          this.solutions.splice(0, this.solutions.length)
          for (let i = 0; i < res.data.data.length; i++) {

            this.solutions.push({
              'id': res.data.data[i].solutionId,
              'userName': '',
              'content': res.data.data[i].content,
              'userid': res.data.data[i].userId,
              'qid': res.data.data[i].questionId,
              'time': res.data.data[i].createdTime,
              'language': res.data.data[i].language,
              'title': res.data.data[i].title,
              'code': res.data.data[i].code,
              'likes': res.data.data[i].likes,
              'isThumbed': false,
              'headImgSrc': this.updateHeadImg(res.data.data[i].userId)
            })
            this.$http.post("http://localhost:8082/api/account/user/getUserInfo?userId="
                + this.solutions[i].userid,{
              headers:{"satoken":localStorage.getItem("satoken")}
            })
                .then(res => {
                  this.solutions[i].userName = res.data.data
                }).catch(err => {
              console.log(err)
            })
          }
        }).catch(err => {
      console.log(err)
    })

  },
},
  mounted () {
    this.reFresh()
    // this.$http.post("http://localhost:8082/api/oj/solution/listSolutions?questionId=" + window.localStorage.getItem("currentQuestionId"))
    //   .then(res => {
    //     console.log(res)
    //     this.solutions.splice(0, this.solutions.length)
    //     for (let i = 0; i < res.data.data.length; i++) {
    //       this.solutions.push({
    //         'id': res.data.data[i].solutionId,
    //         'userName': '',
    //         'content': res.data.data[i].content,
    //         'userid': res.data.data[i].userId,
    //         'qid': res.data.data[i].questionId,
    //         'time': res.data.data[i].createdTime,
    //         'language': res.data.data[i].language,
    //         'title': res.data.data[i].title,
    //         'code': res.data.data[i].code,
    //         'likes': res.data.data[i].likes,
    //         'isThumbed': false
    //       })
    //       this.$http.post("http://localhost:8082/api/account/user/getUserInfo?userId="
    //         + this.solutions[i].userid)
    //         .then(res => {
    //           this.solutions[i].userName = res.data.data
    //         }).catch(err => {
    //           console.log(err)
    //         })
    //     }
    //   }).catch(err => {
    //     console.log(err)
    //   })
  }
}
</script>