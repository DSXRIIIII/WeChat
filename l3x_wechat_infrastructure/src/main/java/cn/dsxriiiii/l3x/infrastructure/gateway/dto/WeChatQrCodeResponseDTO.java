package cn.dsxriiiii.l3x.infrastructure.gateway.dto;

import lombok.Data;

/**
 * @PackageName: cn.dsxriiiii.l3x.trigger.gateway.dto
 * @Author: DSXRIIIII
 * @Email: 1870066109@qq.com
 * @Date: Created in  2024/11/23 15:59
 * @Description: 获取微信登录二维码响应对象
 **/
@Data
public class WeChatQrCodeResponseDTO {

    private String ticket;
    private Long expire_seconds;
    private String url;
    private String errcode;
    private String errmsg;
}

