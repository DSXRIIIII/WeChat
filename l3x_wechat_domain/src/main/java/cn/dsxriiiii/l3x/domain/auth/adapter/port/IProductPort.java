package cn.dsxriiiii.l3x.domain.auth.adapter.port;

import cn.dsxriiiii.l3x.domain.auth.model.entity.ProductEntity;

/**
 * @PackageName: cn.dsxriiiii.l3x.domain.auth.adapter.repository
 * @Author: DSXRIIIII
 * @Email: 1870066109@qq.com
 * @Date: Created in  2024/11/26 15:22
 * @Description:
 **/
public interface IProductPort {
    ProductEntity queryProductByProductId(String productId);
}
