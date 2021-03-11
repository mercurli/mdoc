<template>
  <div :class="prefixCls">
    <div ref="editor" class="editor-wrapper" style="height: 800px"></div>
  </div>
</template>

<script>
import WEditor from 'wangeditor'

export default {
  name: 'WangEditor',
  props: {
    prefixCls: {
      type: String,
      default: 'ant-editor-wang'
    },
    // eslint-disable-next-line
    value: {
      type: String
    }
  },
  data () {
    return {
      editor: null,
      editorContent: null
    }
  },
  watch: {
    value (val) {
      this.editorContent = val
      this.editor.txt.html(val || '')
    }
  },
  mounted () {
    this.initEditor()
  },
  methods: {
    initEditor () {
      this.editor = new WEditor(this.$refs.editor)
      // this.editor.onchangeTimeout = 200
      this.editor.customConfig.onchange = (html) => {
        this.editorContent = html
        this.$emit('change', this.editorContent)
      }
      this.editor.create()
    }
  }
}
</script>

<style lang="less">
.ant-editor-wang {
  .editor-wrapper {
    text-align: left;
  }
}
.w-e-text-container,
.w-e-menu {
  z-index: 1 !important;
}
</style>
