package cn.dsxriiiii.l3x.domain.auth.service;

import cn.dsxriiiii.l3x.domain.auth.model.entity.PayOrderEntity;
import cn.dsxriiiii.l3x.domain.auth.model.entity.ShopCartEntity;

/**
 * @PackageName: cn.dsxriiiii.l3x.domain.auth.service
 * @Author: DSXRIIIII
 * @Email: 1870066109@qq.com
 * @Date: Created in  2024/11/26 15:25
 * @Description:
 **/
public interface IOrderService {
    PayOrderEntity createOrder(ShopCartEntity shopCartEntity) throws Exception;
}
