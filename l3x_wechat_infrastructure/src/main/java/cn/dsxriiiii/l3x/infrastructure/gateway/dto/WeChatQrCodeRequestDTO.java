package cn.dsxriiiii.l3x.infrastructure.gateway.dto;

import lombok.*;

/**
 * @PackageName: cn.dsxriiiii.l3x.trigger.gateway.dto
 * @Author: DSXRIIIII
 * @Email: 1870066109@qq.com
 * @Date: Created in  2024/11/23 15:58
 * @Description: 获取微信登录二维码请求对象
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeChatQrCodeRequestDTO {

    private int expire_seconds;
    private String action_name;
    private ActionInfo action_info;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ActionInfo {
        Scene scene;

        @Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Scene {
            int scene_id;
            String scene_str;
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public enum ActionNameTypeVO {
        QR_SCENE("QR_SCENE", "临时的整型参数值"),
        QR_STR_SCENE("QR_STR_SCENE", "临时的字符串参数值"),
        QR_LIMIT_SCENE("QR_LIMIT_SCENE", "永久的整型参数值"),
        QR_LIMIT_STR_SCENE("QR_LIMIT_STR_SCENE", "永久的字符串参数值");

        private String code;
        private String info;
    }

}

