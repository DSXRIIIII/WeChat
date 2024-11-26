package cn.dsxriiiii.l3x.domain.auth.service;

import cn.dsxriiiii.l3x.domain.auth.adapter.port.IProductPort;
import cn.dsxriiiii.l3x.domain.auth.adapter.repository.IOrderRepository;
import cn.dsxriiiii.l3x.domain.auth.model.aggregate.CreateOrderAggregate;
import cn.dsxriiiii.l3x.domain.auth.model.entity.OrderEntity;
import cn.dsxriiiii.l3x.domain.auth.model.entity.PayOrderEntity;
import cn.dsxriiiii.l3x.domain.auth.model.entity.ProductEntity;
import cn.dsxriiiii.l3x.domain.auth.model.entity.ShopCartEntity;
import cn.dsxriiiii.l3x.domain.auth.model.valobj.OrderStatusVO;
import cn.dsxriiiii.l3x.types.common.Constants;
import lombok.extern.slf4j.Slf4j;

/**
 * @PackageName: cn.dsxriiiii.l3x.domain.auth.service
 * @Author: DSXRIIIII
 * @Email: 1870066109@qq.com
 * @Date: Created in  2024/11/26 15:25
 * @Description:
 **/
@Slf4j
public abstract class AbstractOrderService implements IOrderService{
    protected final IOrderRepository repository;
    protected final IProductPort productPort;

    public AbstractOrderService(IOrderRepository repository,IProductPort productPort){
        this.productPort = productPort;
        this.repository = repository;
    }

    @Override
    public PayOrderEntity createOrder(ShopCartEntity shopCartEntity) throws Exception {
        // 查询是否有存在的订单
        OrderEntity unpaidOrderEntity = repository.queryUnPayOrder(shopCartEntity);
        if (null != unpaidOrderEntity && OrderStatusVO.PAY_WAIT.equals(unpaidOrderEntity.getOrderStatusVO())){
            return PayOrderEntity.builder()
                    .orderId(unpaidOrderEntity.getOrderId())
                    .payUrl(unpaidOrderEntity.getPayUrl())
                    .build();
        }else if (null != unpaidOrderEntity && OrderStatusVO.CREATE.equals(unpaidOrderEntity.getOrderStatusVO())){
            log.info("存在create状态订单：订单信息为：{}",unpaidOrderEntity);
        }

        ProductEntity productEntity = productPort.queryProductByProductId(shopCartEntity.getProductId());
        OrderEntity orderEntity = CreateOrderAggregate.buildOrderEntity(productEntity.getProductId(), productEntity.getProductName());
        CreateOrderAggregate orderAggregate = CreateOrderAggregate.builder()
                .userId(shopCartEntity.getUserId())
                .productEntity(productEntity)
                .orderEntity(orderEntity)
                .build();
        this.doSaveOrder(orderAggregate);

        return PayOrderEntity.builder()
                .orderId(orderEntity.getOrderId())
                .payUrl("暂无")
                .build();
    }

    protected abstract void doSaveOrder(CreateOrderAggregate orderAggregate);
}
