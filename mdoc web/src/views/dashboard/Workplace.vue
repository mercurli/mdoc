<template>
  <div class="wrapper">
    <div style="width: 160px; background-color: #fffef9" @contextmenu.prevent="handleContextmenu($event, ['contextmenuAddBlockGroup'])">
      <a-menu
        :selected-keys="blockActive"
        ref="blockMenu"
      >
        <template v-for="(item, index) in blocks">
          <a-menu-item
            :key="item.id"
            @contextmenu.prevent.stop="handleContextmenu($event, ['contextmenuAddBlockGroup', 'contextmenuEditBlockGroup'], index)">
            <a-icon type="appstore" />
            <span>{{ item.name }}</span>
          </a-menu-item>
        </template>
      </a-menu>
    </div>
    <div style="width: 200px; background-color: #fffcf9" @contextmenu.prevent="handleContextmenu($event, ['contextmenuAddNoteGroup'])">
      <a-menu
        :default-selected-keys="['1']"
        mode="inline"
      >
        <template v-for="item in notes">
          <a-menu-item
            :key="item.id"
            @contextmenu.prevent.stop="handleContextmenu($event, ['contextmenuAddNoteGroup', 'contextmenuEditNoteGroup'])">
            <a-icon type="note" />
            <span>{{ item.title }}</span>
          </a-menu-item>
        </template>
      </a-menu>
    </div>
    <div style="background: #fff; width: calc(100% - 360px);padding: 20px">
      <a-input style="border: 0; font-size: 24px; margin-bottom: 12px"/>
      <WangEditor />
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
        <context-menu-item @click.native="handleDelBlock">
          <span>删除分区</span>
        </context-menu-item>
      </context-menu-group>
      <!-- 新增笔记分组 -->
      <context-menu-group ref="contextmenuAddNoteGroup">
        <context-menu-item @click.native="handleAddNote">
          <span>新建笔记</span>
        </context-menu-item>
      </context-menu-group>
      <!-- 编辑笔记分组 -->
      <context-menu-group ref="contextmenuEditNoteGroup">
        <context-menu-item>
          <span>重命名笔记</span>
        </context-menu-item>
        <context-menu-item>
          <span>删除笔记</span>
        </context-menu-item>
      </context-menu-group>
    </context-menu>
    <a-modal
      title="新建分区"
      :width="640"
      :visible="blockModalVisible"
      @ok="handleAddBlock"
      @cancel="blockModalVisible = false">
      <a-form :form="blockForm">
        <a-form-item label="分区名">
          <a-input ref="blockName" v-decorator="['name', { rules: [{ required: true, message: '请输入分区名！' }] }]" @keyup.enter="handleAddBlock" />
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
        notes: [],
        blockActive: ['1'],
        blockModalVisible: false,
        blockForm: this.$form.createForm(this)
    }
  },
  computed: {

  },
  watch: {
    blockModalVisible (val) {
      console.log('blockModalVisible')
      console.log(val)
    }
  },
  created () {
  },
  mounted () {
    console.log('user', this.$store.state.user)
    this.getBlocks()
  },
  methods: {
    getBlocks () {
      this.$http.get('/note/block/get', {
        params: {
          uId: 1
        }
      }).then(res => {
        console.log(res)
        this.blocks = res.data
        if (res.data.length > 0) {
          this.getNodes(res.data[0].bId)
        }
      })
    },
    handleAddBlock () {
      this.blockForm.validateFields((err, values) => {
        if (!err) {
          this.$http.get('/note/block/add',
            {
              params: {
                uId: 1,
                display: 1,
                name: values.name
              }
            })
            .then(res => {
              console.log(res)
              this.blockModalVisible = false
              this.blockForm.resetFields()
              this.getBlocks()
            })
        }
      })
    },
    handleDelBlock () {
      this.$http.get('/note/block/del',
        {
          params: {
            id: this.blockActive[0]
          }
        })
        .then(res => {
          console.log(res)
          this.$message.success('删除成功')
          this.getBlocks()
        })
    },
    getNodes (bId) {
      this.$http.get('/note/get',
      {
          bId: bId
      })
      .then(res => {
        console.log(res)
        this.notes = res.data
      })
    },
    handleAddNote () {
      this.$http.get('/note/add',
        {
          params: {
            bId: 1,
            display: 1,
            title: '无标题'
          }
        })
        .then(res => {
          console.log(res)
        })
    },
    handleContextmenu (event, groups, index) {
      if (groups.includes('contextmenuEditBlockGroup')) {
        console.log(this.blocks[index])
        this.blockActive[0] = this.blocks[index].id
        console.log(this.blockActive)
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
<style scoped>
  .wrapper {
    display: flex;
    height: calc(100vh - 112px);
  }
  .wrapper > div {
    height: 100%;
  }
</style>
