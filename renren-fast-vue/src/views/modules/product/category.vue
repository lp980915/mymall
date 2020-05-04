<template>
    <div>
      <el-tree :data="menus" :props="defaultProps" @node-click="handleNodeClick"></el-tree>
    </div>
</template>

<script>
    export default {
        name: "category",
      created() {
       this.getMenus();
      },
      data(){
          return {
            menus:[],
            defaultProps: {
              children: 'children',
              label: 'name'
            }
          }
      },
      methods: {
        handleNodeClick(data) {
          console.log(data);
        },
        getMenus(){
          this.$http({
            url: this.$http.adornUrl('/product/category/list/tree'),
            method: 'get',
            //解构data
          }).then(({data})=>{
            this.menus=data.data
          })
        }
      }
    }
</script>

<style scoped>

</style>
