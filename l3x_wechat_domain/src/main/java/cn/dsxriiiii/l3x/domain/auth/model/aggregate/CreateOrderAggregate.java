package cn.dsxriiiii.l3x.domain.auth.model.aggregate;

import cn.dsxriiiii.l3x.domain.auth.model.entity.OrderEntity;
import cn.dsxriiiii.l3x.domain.auth.model.entity.ProductEntity;
import cn.dsxriiiii.l3x.domain.auth.model.valobj.OrderStatusVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Date;

/**
 * @PackageName: cn.dsxriiiii.l3x.domain.auth.model.aggregate
 * @Author: DSXRIIIII
 * @Email: 1870066109@qq.com
 * @Date: Created in  2024/11/26 15:24
 * @Description:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderAggregate {
    private String userId;

    private ProductEntity productEntity;

    private OrderEntity orderEntity;

    public static OrderEntity buildOrderEntity(String productId,String productName) {
        return OrderEntity.builder()
                .productId(productId)
                .productName(productName)
                .orderId(RandomStringUtils.randomNumeric(14))
                .orderTime(new Date())
                .orderStatusVO(OrderStatusVO.CREATE)
                .build();
    }
}
