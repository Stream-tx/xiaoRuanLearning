<template>
  <div class="solution">
  <el-dialog :title="solutions[currentIndex].title" v-model="dialogSolutionVisible">
    <el-row>
      <el-col :span="8"><div class="grid-content ">
        作者:{{solutions[currentIndex].userName}}
      </div></el-col>
      <el-col :span="8"><div class="grid-content">
        语言:{{solutions[currentIndex].language}}
      </div></el-col>
      <el-col :span="8"><div class="grid-content ">
        时间:{{solutions[currentIndex].time}}
      </div></el-col>
    </el-row>
    <el-collapse>
      <el-collapse-item title="思路" name="1">
        <div style="text-align: left">{{solutions[currentIndex].content}}</div>
      </el-collapse-item>
      <el-collapse-item title="代码" name="2">
        <div style="text-align: left">{{solutions[currentIndex].code}}</div>
      </el-collapse-item>
      <el-collapse-item title="评论" name="3">
        <el-card style="background-color: #444444" class="box-card" v-for="item in comments" :key="item.id" @click="view(item)">
          <h5 style="text-align: left;font-size: larger;color:floralwhite">{{item.userName}}</h5>
          <span style="color:floralwhite;text-align: left" >{{item.content}}</span>
          <el-row :gutter="20">
            <el-col :span="4"><div class="grid-content" style="color:floralwhite">
              <Star style="height: 15px;width: 15px" v-if="!item.isThumbed" @click="thumb(item)"/>
              <StarFilled style="height: 15px;width: 15px" v-if="item.isThumbed"/>
              点赞数:{{item.likes}}
            </div></el-col>
            <el-col :span="4"><div class="grid-content" style="color:floralwhite">
              时间:{{item.time}}
            </div></el-col>
          </el-row>
        </el-card>
      </el-collapse-item>
    </el-collapse>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary"  @click="dialogSolutionVisible = false">确 定</el-button>
    </div>
  </el-dialog>
    <div class="solutionHeader">

    </div>
  <el-card style="background-color: lightcyan" class="box-card" v-for="item in solutions" :key="item.id" @click="view(item)">
    <div slot="header" class="clearfix">
      <h5 style="font-size: larger;text-align: left">[{{item.userName}}]:{{item.title}}</h5>
    </div>
    <el-row :gutter="20">
      <el-col :span="4"><div class="grid-content ">
        <Star style="height: 15px;width: 15px" v-if="!item.isThumbed" @click="thumb(item)"/>
        <StarFilled style="height: 15px;width: 15px" v-if="item.isThumbed"/>
        点赞数:{{item.likes}}
      </div></el-col>
      <el-col :span="4"><div class="grid-content ">
        语言:{{item.language}}
      </div></el-col>
      <el-col :span="4"><div class="grid-content ">
        时间:{{item.time}}
      </div></el-col>
    </el-row>
  </el-card>
  </div>



</template>

<script>
import {Star,StarFilled} from '@element-plus/icons-vue'
export default {
  components:{
    Star,
    StarFilled,
  },
  data() {
    return {
      dialogSolutionVisible:false,

      comments:[{
        userid:'1',
        userName:'dqf',
        commentid:'1',
        solutionid:'1',
        content:'太牛啦',
        likes:'5',
        time:'今天',
        isThumbed:false
      }],
      solutions:[{
        id:'0',
        userName:'dqf',
        content:'不会写',
        userid:'1',
        qid:'1',
        time:'昨天',
        language:'c++',
        title:'你猜我怎么写',
        code:'C艹',
        likes:'6',
        isThumbed:false
      }],
      currentIndex:'0'
    };
  },
  methods: {

    thumb(row){
      row.isThumbed=true;
      row.likes++;
    },
    view(row){
      this.dialogSolutionVisible=true;
      for(let i=0;i<this.solutions.length;i++){
        if(row.id==this.solutions[i].id){
          this.currentIndex=i;
          break;
        }
      }
    }
  }
}
</script>