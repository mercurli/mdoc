<template>
  <li>
    <slot />
  </li>
</template>

<script>
export default {
  name: 'ContextMenuItem'
}
</script>

<style lang="less" scoped>
  li {
    width: 120px;
    height: 32px;
    line-height: 32px;
    padding: 0 12px;
    &:hover {
      background-color: #f0f2f5;
      cursor: pointer;
    }
  }
</style>
