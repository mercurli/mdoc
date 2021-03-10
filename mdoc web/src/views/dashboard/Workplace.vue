<template>
  <div class="wrapper">
    <div style="width: 180px; background-color: #fffef9; border-right: 1px solid #eee;" @contextmenu.prevent="handleContextmenu($event, ['contextmenuAddBlockGroup'])">
      <ul class="menu">
        <li
          v-for="(item, index) in blocks"
          :key="item.id"
          @contextmenu.prevent.stop="handleContextmenu($event, ['contextmenuAddBlockGroup', 'contextmenuEditBlockGroup'], index)"
          @click="activeBlockId = item.id"
          :class="{ 'menu-item': true, 'active': item.id === activeBlockId }">
          <a-icon type="appstore" />
          <span>{{ item.name }}</span>
        </li>
      </ul>
    </div>
    <div style="width: 180px; background-color: #fffef9; border-right: 1px solid #eee;" @contextmenu.prevent="handleContextmenu($event, ['contextmenuAddNoteGroup'])">
      <ul class="menu">
        <li
          v-for="(item, index) in notes"
          :key="item.id"
          @contextmenu.prevent.stop="handleContextmenu($event, ['contextmenuAddNoteGroup', 'contextmenuEditNoteGroup'])"
          @click="activeNoteIndex = index"
          :class="{ 'menu-item': true, 'active': activeNoteIndex === index }">
          <span>{{ item.title || '无标题' }}</span>
        </li>
      </ul>
    </div>
    <div style="background: #fff; width: calc(100% - 360px); padding: 20px; overflow: hidden;">
      <input v-model="noteTitle" @blur="updateNote" class="note-title"/>
      <WangEditor :value="noteContent" />
    </div>
    <!-- 分区菜单 -->
    <context-menu ref="contextmenu">
      <!-- 新增分区分组 -->
      <context-menu-group ref="contextmenuAddBlockGroup">
        <context-menu-item @click.native="handleOpenBlockModal">
          <span>新建分区</span>
        </context-menu-item>
      </context-menu-group>
      <!-- 编辑分区分组 -->
      <context-menu-group ref="contextmenuEditBlockGroup">
        <context-menu-item>
          <span>重命名分区</span>
        </context-menu-item>
        <context-menu-item @click.native="delBlock">
          <span>删除分区</span>
        </context-menu-item>
      </context-menu-group>
      <!-- 新增笔记分组 -->
      <context-menu-group ref="contextmenuAddNoteGroup">
        <context-menu-item @click.native="addNote">
          <span>新建笔记</span>
        </context-menu-item>
      </context-menu-group>
      <!-- 编辑笔记分组 -->
      <context-menu-group ref="contextmenuEditNoteGroup">
        <context-menu-item>
          <span>重命名笔记</span>
        </context-menu-item>
        <context-menu-item @click.native="delNote">
          <span>删除笔记</span>
        </context-menu-item>
      </context-menu-group>
    </context-menu>
    <a-modal
      title="新建分区"
      :width="600"
      :visible="blockModalVisible"
      @ok="addBlock"
      @cancel="blockModalVisible = false">
      <a-form :form="blockForm" :label-col="{ span: 5 }" :wrapper-col="{ span: 17 }">
        <a-form-item label="分区名">
          <a-input ref="blockName" v-decorator="['name', { rules: [{ required: true, message: '请输入分区名！' }] }]" @keyup.enter="addBlock" />
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>

<script>
 import { WangEditor, ContextMenu, ContextMenuGroup, ContextMenuItem } from '@/components'

