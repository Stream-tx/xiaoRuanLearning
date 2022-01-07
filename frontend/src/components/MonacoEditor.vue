 <template>
  <div ref="container" class="container"></div>
</template>

 <script>
import * as monaco from "monaco-editor"
import { ref } from '@vue/reactivity'
import { onMounted, watch } from '@vue/runtime-core'
import axios from "axios"

export default {
  props: [
    "myArgs"
  ],
  setup (props, ctx) {
    const data = ref('')
    const container = ref(null)
    const myArgs = ref('')
    onMounted(() => {
      var editor = monaco.editor.create(container.value, {
        language: 'java',
        minimap: {
          enabled: false
        },
        selectOnLineNumbers: true,
        roundedSelection: false,
        cursorStyle: 'line', // 光标样式
        automaticLayout: false, // 自动布局
        glyphMargin: true, // 字形边缘
        useTabStops: false,
        fontSize: 16, // 字体大小
        autoIndent: false, //自动布局
        wordWrap: 'on',
      })


      /*axios.post("http://localhost:8081/question/getQuestion?questionId=" + window.localStorage.getItem("questionId"))
          .then(res => {
            console.log("res.data", res.data);
            myArgs=res.data.data.argsType;
          })
          .catch(err => {
            console.log(err);
          })*/
      watch(
        () => props.myArgs,
        (val, pervVal) => {
          myArgs.value = val
          let type = myArgs.value
          let args = ''
          let returnT = type[0]
          if (type != null) {
            let t = type[1].split(",")
            if (args.length == 2)
              args = t[0] + " arg1," + t[1] + " arg2"
            else
              args = t[0] + " arg1"
          }
          let content = "public class Run {\n"
            + "    public " + returnT + " run(" + args + ") {\n"
            + "        \n"
            + "    }\n"
            + "}"
          this.$http.post("http://localhost:8081/code/getTheLatestCode?userId=" + type[2] + "&questionId=" + type[3])
            .then(res => {
              tmp = res.data.data.content
              if (tmp.length() > 10) {
                content = tmp
              }
            })
          editor.setValue(content)
        }
      )

      editor.onDidChangeModelContent(e => {
        const value = editor.getValue() //使value和其值保持一致
        if (value !== data.value) {
          data.value = value
          ctx.emit("data", data.value)
          // this.props.getValue && this.props.getValue(value)
        }
      })
    })

    return {
      myArgs,
      data,
      container
    }
  },
}
 </script>

<style>
.container {
  width: 800px;
  height: 600px;
  text-align: left;
}
</style>