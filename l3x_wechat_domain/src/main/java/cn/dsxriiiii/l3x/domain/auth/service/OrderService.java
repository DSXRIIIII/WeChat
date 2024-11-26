package cn.dsxriiiii.l3x.domain.auth.service;

import cn.dsxriiiii.l3x.domain.auth.adapter.port.IProductPort;
import cn.dsxriiiii.l3x.domain.auth.adapter.repository.IOrderRepository;
import cn.dsxriiiii.l3x.domain.auth.model.aggregate.CreateOrderAggregate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @PackageName: cn.dsxriiiii.l3x.domain.auth.service
 * @Author: DSXRIIIII
 * @Email: 1870066109@qq.com
 * @Date: Created in  2024/11/26 15:26
 * @Description:
 **/
@Service
@Slf4j
public class OrderService extends AbstractOrderService{

    public OrderService(IOrderRepository repository, IProductPort port) {
        super(repository, port);
    }

    @Override
    protected void doSaveOrder(CreateOrderAggregate orderAggregate) {
        repository.doSaveOrder(orderAggregate);
    }
}
