package cn.dsxriiiii.l3x.domain.auth.service;

import java.io.IOException;

/**
 * @PackageName: cn.dsxriiiii.l3x.domain.auth.service
 * @Author: DSXRIIIII
 * @Email: 1870066109@qq.com
 * @Date: Created in  2024/11/23 16:15
 * @Description: 登录服务
 **/
public interface ILoginService {
    String createQrCodeTicket() throws Exception;

    String checkLogin(String ticket);

    void saveLoginState(String ticket, String openid) throws IOException;

}
