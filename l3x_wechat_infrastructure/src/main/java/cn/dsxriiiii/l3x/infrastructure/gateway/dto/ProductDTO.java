package cn.dsxriiiii.l3x.infrastructure.gateway.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @PackageName: cn.dsxriiiii.l3x.infrastructure.gateway.dto
 * @Author: DSXRIIIII
 * @Email: 1870066109@qq.com
 * @Date: Created in  2024/11/26 15:14
 * @Description: 产品DTO
 **/
@Data
public class ProductDTO {
    /** 商品ID */
    private String productId;
    /** 商品名称 */
    private String productName;
    /** 商品描述 */
    private String productDesc;
    /** 商品价格 */
    private BigDecimal price;
}
