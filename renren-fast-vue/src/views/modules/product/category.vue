<template>
  <div>
    <el-switch
      v-model="draggable"
      active-text="开启拖拽"
      inactive-text="关闭拖拽">
    </el-switch>
    <el-button v-if="draggable" @click="batchSave">批量保存</el-button>
    <el-tree :data="menus" :props="defaultProps" :expand-on-click-node="false" show-checkbox node-key="catId"
    :default-expanded-keys="expendedKey" :draggable="draggable" :allow-drop="allowDrop" @node-drop="handleDrop">
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
            <el-button
              type="text"
              size="mini"
              @click="() => edit(data)">
            edit
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
      :title="dialogTitle"
      :visible.sync="dialogVisible"
      width="30%"
    :close-on-click-modal="false">
      <el-form :model="category">
        <el-form-item label="分类名称" >
          <el-input v-model="category.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="图标" >
          <el-input v-model="category.icon" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="计量单位" >
          <el-input v-model="category.productUnit" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="submitData">确 定</el-button>
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
        pCid:[],
        updateNodes:[],
        maxLevel:0,
        menus: [],
        defaultProps: {
          children: 'children',
          label: 'name'
        },
        expendedKey:[],
        dialogTitle:'',
        dialogVisible:false,
        dialogType:'',  //add,edit
        category:{
          name:'',
          parentCid:0,
          catLevel:0,
          showStatus:1,
          sort:0,
          catId:null,
          icon:'',
          productUnit:''
        },
        draggable:false
      }
    },
    methods: {
      //draggingNode代表当前正在拖拽节点，dropNode代表拖拽目标节点，dropType代表拖拽状态
      handleDrop(draggingNode, dropNode, dropType, ev) {
        console.log('tree drop: ', dropNode.label, dropType);
        console.log('updateNodes',this.updateNodes);
        //1.当前节点最新的父节点ID
        let pCid=0;
        let brother=null;
        //以before或after方式插入的节点的父节点ID就是目标节点的父ID
        if(dropType==='before'||dropType==='after'){
          pCid=dropNode.parent.data.catId===undefined?0:dropNode.parent.data.catId;
          brother=dropNode.parent.childNodes;
          //以inner方式插入的节点的父节点ID就是目标节点ID
        }else {
          pCid=dropNode.data.catId;
          brother=dropNode.childNodes;
        }
        this.pCid.push(pCid);
        //2.当前拖拽节点的最新顺序
      for(let i=0;i<brother.length;i++){
        if(brother[i].data.catId===draggingNode.data.catId){
          //如果遍历的是当前正在拖拽的节点
          let catLevel=draggingNode.level;
          if(brother[i].level!==draggingNode.level){
            //当前节点的层级发生变化
            catLevel=brother[i].level;
            //修改他子节点的层级
            this.updateChildNodeLevel(brother[i]);
          }
          this.updateNodes.push( {catId:brother[i].data.catId,sort:i,parentCid:pCid,catLevel:catLevel});
        }else {
          this.updateNodes.push( {catId:brother[i].data.catId,sort:i});
        }
      }
        //3.当前拖拽节点的最新层级

      },
      updateChildNodeLevel(node){
        if(node.childNodes.length>0){
          for(let i=0;i<node.childNodes.length;i++){
            let cNode=node.childNodes[i].data;
            this.updateNodes.push({catId:cNode.catId,catLevel:node.childNodes[i].level});
            this.updateChildNodeLevel(node.childNodes[i]);
          }
        }

      },
      batchSave(){
        this.$http({
          url: this.$http.adornUrl('/product/category/update/sort'),
          method: 'post',
          data: this.$http.adornData(this.updateNodes, false)
        }).then(({data}) => {
          this.$message.success("菜单顺序等修改成功!");
          this.getMenus();
          this.expendedKey=[this.pCid];
          this.updateNodes=[];
          this.maxLevel=0;
          // this.pCid=0;
        });
      },
      allowDrop(draggingNode, dropNode, type) {
        //被拖动的当前节点以及所在的父节点总层数不能大于3
     console.log(draggingNode, dropNode, type)
        this.countNodeLevel(draggingNode);
     //当前拖动的节点+父节点所在的深度不大于3即可
        let deep=Math.abs(this.maxLevel-draggingNode.level)+1;
        console.log("深度",deep);
        if(type==="inner"){
          // console.log(`this.maxLevel:${this.maxLevel};draggingNode.data.catLevel:${draggingNode.data.catLevel};dropNode.level:${dropNode.level}`)
          return (deep+dropNode.level)<=3;
        }else{
          return (deep+dropNode.parent.level)<=3;
        }
      },
      countNodeLevel(draggingNode){
        //找出所有子节点，求出最大深度
        if(draggingNode.childNodes!==null&& draggingNode.childNodes.length>0){
          for(let i=0;i<draggingNode.childNodes.length;i++){
            if(draggingNode.childNodes[i].level>this.maxLevel){
              this.maxLevel=draggingNode.childNodes[i].level;
            }
            //进行递归,判断当前遍历的节点还有没有子节点
            this.countNodeLevel(draggingNode.childNodes[i]);
          }
        }
      },
      submitData(){
        if(this.dialogType==='add'){
          this.addCategory();
        }else if(this.dialogType==='edit'){
          this.editCategory();
        }
      },
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
        this.dialogType='add';
        this.dialogTitle='添加分类';
        this.dialogVisible=true;
        //新添加元素的父ID为当前选择元素的ID
        this.category.parentCid=data.catId;
        //乘一防止为字符串类型，强行转为数字
        this.category.catLevel=data.catLevel*1+1;

        this.category.catId=null;
        this.category.name='';
        this.category.icon="";
        this.category.productUnit="";
        this.category.sort=0;
        this.category.showStatus=1;
      },
      edit(data){
        this.$http({
          url: this.$http.adornUrl(`/product/category/info/${data.catId}`),
          method: 'get',
        }).then(({data}) => {
          console.log("要修改的数据",data.data);
          this.category=data.data;
        })
        this.dialogType='edit';
        this.dialogTitle='修改分类';
        this.dialogVisible=true;
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
          this.dialogVisible=false;
          this.getMenus();
          //设置默认展开的父节点ID
          this.expendedKey=[this.category.parentCid]
        });
      },
      editCategory(){
        //使用解构表达式只获取这四个字段
        let {catId,name,icon,productUnit}=this.category;
        let data={catId,name,icon,productUnit} //省略了key名
        this.$http({
          url: this.$http.adornUrl('/product/category/update'),
          method: 'post',
          data: this.$http.adornData(data, false)
        }).then(({data}) => {
          this.$message.success("菜单修改成功!");
          this.dialogVisible=false;
          this.getMenus();
          this.expendedKey=[this.category.parentCid]
        });
      }
    }
  }
</script>

<style scoped>

</style>
