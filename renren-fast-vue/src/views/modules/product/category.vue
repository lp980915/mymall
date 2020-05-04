<template>
    <div>
      <el-tree :data="data" :props="defaultProps" @node-click="handleNodeClick"></el-tree>
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
          }).then(res=>{
            console.log(res);
          })
        }
      }
    }
</script>

<style scoped>

</style>
