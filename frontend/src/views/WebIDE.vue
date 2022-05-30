<template>

  <div class = "main">
  <div class = "m">
    <div class="fzdm">
      <li @click="downloadHTML" >
        <img :src="downloadImgSrc" style="height:24px;width:24px"  />
      </li>
      <li @click="saveCode"><img :src="saveCodeImgSrc" style="height:24px;width:24px" /></li>
      <li @click="switchTheme"><img :src="switchThemeImgSrc" style="height:24px;width:22px" /></li>
      <li @click="searchKeyWord"><img :src="searchKeyWordImgSrc" @mouseover="showInput=true"  style="height:24px;width:22px" /></li>
      <li ><input ref="searchBox" v-show =" showInput||inputText.length" v-model="inputText" @mouseover="showInput=true" @mouseout="showInput=false" style="font-size:17px;width:140px;height:18px;margin-top:3px;margin-bottom:2px"/></li>
<!--      v-if=" showInput||inputText.length"-->
      <li @click="replace"><img :src="replaceImgSrc" @mouseover="showReplace=true"  style="height:24px;width:23px" /></li>
      <li><input v-if=" showReplace||replaceText.length"  v-model="replaceTextAfter" @mouseover="showReplace=true"  style="font-size:18px;width:140px;height:20px;margin-top:2px;margin-bottom:2px"/></li>
      <li id="replaceLabel" v-if=" showReplace||replaceText.length" @mouseover="showReplace=true"  style="font-size:20px;">替换为</li>
      <li><input v-if=" showReplace||replaceText.length"  v-model="replaceText" @mouseover="showReplace=true" @mouseout="showReplace=false" style="font-size:18px;width:140px;height:20px;margin-top:2px;margin-bottom:2px"/></li>
<!--      showInput||inputText.length-->
    </div>
  <div class="codeEditBox">
    <v-ace-editor id="editor"
                  v-model:value="content"
                  @init="editorInit"
                  lang="html"
                  theme="chrome"
        style="height: 96%" >

    </v-ace-editor>
  </div>
  </div>
  <div class = "m1">
    <iframe id="iframe" ></iframe>
  </div>
  </div>

</template>
<script>
import { saveAs } from 'file-saver'
import { VAceEditor } from 'vue3-ace-editor';
import 'ace-builds/src-noconflict/mode-text';
import 'ace-builds/src-noconflict/theme-chrome';

