<template>
  <ul v-show="isShow" :style="{left: x + 'px', top: y + 'px'}">
    <slot />
  </ul>
</template>

<script>
export default {
  name: 'ContextMenu',
  props: {
    isShow: {
      type: Boolean,
      default: false
    },
    x: {
      type: Number,
      default: 0
    },
    y: {
      type: Number,
      default: 0
    }
  },
  methods: {

  }
}
</script>

<style scoped>
  ul {
    position: fixed;
    background: #fff;
    padding: 4px 0;
  }
</style>
