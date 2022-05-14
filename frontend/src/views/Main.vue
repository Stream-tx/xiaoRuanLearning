<template>
  <div class="home">
    <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        @select="handleSelect"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b">
      <el-menu-item style="font-size: x-large;padding-left:5% " index="1">miniSoft</el-menu-item>
      <el-menu-item style="font-size: medium;padding-left:1% " index="2"><router-link to="/miniSoft/bank">题库</router-link></el-menu-item>
      <el-menu-item style="font-size: medium;" index="3"><router-link to="/miniSoft/material">资料</router-link></el-menu-item>
      <el-menu-item style="font-size: medium;" index="4"><router-link to="/miniSoft/online">在线编译</router-link></el-menu-item>
      <el-menu-item style="font-size: medium;" index="5"><router-link to="/miniSoft/webIDE">webIDE</router-link></el-menu-item>
      <el-menu-item index="6" style='font-size: medium; position: absolute;right:5%'><router-link to="/miniSoft/myInfo">个人信息</router-link></el-menu-item>
      <el-menu-item index="7" style='position: absolute;right:2%' @click="logout()"><img :src="logOutImgSrc" style="height:24px;width:24px" /></el-menu-item>
    </el-menu>

    <!--    <el-button style="width:130px;height:40px; border-radius:10px;">编辑个⼈资料</el-button>-->

    <div ref="dragArea" class="drag-area">
      <img :src="headImgSrc" width="35" @click="popOutAI" height="35" style="border-radius:50%;margin-top:5px">
      <!--      <el-tooltip effect="dark" content="返回主界面" placement="top">-->
      <!--        <el-button size="mini" icon="el-icon-back" circle @click="callback"></el-button>-->
      <!--      </el-tooltip>-->

      <!--      <el-tooltip effect="dark" content="分享页面" placement="top">-->
      <!--        <el-button size="mini" icon="el-icon-share" circle @click="shareLink"></el-button>-->
      <!--      </el-tooltip>-->
    </div>

    <div ref="dragAI">
      <div ref="dragInputAI" class="drag-AI">
        <el-input  class="inline-input" v-model="contentData" @keyup.enter.native="sendAIMessage"
                   style=" font-size: 25px !important; width: 370px !important; opacity: 0.5 !important; border: 0 !important;user-select:none !important;"
                   placeholder="Type to miniSoft"></el-input>
      </div>
      <!--      <img :src="headImgSrc" width="30" height="15" style="border-radius:50%;margin-top:5px">-->
      <div ref="dragChatBoxAI" class="drag-ChatBoxAI">

        <el-row style="height:fit-content">

          <div  style="right:20px!important;color:#6C6C6CFF;text-align:left; height:fit-content;word-wrap:break-word !important; width:350px !important;word-break:normal !important; font-size: 22px !important;">
            <p style="margin: 15px;">{{this.sendMessages}}</p>
            <p ref="AIReply" @click="jumpToRecommand" style="line-height:28px;transition: opacity 0.5s ease-in-out 0.1s;height:fit-content;opacity:0;margin: 15px;margin-bottom: 5px;background-image: linear-gradient(to right, midnightblue, cornflowerblue);-webkit-background-clip: text;color: transparent;font-size: 22px;width:350px !important;">
              {{this.replyMessage }}</p>
            <!--              Something went wrong. Please try again.-->


          </div>

        </el-row>

        <!--      <img :src="headImgSrc" width="30" height="15" style="border-radius:50%;margin-top:5px">-->
      </div>
    </div>




    <router-view/>

  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      currentID:"",
      logOutImgSrc: require('@/assets/logOut.png'),
      recommendId:'',
      replyMessage:'',
      sendMessages:'',
      activeIndex: '2',
      contentData:'',

      headImgSrc: 'https://tva2.sinaimg.cn/large/9bd9b167ly1fzjxyujrpaj20b40b40ta.jpg',
    };
  },
  methods: {
    logout(){
      this.$http.get('http://localhost:8082/api/account/user/logout',{
        headers:{
          "satoken":window.localStorage.getItem("satoken")
        }
      })
          .then(res => {
            this.$message('退出成功');
            console.log(res);
            this.timer=setTimeout(() => {  //创建并执行定时器
              window.location.href="/";
            },1000);
          })
          .catch(err => {
            console.log(err);
          });
    },
    jumpToRecommand(){
      if(this.recommandId==-1)
      {

      }else{
        window.localStorage.setItem("currentQuestionId", this.recommandId)
        window.localStorage.setItem("questionId", this.recommandId)
        this.$router.push("/miniSoft/bank/q/" +this.recommandId)

      }


    },
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    }
    ,
    callback() {
      this.$router.push("/index");
      console.log("返回主界面");
    },
    getCurrentId () {
      var userInfo = JSON.parse(localStorage.getItem('token'))
      console.log(userInfo)
      this.currentID = userInfo.id
    },
    sendAIMessage()
    {
      let AIReply = this.$refs.AIReply;
      let AIChatBox = this.$refs.dragChatBoxAI;
      AIReply.style.opacity = '0'
      AIChatBox.style.maxHeight = '50px'
      if(this.contentData!='')
      {
        AIChatBox.style.opacity = "1";
        this.sendMessages = this.contentData
        this.replyMessage = ''
        this.contentData =''
        console.log(this.sendMessages)
        let url = 'http://localhost:8082/api/onlinetools/ai/addChatRecords?qChat='+this.sendMessages+'&&userId='+this.currentID
        console.log(url)
        axios.post(url).then(res => {
          console.log(res)
          console.log(res.data)
          this.replyMessage = res.data.data.response
          console.log(this.replyMessage)
          this.$refs.dragChatBoxAI.style.maxHeight = '1200px'
          this.$refs.AIReply.style.opacity = '1'
          if(res.data.data.question.questionId=='undefined')
          {
            this.recommendId = -1
          }else{
            this.recommendId = res.data.data.question.questionId
            this.replyMessage += ' '
            this.replyMessage += res.data.data.question.name
          }
        })
      }
    }
    ,
    popOutAI(){
      let dragArea = this.$refs.dragArea;
      let AI = this.$refs.dragAI;
      let AIChatBox = this.$refs.dragChatBoxAI;
      let AIInPut = this.$refs.dragInputAI
      // if(AI.style.visibility == "hidden"){
      //   AI.style.visibility = 'visible';
      //
      // }else{
      //   AI.style.visibility = "hidden";
      // }
      if(AIInPut.style.opacity == 0){
        AIInPut.style.opacity = '1';



      }else{
        AIInPut.style.opacity = "0";
        AIChatBox.style.opacity = "0";

      }
    },

    /**
     * 分享页面
     */
    shareLink() {
      console.log("分享页面");
    }

  },
  mounted() {
    /**
     * 监听悬浮拖拽区域
     */
    this.$nextTick(() => {
      // 获取DOM元素
      let dragArea = this.$refs.dragArea;
      let AI = this.$refs.dragAI;
      let AIChatBox = this.$refs.dragChatBoxAI;
      let AIInPut = this.$refs.dragInputAI
      // 缓存 clientX clientY 的对象: 用于判断是点击事件还是移动事件
      let clientOffset = {};
      // 绑定鼠标按下事件
      dragArea.addEventListener("mousedown", (event) => {

        let offsetX = dragArea.getBoundingClientRect().left; // 获取当前的x轴距离
        let offsetY = dragArea.getBoundingClientRect().top; // 获取当前的y轴距离
        let innerX = event.clientX - offsetX; // 获取鼠标在方块内的x轴距
        let innerY = event.clientY - offsetY; // 获取鼠标在方块内的y轴距
        console.log(offsetX, offsetY, innerX, innerY);
        // 缓存 clientX clientY
        clientOffset.clientX = event.clientX;
        clientOffset.clientY = event.clientY;
        // 鼠标移动的时候不停的修改div的left和top值
        document.onmousemove = function (event) {
          dragArea.style.left = event.clientX - innerX + "px";
          dragArea.style.top = event.clientY - innerY + "px";
          // dragArea 距离顶部的距离
          let dragAreaTop = window.innerHeight - dragArea.getBoundingClientRect().height;
          // dragArea 距离左部的距离
          let dragAreaLeft = window.innerWidth - dragArea.getBoundingClientRect().width;
          // 边界判断处理
          // 1、设置左右不能动
          dragArea.style.left = dragAreaLeft + "px";
          // 2、超出顶部处理
          if (dragArea.getBoundingClientRect().top <= 0) {
            dragArea.style.top = "0px";
          }
          // 3、超出底部处理
          if (dragArea.getBoundingClientRect().top >= dragAreaTop) {
            dragArea.style.top = dragAreaTop + "px";
          }
        };
        // 鼠标抬起时，清除绑定在文档上的mousemove和mouseup事件；否则鼠标抬起后还可以继续拖拽方块
        document.onmouseup = function () {
          document.onmousemove = null;
          document.onmouseup = null;



        };
      }, false);
      // 绑定鼠标松开事件
      dragArea.addEventListener('mouseup', (event) => {

        let clientX = event.clientX;
        let clientY = event.clientY;
        if (clientX === clientOffset.clientX && clientY === clientOffset.clientY) {
          console.log('click 事件');
        } else {
          console.log('drag 事件');
        }
      })



      ////ref改变位置，class控制样式
      AI.addEventListener("mousedown", (event) => {
//AIInPut
        let offsetX = AIChatBox.getBoundingClientRect().left; // 获取当前的x轴距离
        let offsetY = AIChatBox.getBoundingClientRect().top; // 获取当前的y轴距离
        let innerX = event.clientX - offsetX; // 获取鼠标在方块内的x轴距
        let innerY = event.clientY - offsetY; // 获取鼠标在方块内的y轴距
        console.log('yes')
        console.log(offsetX, offsetY, innerX, innerY);
        // 缓存 clientX clientY
        clientOffset.clientX = event.clientX;
        clientOffset.clientY = event.clientY;
        // 鼠标移动的时候不停的修改div的left和top值
        document.onmousemove = function (event) {
          AIChatBox.style.left = event.clientX - innerX + "px";
          AIChatBox.style.top = event.clientY - innerY + "px";
          AIInPut.style.left = event.clientX - innerX + "px";
          AIInPut.style.top = event.clientY - innerY -100+'px'
          // dragArea 距离顶部的距离
          let dragAreaTop = window.innerHeight - AIChatBox.getBoundingClientRect().height;
          // dragArea 距离左部的距离
          let dragAreaLeft = window.innerWidth - AIChatBox.getBoundingClientRect().width;
          // 边界判断处理
          // 1、设置左右不能动
          //dragArea.style.left = dragAreaLeft + "px";
          // 2、超出顶部处理
          if (AIChatBox.getBoundingClientRect().top <= 0) {
            AIChatBox.style.top = "0px";
          }
          // 3、超出底部处理
          if (AIChatBox.getBoundingClientRect().top >= dragAreaTop) {
            AIChatBox.style.top = dragAreaTop + "px";
          }
        };
        // 鼠标抬起时，清除绑定在文档上的mousemove和mouseup事件；否则鼠标抬起后还可以继续拖拽方块
        document.onmouseup = function () {
          document.onmousemove = null;
          document.onmouseup = null;



        };
      }, false);
      // 绑定鼠标松开事件
      AI.addEventListener('mouseup', (event) => {

        let clientX = event.clientX;
        let clientY = event.clientY;
        if (clientX === clientOffset.clientX && clientY === clientOffset.clientY) {
          console.log('click 事件');
        } else {
          console.log('drag 事件');
        }
      })


      ///
      // AIChatBox.addEventListener("mousedown", (event) => {
      //
      //   let offsetX = AIChatBox.getBoundingClientRect().left; // 获取当前的x轴距离
      //   let offsetY = AIChatBox.getBoundingClientRect().top; // 获取当前的y轴距离
      //   let innerX = event.clientX - offsetX; // 获取鼠标在方块内的x轴距
      //   let innerY = event.clientY - offsetY; // 获取鼠标在方块内的y轴距
      //   console.log('yes')
      //   console.log(offsetX, offsetY, innerX, innerY);
      //   // 缓存 clientX clientY
      //   clientOffset.clientX = event.clientX;
      //   clientOffset.clientY = event.clientY;
      //   // 鼠标移动的时候不停的修改div的left和top值
      //   document.onmousemove = function (event) {
      //     AIChatBox.style.left = event.clientX - innerX + "px";
      //     AIChatBox.style.top = event.clientY - innerY + "px";
      //     // dragArea 距离顶部的距离
      //     let dragAreaTop = window.innerHeight - AIChatBox.getBoundingClientRect().height;
      //     // dragArea 距离左部的距离
      //     let dragAreaLeft = window.innerWidth - AIChatBox.getBoundingClientRect().width;
      //     // 边界判断处理
      //     // 1、设置左右不能动
      //     //dragArea.style.left = dragAreaLeft + "px";
      //     // 2、超出顶部处理
      //     if (AIChatBox.getBoundingClientRect().top <= 0) {
      //       AIChatBox.style.top = "0px";
      //     }
      //     // 3、超出底部处理
      //     if (AIChatBox.getBoundingClientRect().top >= dragAreaTop) {
      //       AIChatBox.style.top = dragAreaTop + "px";
      //     }
      //   };
      //   // 鼠标抬起时，清除绑定在文档上的mousemove和mouseup事件；否则鼠标抬起后还可以继续拖拽方块
      //   document.onmouseup = function () {
      //     document.onmousemove = null;
      //     document.onmouseup = null;
      //
      //
      //
      //   };
      // }, false);
      // 绑定鼠标松开事件
      // AIChatBox.addEventListener('mouseup', (event) => {
      //
      //   let clientX = event.clientX;
      //   let clientY = event.clientY;
      //   if (clientX === clientOffset.clientX && clientY === clientOffset.clientY) {
      //     console.log('click 事件');
      //   } else {
      //     console.log('drag 事件');
      //   }
      // })



    });
  }
}
</script>


