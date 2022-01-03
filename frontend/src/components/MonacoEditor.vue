 <template>
  <div ref="container" class="container"></div>
</template>

 <script>
import * as monaco from "monaco-editor"
import { ref } from '@vue/reactivity'
import { onMounted, watch } from '@vue/runtime-core'

export default {
  props: {
    rightWidth: {
      type: String
    }
  },
  setup (props, ctx) {
    const data = ref('')
	const container = ref(null)
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
      editor.setValue("public class Run {\n"
        + "    public static void main(String[] args) {\n"
        + "        \n"
        + "    }\n"
        + "}")
      editor.onDidChangeModelContent(e => {
        const value = editor.getValue() //使value和其值保持一致
        if (value !== data.value) {
          data.value = value
		  ctx.emit("data", data.value)
          // this.props.getValue && this.props.getValue(value)
        }
      })
      watch(
        () => props.rightWidth,
        (val, prevVal) => {
          console.log(typeof val)
          document.getElementById('container').style.width = val
        }
      )
    })
    return {
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