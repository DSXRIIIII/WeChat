package cn.dsxriiiii.l3x.trigger.http;

import cn.dsxriiiii.l3x.api.IAuthService;
import cn.dsxriiiii.l3x.api.response.Response;
import cn.dsxriiiii.l3x.domain.auth.service.ILoginService;
import cn.dsxriiiii.l3x.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @PackageName: cn.dsxriiiii.l3x.trigger.http
 * @Author: DSXRIIIII
 * @Email: 1870066109@qq.com
 * @Date: Created in  2024/11/23 16:23
 * @Description: 登录接口
 **/
@CrossOrigin("*")
@Slf4j
@RestController()
@RequestMapping("/api/v1/login/")
public class LoginController implements IAuthService {

    @Resource
    private ILoginService loginService;

    /**
     * <a href="http://natapp1.cc/api/v1/login/wechat_qrcode_ticket">...</a>
     * @return Response
     */
    @RequestMapping(value = "wechat_qrcode_ticket", method = RequestMethod.GET)
    @Override
    public Response<String> wechatQrCodeTicket() {
        try {
            String qrCodeTicket = loginService.createQrCodeTicket();
            log.info("生成微信扫码登录 ticket:{}", qrCodeTicket);
            return Response.<String>builder()
                    .code(Constants.ResponseCode.SUCCESS.getCode())
                    .info(Constants.ResponseCode.SUCCESS.getInfo())
                    .data(qrCodeTicket)
                    .build();
        } catch (Exception e) {
            log.error("生成微信扫码登录 ticket 失败", e);
            return Response.<String>builder()
                    .code(Constants.ResponseCode.UN_ERROR.getCode())
                    .info(Constants.ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }

    /**
     * <a href="http://natapp1.cc/api/v1/login/check_login">...</a>
     */
    @RequestMapping(value = "check_login", method = RequestMethod.GET)
    @Override
    public Response<String> checkLogin(String ticket) {
        try {
            String openidToken = loginService.checkLogin(ticket);
            log.info("扫码检测登录结果 ticket:{} openidToken:{}", ticket, openidToken);
            if (StringUtils.isNotBlank(openidToken)) {
                return Response.<String>builder()
                        .code(Constants.ResponseCode.SUCCESS.getCode())
                        .info(Constants.ResponseCode.SUCCESS.getInfo())
                        .data(openidToken)
                        .build();
            } else {
                return Response.<String>builder()
                        .code(Constants.ResponseCode.NO_LOGIN.getCode())
                        .info(Constants.ResponseCode.NO_LOGIN.getInfo())
                        .build();
            }
        } catch (Exception e) {
            log.error("扫码检测登录结果失败 ticket:{}", ticket, e);
            return Response.<String>builder()
                    .code(Constants.ResponseCode.UN_ERROR.getCode())
                    .info(Constants.ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }




}