import { watch, onMounted, onBeforeUnmount, ref} from "vue";
import ace from "ace-builds";
import "ace-builds/webpack-resolver";
import "ace-builds/src-noconflict/mode-yaml";
import "ace-builds/src-noconflict/theme-chaos";
import "ace-builds/src-noconflict/ext-language_tools";
import "ace-builds/src-noconflict/ext-emmet";
import "ace-builds/src-noconflict/snippets/yaml";
import Editor from 'vue2-ace-editor';
export default {
  components: {
    VAceEditor,
  },

  data() {
    return {
      replaceText:"",
      replaceTextAfter:"",
      showReplace:"",

      inputText:"",
      showInput:'',
      replaceImgSrc: require('@/assets/replace.png'),
      downloadImgSrc: require('@/assets/downLoad.png'),
      searchKeyWordImgSrc:require("@/assets/searchKeyWord.png"),
      saveCodeImgSrc: require('@/assets/saveCode.png'),
      switchThemeImgSrc:require('@/assets/switchTheme.png'),
      code: '',
      content:'',
      editor:'',
      themeIndex:0,
      themeArr:["ace/theme/clouds_midnight","ace/theme/chrome","ace/theme/mono_industrial","ace/theme/solarized_light"]
    };
  },
  // computed:{
  //   nice:{
  //     function(){
  //       if(this.inputText)
  //         console.log('@')
  //       return '5'
  //     }
  //   }
  // }
  // ,
  // watch:{
  //   inputText: {
  //     handler() {
  //       console.log('@')
  //       //let searchBox = this.$refs.searchBox
  //       //console.log(searchBox)
  //       if (1) {
  //         //searchBox.style.opacity = '1'
  //       } else {
  //        // searchBox.style.opacity = '0'
  //       }
  //
  //     },
  //     deep: true,
  //     immediate:true
  //   }
  //
  // },
  methods: {
    replace(){

      let editor = ace.edit('editor')
      editor.find(this.replaceText,{
        backwards: false,
        wrap: true,
        caseSensitive: false,
        wholeWord: false,
        regExp: false
      });
      editor.replaceAll(this.replaceTextAfter)

    },
    searchKeyWord(){
      //this.showInput = false
      //console.log(this.showInput)
      let editor = ace.edit('editor')
      editor.find(this.inputText,{
        backwards: false,
        wrap: true,
        caseSensitive: false,
        wholeWord: false,
        regExp: false
      });
      //editor.findNext();

    },
    switchTheme(){
      console.log(this.showInput)
      let editor = ace.edit('editor')
      //console.log(editor)
      //editor.session.setMode("ace/mode/html");

      this.themeIndex = (this.themeIndex+1)%this.themeArr.length
      console.log(this.themeIndex)
      editor.setTheme(this.themeArr[this.themeIndex])

      //editor.findPrevious();
      //editor.setTheme("ace/theme/solarized_dark")

    },
    codeChange(val){
      console.log(val);
    },
    Run() {
      let editor = ace.edit('editor')
      //console.log(123)
  var o = document.getElementById("iframe");

  let ed = o.contentDocument;
  console.log(ed)

  ed.open();
  ed.write('<html><head></head><body>' + editor.getValue() + '</body></html>');
  ed.close();
  ed.contentEditable = true;
  ed.designMode = 'on';

},
    downloadHTML() {
      let editor = ace.edit('editor')
      let blob = new Blob([editor.getValue()], {
        type: "text/plain;charset=utf-8"
      });

      let FileSaver = require('file-saver')
      FileSaver.saveAs(blob, "miniSoft.html")
    },
    saveCode(){
      let editor = ace.edit('editor')
      localStorage.setItem("frontEndCode", editor.getValue());
      alert('保存成功！')
    }
  },
  mounted(){
    window.vue = this
    let editor = ace.edit('editor')
    console.log(editor)
    editor.session.setMode("ace/mode/html");
    editor.setTheme("ace/theme/clouds_midnight");
    //editor.setTheme("ace/theme/chrome");
    editor.setOptions({
      enableBasicAutocompletion: true,
      enableSnippets: true,
      enableLiveAutocompletion: true
  })
    editor.getSession().on('change',()=>{
      console.log(editor.getValue())
      let o = document.getElementById("iframe");
      let ed = o.contentDocument;
      console.log(ed)
      ed.open();
      ed.write('<html><head></head><body>' + editor.getValue() + '</body></html>');
      ed.close();
      ed.contentEditable = true;
      ed.designMode = 'on';
    })
    if(localStorage.getItem('frontEndCode')!=null){
      editor.setValue(localStorage.getItem('frontEndCode'));
    }
  else
      editor.setValue("the new code here");
  }
}
</script>
<style scoped>
/*.outerFrame{*/
/*  background-color: #040404;*/
/*}*/
.ace_editor {
  font: 18px/normal 'Monaco', 'Menlo', 'Ubuntu Mono', 'Consolas', 'source-code-pro', monospace;
}
.codeEditBox{
  width:100%;
  height:calc(100% - 30px);
  background-color: #171616;
  padding-top: 30px;
  /*border:1px solid #dcdee2;*/
}
.main {
  border-color: #040404;
  background-color: #353434;
  width:100%;

  height: calc(100% - 101px);
  padding-top: 40px;

}
.m {
  width: 50%;
  padding-left: 2%;
  height: 100%;
  float: left;
  /*border: 1px solid #353434;*/
  box-sizing: border-box;
  background-color: #353434;
  position: relative;
  padding-down: 2%;
}
.m1 {
  width: 50%;
  padding-right: 2%;
  padding-down: 2%;
  height: 100%;
  float: left;
  /*border: 1px solid #353434;*/
  box-sizing: border-box;
  background-color:#353434;
  color:#353434;
  position: relative;
}
iframe {
  border-width: 0px;
  width: 100%;
  height: 96%;
  background-color: #ffffff;
}

@media screen and (max-width:980px) {
  .m {
    width: 100%;
  }
  html,
  body {
    height: 100%;
    overflow: auto
  }
}
.fzdm {
  position: absolute;
  top: 0px;
  right: 0px;
  z-index: 99;
  font-size: 12px;
}

.fzdm li {
  list-style-image: none;
  list-style-type: none;
  float: right;
  margin-left: 1px;
  background-color: rgba(39, 39, 39, 1.00);
  padding: 0px 4px 0px 4px;
  color: rgba(255, 255, 255, 1.00);
  cursor: pointer
}

.fzdm li:hover {
  background-color: rgba(32, 103, 229, 1.00);
}
#replaceLabel:hover {
  background-color: rgba(39, 39, 39, 1.00);
}

</style>

