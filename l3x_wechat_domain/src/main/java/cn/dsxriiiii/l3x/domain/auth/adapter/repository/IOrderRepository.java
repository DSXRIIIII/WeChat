package cn.dsxriiiii.l3x.domain.auth.adapter.repository;

import cn.dsxriiiii.l3x.domain.auth.model.aggregate.CreateOrderAggregate;
import cn.dsxriiiii.l3x.domain.auth.model.entity.OrderEntity;
import cn.dsxriiiii.l3x.domain.auth.model.entity.ShopCartEntity;

/**
 * @PackageName: cn.dsxriiiii.l3x.domain.auth.adapter.repository
 * @Author: DSXRIIIII
 * @Email: 1870066109@qq.com
 * @Date: Created in  2024/11/26 15:22
 * @Description:
 **/
public interface IOrderRepository {

    void doSaveOrder(CreateOrderAggregate createOrderAggregate);

    OrderEntity queryUnPayOrder(ShopCartEntity shopCartEntity);
}
