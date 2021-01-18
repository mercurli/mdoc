<template>
  <div class="wrapper">
    <div style="width: 160px" @contextmenu.prevent="contextmenuHanlder($event, ['contextmenuAddBlockGroup'])">
      <a-menu
        :default-selected-keys="['1']"
      >
        <template v-for="item in blocks">
          <a-menu-item
            :key="item.id"
            @contextmenu.prevent.stop="contextmenuHanlder($event, ['contextmenuAddBlockGroup', 'contextmenuEditBlockGroup'])">
            <a-icon type="appstore" />
            <span>{{ item.name }}</span>
          </a-menu-item>
        </template>
      </a-menu>
    </div>
    <div style="width: 200px" @contextmenu.prevent="contextmenuHanlder($event, ['contextmenuAddNoteGroup'])">
      <a-menu
        :default-selected-keys="['1']"
        mode="inline"
      >
        <template v-for="item in notes">
          <a-menu-item
            :key="item.id"
            @contextmenu.prevent.stop="contextmenuHanlder($event, ['contextmenuAddNoteGroup', 'contextmenuEditNoteGroup'])">
            <a-icon type="note" />
            <span>{{ item.title }}</span>
          </a-menu-item>
        </template>
      </a-menu>
    </div>
    <div style="background: #fff; width: calc(100% - 360px);padding: 20px">
      <h1>Ngnix</h1>
      <WangEditor />
    </div>
    <!-- 分区菜单 -->
    <context-menu ref="contextmenu">
      <!-- 新增分区分组 -->
      <context-menu-group ref="contextmenuAddBlockGroup">
        <context-menu-item>
          <span>新建分区</span>
        </context-menu-item>
      </context-menu-group>
      <!-- 编辑分区分组 -->
      <context-menu-group ref="contextmenuEditBlockGroup">
        <context-menu-item>
          <span>重命名分区</span>
        </context-menu-item>
        <context-menu-item>
          <span>删除分区</span>
        </context-menu-item>
      </context-menu-group>
      <!-- 新增笔记分组 -->
      <context-menu-group ref="contextmenuAddNoteGroup">
        <context-menu-item>
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
        blocks: [
          {
            id: '1',
            name: '学习',
            createTime: '',
            display: 1,
            uId: 1
          },
          {
            id: '2',
            name: '工作',
            createTime: '',
            display: 2,
            uId: 1
          },
          {
            id: '3',
            name: '项目',
            createTime: '',
            display: 3,
            uId: 1
          }
        ],
        notes: [
          {
            id: '1',
            bId: '1',
            title: 'Ngnix',
            content: '',
            createTime: '',
            updateTime: '',
            display: 1
          },
          {
            id: '2',
            bId: '1',
            title: 'apereo',
            content: 'http://www.apereo.org/cas',
            createTime: '',
            updateTime: '',
            display: 2
          }
        ]
    }
  },
  computed: {

  },
  created () {

  },
  mounted () {

  },
  methods: {
    contextmenuHanlder (event, groups) {
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