<style scoped>
/*.my-el-select /deep/ .el-input .el-input__inner {*/
/*  border-color: transparent  !important;*/
/*}*/
/deep/input::-webkit-input-placeholder {
  color: #4a4a4c;
}
.inline-input>>>.el-input__inner {
  background-color: transparent !important;

  border-radius: 15px 15px 15px 15px !important;
  border: 0;
}

.home{
  height:100%;
}
.drag-area {
  position: fixed;
  right: 20px;
  top: 80%;
  z-index: 1000;
  padding: 13px;
  width: fit-content;
  opacity: 1;
  background-color: rgba(255, 255, 255, 0.1);
  border-radius: 8px;
  box-shadow: 0px 2px 15px 0px rgba(9,41,77,0.15);
  cursor: move;
  user-select: none;
  text-align: center;
}
.drag-AI {
  transition: opacity 0.5s ease-in-out;
  border-radius: 27px 27px 27px 27px !important;
  position: fixed;
  right: 100px;
  top: 300px;
  z-index: 2000;
  padding: 13px;
  width: fit-content;
  height:fit-content;
  opacity: 0;
  background-color: rgba(12, 12, 12, 0.1);
  /*border-radius: 8px;*/
  box-shadow: 0px 2px 15px 0px rgba(9,41,77,0.15);
  cursor: move;
  user-select: none;
  text-align: center;
  visibility: visible;


  font-family: sans-serif;
  line-height: 1;
  -webkit-backdrop-filter: blur(5px);
  backdrop-filter: blur(5px);
  max-width: 50%;
  max-height: 50%;
}

.drag-ChatBoxAI {
  transition-property: opacity,max-height;
  transition-duration:0.5s ,1.5s;
  transition-timing-function:ease-in-out,ease-in-out;
  border-radius: 27px 27px 27px 27px !important;
  position: fixed;
  right: 100px;
  top: 400px;
  z-index: 2000;
  padding: 13px;
  width: 370px;
  height: fit-content;
  max-height: 50px;
  opacity: 0;
  background-color: rgba(12, 12, 12, 0.1);
  /*border-radius: 8px;*/
  box-shadow: 0px 2px 15px 0px rgba(9,41,77,0.15);
  cursor: move;
  user-select: none;
  text-align: center;
  visibility: visible;


  font-family: sans-serif;
  line-height: 1;
  -webkit-backdrop-filter: blur(5px);
  backdrop-filter: blur(5px);
  max-width: 50%;
}
</style>