export default {
  name: 'Workplace',
  components: {
    WangEditor,
    ContextMenu,
    ContextMenuGroup,
    ContextMenuItem
  },
  data () {
    return {
        blocks: [],
        activeBlockId: '',
        blockModalVisible: false,
        blockForm: this.$form.createForm(this),
        notes: [],
        activeNoteIndex: 0,
        noteTitle: '',
        noteContent: 'test'
    }
  },
  watch: {
    activeBlockId (val) {
      console.log('activeBlockId', this.activeBlockId)
      this.getNotes(val)
    },
    notes (val) {
      console.log(val)
    },
    noteTitle (val) {
      console.log(val)
      this.notes[this.activeNoteIndex].title = val
    },
    noteContent (val) {
      console.log(val)
      this.notes[this.activeNoteIndex].content = val
    }
  },
  mounted () {
    console.log('user', this.$store.state.user)
    this.getBlocks().then(() => {
      if (this.blocks.length) {
        this.activeBlockId = this.blocks[0].id
      }
    })
  },
  methods: {
    getBlocks () {
      return new Promise(resolve => {
        this.$http.get('/note/block/get', {
          params: {
            userId: 1
          }
        }).then(res => {
          console.log(res)
          this.blocks = res.data
          resolve()
        })
      })
    },
    addBlock () {
      this.blockForm.validateFields((err, values) => {
        if (!err) {
          const disorder = this.blocks.length ? parseInt(this.blocks[this.blocks.length - 1].disorder) + 1 : 1
          this.$http.get('/note/block/add',
            {
              params: {
                userId: 1,
                disorder: disorder,
                name: values.name
              }
            })
            .then(res => {
              console.log(res)
              this.blocks.push(res.data)
              this.activeBlockId = res.data.id
              this.addNote()
              this.blockModalVisible = false
              this.blockForm.resetFields()
            })
        }
      })
    },
    delBlock () {
      this.$http.get('/note/block/del',
        {
          params: {
            id: this.activeBlockId
          }
        })
        .then(res => {
          console.log(res)
          this.$message.success('删除成功')
          this.getBlocks().then(() => {
            if (this.blocks.length) {
              this.activeBlockId = this.blocks[this.blocks.length - 1].id
            }
          })
        })
    },
    getNotes (blockId) {
      this.$http.get('/note/get',
      {
          params: {
            blockId: blockId
          }
      })
      .then(res => {
        console.log(res)
        this.notes = res.data
        if (this.notes.length) {
          this.noteTitle = this.notes[this.activeNoteIndex].title
        }
      })
    },
    addNote () {
      const disorder = this.notes.length ? parseInt(this.notes[this.notes.length - 1].disorder) + 1 : 1
      this.$http.get('/note/add',
        {
          params: {
            blockId: this.activeBlockId,
            disorder: disorder
          }
        })
        .then(res => {
          console.log(res)
          this.notes.push(res.data)
        })
    },
    updateNote () {
      this.$http.get('/note/update',
        {
          params: this.notes[this.activeNoteIndex]
        })
        .then(res => {
          console.log(res)
        })
    },
    delNote () {
      this.$http.get('/note/del',
        {
          params: {
            id: this.notes[this.activeNoteIndex].id
          }
        })
        .then(res => {
          console.log(res)
          this.$message.success('删除成功')
        })
    },
    handleContextmenu (event, groups, index) {
      if (groups.includes('contextmenuEditBlockGroup')) {
        console.log(this.blocks[index])
        this.activeBlockId = this.blocks[index].id
      }
      this.$refs.contextmenu.$children.forEach(component => {
        if (groups.includes(component.$vnode.data.ref)) {
          component.visible = true
        } else {
          component.visible = false
        }
      })
      this.$refs.contextmenu.show({
        left: event.x,
        top: event.y
      })
    },
    handleOpenBlockModal () {
      this.blockModalVisible = true
      console.log('refs', this.$refs)
    }
  }
}
</script>
<style lang="less" scoped>
  .wrapper {
    display: flex;
    height: calc(100vh - 112px);
  }
  .wrapper {
    div {
      height: 100%;
    }
  }
  .menu {
    padding-left: 0;
    .menu-item {
      height: 40px;
      line-height: 40px;
      padding: 0 12px;
      &:hover {
        background-color: #f5f4ee;
        cursor: pointer;
      }
      &.active {
        background-color: #ddd;
      }
      .anticon {
        margin-right: 8px;
      }
    }
  }
  .note-title {
    border: none;
    border-bottom: 1px solid #ccc;
    margin-bottom: 12px;
    font-size: 24px;
    &:focus {
      outline: none;
    }
  }
</style>
