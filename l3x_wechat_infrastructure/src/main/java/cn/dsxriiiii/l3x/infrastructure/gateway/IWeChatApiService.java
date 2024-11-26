package cn.dsxriiiii.l3x.infrastructure.gateway;

import cn.dsxriiiii.l3x.infrastructure.gateway.dto.WeChatQrCodeRequestDTO;
import cn.dsxriiiii.l3x.infrastructure.gateway.dto.WeChatQrCodeResponseDTO;
import cn.dsxriiiii.l3x.infrastructure.gateway.dto.WeChatTemplateMessageDTO;
import cn.dsxriiiii.l3x.infrastructure.gateway.dto.WeChatTokenResponseDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @PackageName: cn.dsxriiiii.l3x.trigger.gateway
 * @Author: DSXRIIIII
 * @Email: 1870066109@qq.com
 * @Date: Created in  2024/11/23 15:53
 * @Description: 微信登录API接口
 **/
public interface IWeChatApiService {
    /**
     * 获取 Access token
     * 文档：<a href="https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Get_access_token.html">Get_access_token</a>
     *
     * @param grantType 获取access_token填写client_credential
     * @param appId     第三方用户唯一凭证
     * @param appSecret 第三方用户唯一凭证密钥，即appsecret
     * @return 响应结果
     */
    @GET("cgi-bin/token")
    Call<WeChatTokenResponseDTO> getToken(@Query("grant_type") String grantType,
                                          @Query("appid") String appId,
                                          @Query("secret") String appSecret);


    /**
     * 获取凭据 ticket
     * 文档：<a href="https://developers.weixin.qq.com/doc/offiaccount/Account_Management/Generating_a_Parametric_QR_Code.html">Generating_a_Parametric_QR_Code</a>
     * <a href="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET">前端根据凭证展示二维码</a>
     *
     * @param accessToken            getToken 获取的 token 信息
     * @param wechatQrCodeRequestDTO 入参对象
     * @return 应答结果
     */
    @POST("cgi-bin/qrcode/create")
    Call<WeChatQrCodeResponseDTO> createQrCode(@Query("access_token") String accessToken, @Body WeChatQrCodeRequestDTO wechatQrCodeRequestDTO);

    /**
     * 发送微信公众号模板消息
     * 文档：<a href="https://mp.weixin.qq.com/debug/cgi-bin/readtmpl?t=tmplmsg/faq_tmpl">...</a>
     *
     * @param accessToken              getToken 获取的 token 信息
     * @param weixinTemplateMessageDTO 入参对象
     * @return 应答结果
     */
    @POST("cgi-bin/message/template/send")
    Call<Void> sendMessage(@Query("access_token") String accessToken, @Body WeChatTemplateMessageDTO weixinTemplateMessageDTO);

}
