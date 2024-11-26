package cn.dsxriiiii.l3x.api;

import cn.dsxriiiii.l3x.api.response.Response;

/**
 * @PackageName: cn.dsxriiiii.l3x.pay.api
 * @Author: DSXRIIIII
 * @Email: 1870066109@qq.com
 * @Date: Created in  2024/11/23 15:48
 * @Description: 登录接口实现
 **/
public interface IAuthService {
    Response<String> wechatQrCodeTicket();

    Response<String> checkLogin(String ticket);

}
