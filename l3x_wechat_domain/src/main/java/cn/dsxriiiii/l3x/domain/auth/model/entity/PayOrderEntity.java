package cn.dsxriiiii.l3x.domain.auth.model.entity;

import cn.dsxriiiii.l3x.domain.auth.model.valobj.OrderStatusVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @PackageName: cn.dsxriiiii.l3x.domain.auth.model.entity
 * @Author: DSXRIIIII
 * @Email: 1870066109@qq.com
 * @Date: Created in  2024/11/26 15:24
 * @Description:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PayOrderEntity {
    private String userId;
    private String orderId;
    private String payUrl;
    private OrderStatusVO orderStatus;

}
