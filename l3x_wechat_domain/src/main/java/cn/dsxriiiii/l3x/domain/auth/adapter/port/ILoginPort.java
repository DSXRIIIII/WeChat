package cn.dsxriiiii.l3x.domain.auth.adapter.port;

import java.io.IOException;

/**
 * @PackageName: cn.dsxriiiii.l3x.domain.auth.adapter.port
 * @Author: DSXRIIIII
 * @Email: 1870066109@qq.com
 * @Date: Created in  2024/11/23 16:14
 * @Description:
 **/
public interface ILoginPort {

    String createQrCodeTicket() throws IOException;

    void sendLoginTemplate(String openid) throws IOException;

}