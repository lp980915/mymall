package com.ljc.mymall.ware;

import com.ljc.mymall.ware.service.WareInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MymallWareApplicationTests {

    @Autowired
    private WareInfoService wareInfoService;
    @Test
    void contextLoads() {
        wareInfoService.list();
    }

}
