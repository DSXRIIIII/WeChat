package cn.dsxriiiii.l3x.infrastructure.dao;

import cn.dsxriiiii.l3x.infrastructure.dao.po.PayOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * @PackageName: cn.dsxriiiii.l3x.infrastructure.dao
 * @Author: DSXRIIIII
 * @Email: 1870066109@qq.com
 * @Date: Created in  2024/11/26 15:13
 * @Description: Order订单对应Mapper映射
 **/
@Mapper
public interface IOrderDao {

    void insert(PayOrder payOrder);

    PayOrder queryUnPayOrder(PayOrder payOrder);

}
