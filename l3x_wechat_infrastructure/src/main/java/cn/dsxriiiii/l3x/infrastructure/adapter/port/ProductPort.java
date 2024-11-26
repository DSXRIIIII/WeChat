package cn.dsxriiiii.l3x.infrastructure.adapter.port;

import cn.dsxriiiii.l3x.domain.auth.adapter.port.IProductPort;
import cn.dsxriiiii.l3x.domain.auth.model.entity.PayOrderEntity;
import cn.dsxriiiii.l3x.domain.auth.model.entity.ProductEntity;
import cn.dsxriiiii.l3x.infrastructure.gateway.ProductRPC;
import cn.dsxriiiii.l3x.infrastructure.gateway.dto.ProductDTO;
import org.springframework.stereotype.Component;

/**
 * @PackageName: cn.dsxriiiii.l3x.infrastructure.adapter.port
 * @Author: DSXRIIIII
 * @Email: 1870066109@qq.com
 * @Date: Created in  2024/11/26 15:12
 * @Description:
 **/
@Component
public class ProductPort implements IProductPort {

    private final ProductRPC productRPC;

    public ProductPort(ProductRPC productRPC){
        this.productRPC = productRPC;
    }
    @Override
    public ProductEntity queryProductByProductId(String productId) {
        ProductDTO productDTO = productRPC.queryProductByProductId(productId);
        return ProductEntity.builder()
                .productId(productDTO.getProductId())
                .productName(productDTO.getProductName())
                .productDesc(productDTO.getProductDesc())
                .price(productDTO.getPrice())
                .build();
    }
}
