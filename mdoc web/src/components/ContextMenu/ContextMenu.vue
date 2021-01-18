<template>
  <ul v-show="visible" :style="style">
    <slot />
  </ul>
</template>

<script>
export default {
  name: 'ContextMenu',
  props: {

  },
  data () {
    return {
      visible: false,
      style: {
        top: 0,
        left: 0
      }
    }
  },
  watch: {
    visible (val) {
      if (val) {
        document.body.addEventListener('click', this.handleBodyClick)
      } else {
        document.body.removeEventListener('click', this.handleBodyClick)
      }
    }
  },
  beforeDestroy () {
    console.log('beforeDestroy')
    document.body.removeEventListener('click', this.handleBodyClick)
  },
  methods: {
    show (position) {
      if (position) {
          this.style = {
            top: `${position.top}px`,
            left: `${position.left}px`
          }
          this.visible = true
        }
    },
    handleBodyClick () {
      console.log('handleBodyClick')
      this.visible = false
    }
  }
}
</script>

<style scoped>
  ul {
    position: fixed;
    background: #fff;
    padding: 0;
    border: 1px solid #d1d2d3;
    z-index: 2;
  }
</style>
