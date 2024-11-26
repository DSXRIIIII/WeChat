package cn.dsxriiiii.l3x.domain.auth.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @PackageName: cn.dsxriiiii.l3x.domain.auth.model.valobj
 * @Author: DSXRIIIII
 * @Email: 1870066109@qq.com
 * @Date: Created in  2024/11/26 15:25
 * @Description:
 **/
@Getter
@AllArgsConstructor
public enum OrderStatusVO {

    CREATE("CREATE", "创建完成 - 如果调单了，也会从创建记录重新发起创建支付单"),
    PAY_WAIT("PAY_WAIT", "等待支付 - 订单创建完成后，创建支付单"),
    PAY_SUCCESS("PAY_SUCCESS", "支付成功 - 接收到支付回调消息"),
    DEAL_DONE("DEAL_DONE", "交易完成 - 商品发货完成"),
    CLOSE("CLOSE", "超时关单 - 超市未支付"),
    ;

    private final String code;
    private final String desc;

}
