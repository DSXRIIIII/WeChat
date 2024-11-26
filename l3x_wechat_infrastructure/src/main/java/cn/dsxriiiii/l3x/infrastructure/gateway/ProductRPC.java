package cn.dsxriiiii.l3x.infrastructure.gateway;

import cn.dsxriiiii.l3x.infrastructure.gateway.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @PackageName: cn.dsxriiiii.l3x.infrastructure.gateway
 * @Author: DSXRIIIII
 * @Email: 1870066109@qq.com
 * @Date: Created in  2024/11/26 15:14
 * @Description:
 **/
@Service
public class ProductRPC {
    public ProductDTO queryProductByProductId(String productID) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(productID);
        productDTO.setProductName("测试商品");
        productDTO.setProductDesc("这是一个测试商品");
        productDTO.setPrice(new BigDecimal("1.68"));
        return productDTO;
    }
}
