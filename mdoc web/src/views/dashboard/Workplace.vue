<template>
  <div class="wrapper">
    <div style="width: 160px">
      <a-menu
        :default-selected-keys="['1']"
      >
        <template v-for="item in blocks">
          <a-menu-item :key="item.id">
            <a-icon type="appstore" />
            <span>{{ item.name }}</span>
          </a-menu-item>
        </template>
      </a-menu>
    </div>
    <div style="width: 200px">
      <a-menu
        :default-selected-keys="['1']"
        mode="inline"
      >
        <template v-for="item in notes">
          <a-menu-item :key="item.id">
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
  </div>
</template>

<script>
 import { WangEditor } from '@/components'

export default {
  name: 'Workplace',
  components: {
    WangEditor
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
