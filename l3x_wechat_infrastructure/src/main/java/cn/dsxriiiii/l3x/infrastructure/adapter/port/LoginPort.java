package cn.dsxriiiii.l3x.infrastructure.adapter.port;


import cn.dsxriiiii.l3x.domain.auth.adapter.port.ILoginPort;
import cn.dsxriiiii.l3x.infrastructure.gateway.IWeChatApiService;
import cn.dsxriiiii.l3x.infrastructure.gateway.dto.WeChatQrCodeRequestDTO;
import cn.dsxriiiii.l3x.infrastructure.gateway.dto.WeChatQrCodeResponseDTO;
import cn.dsxriiiii.l3x.infrastructure.gateway.dto.WeChatTemplateMessageDTO;
import cn.dsxriiiii.l3x.infrastructure.gateway.dto.WeChatTokenResponseDTO;
import com.github.benmanes.caffeine.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Call;


import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @PackageName: cn.dsxriiiii.l3x.domain.auth.adapter.port
 * @Author: DSXRIIIII
 * @Email: 1870066109@qq.com
 * @Date: Created in  2024/11/23 16:36
 * @Description:
 **/
@Service
@Slf4j
public class LoginPort implements ILoginPort {

    @Value("${wechat.config.app-id}")
    private String appid;
    @Value("${wechat.config.app-secret}")
    private String appSecret;
    @Value("${wechat.config.template_id}")
    private String template_id;

    @Resource
    private Cache<String, String> wechatAccessToken;

    @Resource
    private IWeChatApiService weChatApiService;

    private String getToken() throws IOException {
        // 获取token
        String accessToken = wechatAccessToken.getIfPresent(appid);
        if(accessToken == null){
            Call<WeChatTokenResponseDTO> call = weChatApiService.getToken("client_credential",appid,appSecret);
            WeChatTokenResponseDTO wechatRes = call.execute().body();
            assert wechatRes != null;
            accessToken = wechatRes.getAccess_token();
            wechatAccessToken.put(appid,accessToken);
        }
        log.info("Wechat get token success,token message: {}",accessToken);
        return accessToken;
    }

    @Override
    public String createQrCodeTicket() throws IOException {
        // 获取token
        String accessToken = getToken();
        //生成ticket
        WeChatQrCodeRequestDTO weChatQrCodeRequestDTO = WeChatQrCodeRequestDTO.builder()
                .expire_seconds(2592000)
                .action_name(WeChatQrCodeRequestDTO.ActionNameTypeVO.QR_SCENE.getCode())
                .action_info(WeChatQrCodeRequestDTO.ActionInfo.builder()
                        .scene(WeChatQrCodeRequestDTO.ActionInfo.Scene.builder()
                                .scene_id(100601)
                                .build())
                        .build())
                .build();
        Call<WeChatQrCodeResponseDTO> call  = weChatApiService.createQrCode(accessToken,weChatQrCodeRequestDTO);
        WeChatQrCodeResponseDTO wechatQrCodeRes = call.execute().body();
        assert null != wechatQrCodeRes;
        return wechatQrCodeRes.getTicket();
    }

    @Override
    public void sendLoginTemplate(String openid) throws IOException {
        // 获取token
        String accessToken = getToken();

        // 发送模板消息
        Map<String, Map<String,String>> data = new HashMap<>();
        WeChatTemplateMessageDTO.put(data, WeChatTemplateMessageDTO.TemplateKey.USER, openid);

        WeChatTemplateMessageDTO templateMessageDTO = new WeChatTemplateMessageDTO(openid, template_id);
        templateMessageDTO.setUrl("https://dsxriiiii.cn");
        templateMessageDTO.setData(data);

        Call<Void> call = weChatApiService.sendMessage(accessToken, templateMessageDTO);
        call.execute();
        log.info("模板消息发送成功");
    }
}
