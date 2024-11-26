package cn.dsxriiiii.l3x.test;

import cn.dsxriiiii.l3x.domain.auth.model.entity.PayOrderEntity;
import cn.dsxriiiii.l3x.domain.auth.model.entity.ShopCartEntity;
import cn.dsxriiiii.l3x.domain.auth.service.IOrderService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @PackageName: cn.dsxriiiii.test.domain
 * @Author: DSXRIIIII
 * @Email: 1870066109@qq.com
 * @Date: Created in  2024/11/26 16:09
 * @Description:
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {
    @Resource
    private IOrderService orderService;

    @Test
    public void test_create_order() throws Exception {
        ShopCartEntity shopCartEntity = new ShopCartEntity();
        shopCartEntity.setUserId("liuxiaoxuan5201314");
        shopCartEntity.setProductId("10001");
        PayOrderEntity payOrderEntity = orderService.createOrder(shopCartEntity);
        log.info("请求参数:{}", JSON.toJSONString(shopCartEntity));
        log.info("测试结果:{}", JSON.toJSONString(payOrderEntity));
    }
}
