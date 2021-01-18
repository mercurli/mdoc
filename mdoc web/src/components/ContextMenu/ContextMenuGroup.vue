<template>
  <li class="contextmenu-group">
    <ul v-if="visible">
      <slot />
    </ul>
  </li>
</template>

<script>
export default {
  name: 'ContextMenuGroup',
  data () {
    return {
      visible: true
    }
  }
}
</script>

<style scoped>
  ul {
    padding: 0;
  }
  .contextmenu-group {
    border-bottom: 1px solid #f1f2f3;
  }
</style>
