package cn.dsxriiiii.l3x.domain.auth.service;

import cn.dsxriiiii.l3x.domain.auth.adapter.port.ILoginPort;
import com.github.benmanes.caffeine.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @PackageName: cn.dsxriiiii.l3x.domain.auth.service
 * @Author: DSXRIIIII
 * @Email: 1870066109@qq.com
 * @Date: Created in  2024/11/23 16:15
 * @Description: 微信登录实现类
 **/
@Slf4j
@Service
public class WeChatLoginService implements ILoginService{

    @Resource
    private ILoginPort loginPort;
    @Resource
    private Cache<String, String> openidToken;


    @Override
    public String createQrCodeTicket() throws Exception {
        return loginPort.createQrCodeTicket();
    }

    @Override
    public String checkLogin(String ticket) {
        return openidToken.getIfPresent(ticket);
    }

    @Override
    public void saveLoginState(String ticket, String openid) throws IOException {
        // 保存登录信息
        openidToken.put(ticket, openid);
        // 发送模板消息
        loginPort.sendLoginTemplate(openid);
    }
}
