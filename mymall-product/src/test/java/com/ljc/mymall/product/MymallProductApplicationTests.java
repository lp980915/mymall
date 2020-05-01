package com.ljc.mymall.product;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljc.mymall.product.entity.BrandEntity;
import com.ljc.mymall.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MymallProductApplicationTests {

    @Autowired
    BrandService brandService;

    //测试mybatisPlus的crud操作
    @Test
    void contextLoads() {
    /*    BrandEntity brandEntity=new BrandEntity();
        //主键自增，所以不需要给主键的值
        brandEntity.setDescript("P40手机")
        .setName("华为");
        brandService.save(brandEntity);
        System.out.println("保存成功...");*/

  /*      BrandEntity brandEntity=new BrandEntity();
        brandEntity.setBrandId(1L)
        .setDescript("华为p40pro，帅的一");
        brandService.updateById(brandEntity);*/

//        QueryWrapper代表查询条件
        // eq("brand_id",1);代表查询字段为brand_id这一列中，brand_id=1L时对应的值
        List<BrandEntity> list = brandService.list(new QueryWrapper<BrandEntity>().eq("brand_id", 1L));
        //forEach快捷操作
        list.forEach((item)->{
            System.out.println(item);
        });
    }

}
