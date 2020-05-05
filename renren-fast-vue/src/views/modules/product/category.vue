<template>
  <div>
    <el-tree :data="menus" :props="defaultProps" :expand-on-click-node="false" show-checkbox node-key="catId">
        <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
          <el-button
            v-if="node.level<=2"
            type="text"
            size="mini"
            @click="() => append(data)">
            Append
          </el-button>
          <!--无子节点才可以删除-->
          <el-button
            v-if="node.childNodes.length==0"
            type="text"
            size="mini"
            @click="() => remove(node, data)">
            Delete
          </el-button>
        </span>
        </span>
    </el-tree>
  </div>
</template>

<script>
  export default {
    name: "category",
    created() {
      this.getMenus();
    },
    data() {
      return {
        menus: [],
        defaultProps: {
          children: 'children',
          label: 'name'
        }
      }
    },
    methods: {
      getMenus() {
        this.$http({
          url: this.$http.adornUrl('/product/category/list/tree'),
          method: 'get',
          //解构data
        }).then(({data}) => {
          this.menus = data.data
        })
      },
      append(data) {
        console.log("append", data);
      },

      remove(node, data) {
        console.log("remove", node, data);
      },
    }
  }
</script>

<style scoped>

</style>
