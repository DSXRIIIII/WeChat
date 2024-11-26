package cn.dsxriiiii.l3x.infrastructure.adapter.repository;

import cn.dsxriiiii.l3x.domain.auth.adapter.repository.IOrderRepository;
import cn.dsxriiiii.l3x.domain.auth.model.aggregate.CreateOrderAggregate;
import cn.dsxriiiii.l3x.domain.auth.model.entity.OrderEntity;
import cn.dsxriiiii.l3x.domain.auth.model.entity.ProductEntity;
import cn.dsxriiiii.l3x.domain.auth.model.entity.ShopCartEntity;
import cn.dsxriiiii.l3x.domain.auth.model.valobj.OrderStatusVO;
import cn.dsxriiiii.l3x.infrastructure.dao.IOrderDao;
import cn.dsxriiiii.l3x.infrastructure.dao.po.PayOrder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @PackageName: cn.dsxriiiii.l3x.infrastructure.adapter.repository
 * @Author: DSXRIIIII
 * @Email: 1870066109@qq.com
 * @Date: Created in  2024/11/26 15:12
 * @Description:
 **/
@Repository
public class OrderRepository implements IOrderRepository {
    @Resource
    private IOrderDao orderDao;

    @Override
    public void doSaveOrder(CreateOrderAggregate orderAggregate) {
        String userId = orderAggregate.getUserId();
        ProductEntity productEntity = orderAggregate.getProductEntity();
        OrderEntity orderEntity = orderAggregate.getOrderEntity();

        PayOrder order = new PayOrder();
        order.setUserId(userId);
        order.setProductId(productEntity.getProductId());
        order.setProductName(productEntity.getProductName());
        order.setOrderId(orderEntity.getOrderId());
        order.setOrderTime(orderEntity.getOrderTime());
        order.setTotalAmount(productEntity.getPrice());
        order.setStatus(orderEntity.getOrderStatusVO().getCode());

        orderDao.insert(order);
    }

    @Override
    public OrderEntity queryUnPayOrder(ShopCartEntity shopCartEntity) {
        // 1. 封装参数
        PayOrder orderReq = new PayOrder();
        orderReq.setUserId(shopCartEntity.getUserId());
        orderReq.setProductId(shopCartEntity.getProductId());

        // 2. 查询到订单
        PayOrder order = orderDao.queryUnPayOrder(orderReq);
        if (null == order) return null;

        // 3. 返回结果
        return OrderEntity.builder()
                .productId(order.getProductId())
                .productName(order.getProductName())
                .orderId(order.getOrderId())
                .orderStatusVO(OrderStatusVO.valueOf(order.getStatus()))
                .orderTime(order.getOrderTime())
                .totalAmount(order.getTotalAmount())
                .payUrl(order.getPayUrl())
                .build();
    }
}
