<template>
  <div>
    <el-tree :data="menus" :props="defaultProps" :expand-on-click-node="false" show-checkbox node-key="catId"
    :default-expanded-keys="expendedKey">
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

    <el-dialog
      title="提示"
      :visible.sync="addMenuDialog"
      width="30%">
      <el-form :model="category">
        <el-form-item label="分类名称" >
          <el-input v-model="category.name" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="addMenuDialog = false">取 消</el-button>
    <el-button type="primary" @click="addCategory">确 定</el-button>
  </span>
    </el-dialog>
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
        },
        expendedKey:[],
        addMenuDialog:false,
        category:{
          name:'',
          parentCid:0,
          catLevel:0,
          showStatus:1,
          sort:0
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
        this.addMenuDialog=true;
        //新添加元素的父ID为当前选择元素的ID
        this.category.parentCid=data.catId;
        //乘一防止为字符串类型，强行转为数字
        this.category.catLevel=data.catLevel*1+1;

      },

      remove(node, data) {
        console.log('remove',node,data);
        this.$confirm(`是否删除【${data.name}】菜单`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let ids=[data.catId];
          this.$http({
            url: this.$http.adornUrl('/product/category/delete'),
            method: 'post',
            data: this.$http.adornData(ids, false)
          }).then(({data}) => {
            this.$message.success('菜单删除成功！');
            this.getMenus();
            //设置默认展开的父节点ID
            this.expendedKey=[node.parent.data.catId]
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      addCategory(){
        console.log("category",this.category);
        this.$http({
          url: this.$http.adornUrl('/product/category/save'),
          method: 'post',
          data: this.$http.adornData(this.category, false)
        }).then(({data}) => {
          this.$message.success("保存成功!");
          this.addMenuDialog=false;
          this.getMenus();
          //设置默认展开的父节点ID
          this.expendedKey=[this.category.parentCid]
        });
      }
    }
  }
</script>

<style scoped>

</style>
