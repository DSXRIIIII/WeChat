package cn.dsxriiiii.l3x.domain.auth.model.entity;

import cn.dsxriiiii.l3x.domain.auth.model.valobj.OrderStatusVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @PackageName: cn.dsxriiiii.l3x.domain.auth.model.entity
 * @Author: DSXRIIIII
 * @Email: 1870066109@qq.com
 * @Date: Created in  2024/11/26 15:24
 * @Description:
 **/
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderEntity {
    private String productId;
    private String productName;
    private String orderId;
    private Date orderTime;
    private BigDecimal totalAmount;
    private OrderStatusVO orderStatusVO;
    private String payUrl;
}
