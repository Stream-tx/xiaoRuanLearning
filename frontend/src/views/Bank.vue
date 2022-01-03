<template>
  <div class="home">
    <div class="search">
    <el-row :gutter="20" style="padding-left: 30%;padding-right: 30%;padding-top: 30px">
      <el-col :span="5"><div class="grid-content bg-purple">
          <el-select v-model="difficulty" placeholder="难度" @change="dChange()">
            <el-option
                v-for="item in difficultyOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
      </div></el-col>
      <el-col :span="5"><div class="grid-content bg-purple">
          <el-select v-model="state" placeholder="状态" @change="sChange()">
            <el-option
                v-for="item in stateOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
      </div></el-col>
      <el-col :span="12"><div class="grid-content bg-purple">
        <el-input
            placeholder="请输入内容"
            clearable>
        </el-input>
      </div></el-col>
      <el-col :span="2"><div>
        <el-button @click="search()">
          <el-icon style="vertical-align: middle;">
            <Search />
          </el-icon>
        </el-button>
      </div></el-col>
    </el-row>
    <el-tag
        v-for="tag in tags"
        :key="tag.name"
        closable
        :type="tag.type"
        @close="handleClose(tag)">
      {{tag.name}}
    </el-tag>
    </div>
    <div class="question" >
        <el-table
            @row-click="openDetails"
            :data="tableData"
            style="width: 100%; padding-left: 25%;padding-right: 25%;"
            :row-class-name="tableRowClassName"
        >
          <el-table-column label="id" align="center" prop="id" v-if="false" />
          <el-table-column
              prop="state"
              label="状态"
              sortable
              width="150">
          </el-table-column>
          <el-table-column
              prop="name"
              label="题目"
              sortable
              width="300">
          </el-table-column>
          <el-table-column
              prop="solution"
              label="题解"
              sortable
              width="100">
          </el-table-column>
          <el-table-column
              prop="passingRate"
              label="通过率"
              sortable
              width="100">
          </el-table-column>
          <el-table-column
              prop="difficulty"
              label="难度"
              sortable
              width="150">
          </el-table-column>
        </el-table>
    </div>
  </div>
</template>

<script>
import {Search} from '@element-plus/icons-vue'
export default {
  components:{
    Search,
  },
  data() {
    return {
      tags: [
        { name: '标签一', type: '' },
        { name: '标签二', type: 'info' },
      ],
      difficultyOptions: [{
        value: '困难',
        label: '困难'
      }, {
        value: '中等',
        label: '中等'
      }, {
        value: '简单',
        label: '简单'
      }],
      difficulty: '',
      stateOptions: [{
        value: '已解答√',
        label: '已解答√'
      }, {
        value: '未尝试(--`)',
        label: '未尝试(--`)'
      }, {
        value: '在做了ing',
        label: '在做了ing'
      }],
      state: '' ,
      input: '' ,
      tableData:[{
        id:'1',
        state:'未完成',
        name:'大整数加法',
        solution:'1',
        passingRate:'50%',
        difficulty:'简单',
      },{
        id:'1',
        state:'未完成',
        name:'大整数加法',
        solution:'1',
        passingRate:'50%',
        difficulty:'中等',
      },{
        id:'1',
        state:'未完成',
        name:'大整数加法',
        solution:'1',
        passingRate:'50%',
        difficulty:'困难',
      }],
    };
  },
  methods: {
    openDetails(row){
      this.$router.push("/hdoj/bank/q/"+row.id);
    },
    handleClose(tag) {
      this.tags.splice(this.tags.indexOf(tag), 1);
    },
    dChange(){
      for(let i=0;i<this.tags.length;i++){//先删除之前的标签
        for(let j=0;j<this.difficultyOptions.length;j++){
          if(this.difficultyOptions[j].value==this.tags[i].name){
            this.tags.splice(i,1);
            break;
          }
        }
      }
      switch (this.difficulty){
        case "简单":
          this.tags.push({name: '简单', type: 'success'});
          break;
        case "中等":
          this.tags.push({name: '中等', type: 'warning'});
          break;
        case "困难":
          this.tags.push({name: '困难', type: 'danger' });
          break;
      }
      this.difficulty=null;
    },
    sChange(){
      for(let i=0;i<this.tags.length;i++){//先删除之前的标签
        for(let j=0;j<this.stateOptions.length;j++){
          if(this.stateOptions[j].value==this.tags[i].name){
            this.tags.splice(i,1);
            break;
          }
        }
      }
      switch (this.state){
        case "未尝试(--`)":
          this.tags.push({name: '未尝试(--`)', type: 'info'});
          break;
        case "在做了ing":
          this.tags.push({name: '在做了ing', type: 'info'});
          break;
        case "已解答√":
          this.tags.push({name: '已解答√', type: 'info' });
          break;
      }
      this.state=null;
    },
    search(){

    },
    formatter(row, column) {
      return row.name;
    },
    tableRowClassName({row, rowIndex}) {
      if (row.difficulty == "简单") {
        return 'easy-row';
      } else if (row.difficulty == "中等") {
        return 'middle-row';
      } else if (row.difficulty == "困难")
      return 'difficult-row';
      else
        return '';
    },
  }
}
</script>

<style>

.el-table .difficult-row {
  background: rgba(238, 107, 107, 0.99);
}

.el-table .middle-row {
  background: rgba(232, 173, 151, 0.96);
}

.el-table .easy-row {
  background: rgba(229, 255, 229, 0.95);
}
.el-row {
  margin-bottom: 20px;
  &:last-child {
   margin-bottom: 0;
 }
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>