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
    const value = ref('')
    // 根据关键字提示
    // monaco.languages.registerCompletionItemProvider(
    //   'java',
    //   {
    //     triggerCharacters: ['abstract', 'boolean', 'break', 'byte', 'case', 'catch', 'char', 'class', 'continue', 'default', 'do', 'double', 'else', 'extends', 'false', 'final', 'finally', 'float', 'for', 'if', 'implements', 'import', 'instanceof', 'int', 'interface', 'long', 'native', 'new', 'null', 'package', 'private', 'protected', 'public', 'return', 'short', 'static', 'super', 'switch', 'synchronized', 'this', 'throw', 'throws', 'transient', 'try', 'true', 'void', 'volatile', 'while'],
    //     provideCompletionItems (model, position, context, token) {
    //       const suggestion = triggerCharacters.map(item => {
    //         return {
    //           label: item,
    //           quickSuggestions: false,
    //           kind: monaco.languages.CompletionItemKind['Text'], // 用于定义提示的icon，可参照官网进行选择
    //           insertText: item
    //         }
    //       })
    //       return {
    //         suggestions: suggestion
    //       }
    //     },
    //   }
    // )

    // 根据输入的内容提示，两者只能取一
    // monaco.languages.registerCompletionItemProvider(
    //   "java",
    //   {
    //     triggerCharacters: ['ds.', '.'],
    //     provideCompletionItems: (model, position) => {
    //       const { lineNumber, column } = position
    //       // 光标前文本
    //       const textBeforePointer = model.getValueInRange({
    //         startLineNumber: lineNumber,
    //         startColumn: 0,
    //         endLineNumber: lineNumber,
    //         endColumn: column
    //       })
    //       if (['ds.'].includes(textBeforePointer)) {
    //         return {
    //           suggestions: [
    //             {
    //               label: 'connection("")', //显示的提示名称
    //               insertText: 'connection("")' //选择后粘贴到编辑器中的文字
    //             },
    //             {
    //               label: 'query("","")',
    //               insertText: 'query("","")'
    //             },
    //           ]
    //         }
    //       }
    //       if (['ds.connection("").'].includes(textBeforePointer)) {
    //         return {
    //           suggestions: [
    //             {
    //               label: 'query("")',
    //               insertText: 'query("")',
    //             },
    //           ]
    //         }
    //       }

    //     }
    //   }
    // )
	const container = ref(null);
    ctx.emit("value", value)
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
        if (value !== this.value) {
          this.value = value
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
      value,
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