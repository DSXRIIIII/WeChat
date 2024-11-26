微信扫码流程是怎么实现的？

## 1.内网穿透
将本地端口暴露到公网，或者用可访问的域名链接进行访问也是可以的

作者采用NetApp进行内网穿透工作，暴露本地8091端口到公网对外服务

## 2.验证服务器
### 2.1 首先需要`yaml`文件配置`wechat`对应的属性
![](https://cdn.nlark.com/yuque/0/2024/png/39213833/1732359633663-4b77fc70-0902-4eb9-9c89-bb0c34103560.png)

### 2.2 需要创建工具类，用作`XML`和`BEAN`的转换工作
![](https://cdn.nlark.com/yuque/0/2024/png/39213833/1732359965688-7f4f6f95-804d-47ec-99e2-62fb0f534991.png)

### 2.3 定义`MessageTextEntity`相当于传输文件内容的标签语句
![](https://cdn.nlark.com/yuque/0/2024/png/39213833/1732360093037-cd0d388a-5df8-407e-b6ad-3e63e2ba4294.png)

### 2.4 定义`SignatureUtil`工具类用于对`token`的检查
![](https://cdn.nlark.com/yuque/0/2024/png/39213833/1732360159383-a012c3ac-b923-497a-b040-cbdd134d4669.png)



### 2.5 定义用于验证的Controller
```java
@GetMapping(value = "receive", produces = "text/plain;charset=utf-8")
public String validate(@RequestParam(value = "signature", required = false) String signature,
                       @RequestParam(value = "timestamp", required = false) String timestamp,
                       @RequestParam(value = "nonce", required = false) String nonce,
                       @RequestParam(value = "echostr", required = false) String echostr) {
    try {
        log.info("微信公众号验签信息开始 [{}, {}, {}, {}]", signature, timestamp, nonce, echostr);
        if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
            throw new IllegalArgumentException("请求参数非法，请核实!");
        }
        boolean check = SignatureUtil.check(token, signature, timestamp, nonce);
        log.info("微信公众号验签信息完成 check：{}", check);
        if (!check) {
            return null;
        }
        return echostr;
    } catch (Exception e) {
        log.error("微信公众号验签信息失败 [{}, {}, {}, {}]", signature, timestamp, nonce, echostr, e);
        return null;
    }
}

```

### 2.6 调用成功
通过内网穿透将数据信息返回到本地服务端口，此时公众号服务器绑定本地8091的服务，可以查看到配置成功的绿色窗口，并且服务器已绑定

![](https://cdn.nlark.com/yuque/0/2024/png/39213833/1732360411634-0dcd54be-1354-4972-9bed-9bffa32b87e7.png)

## 3.微信登录如何实现
### 3.1 流程分析图
![](https://cdn.nlark.com/yuque/0/2024/png/39213833/1732374099278-8ffce9bf-1e06-4d1d-8c62-fddaa770f583.png)

### 3.2 具体流程分析
1. `User`用户发起登录请求，`Server`服务层向微信平台发起登录请求获取`accessToken`
2. 根据`accessToken`请求二维码的`ticket`
3. 获取二维码的图片信息，用户扫码登录
4. `Web`服务不断轮询发起是否登录的请求
5. 根据用户是否登录返回成功与否的结果值，并且返回认证的token

### 3.3 代码实现
#### 3.3.1登录服务接口
```java
public interface ILoginService {
    //创建tiket
    String createQrCodeTicket() throws Exception;
    //轮询是否登录接口
    String checkLogin(String ticket);
    //保存当前登录状态，这里选择本地缓存caffeine
    void saveLoginState(String ticket, String openid) throws IOException;
}
```

#### 3.3.2 http调用微信开放平台接口
```java
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

```

### 3.4 测试结果
#### 3.4.1获取ticket接口测试结果
![](https://cdn.nlark.com/yuque/0/2024/png/39213833/1732375627007-82f4a16d-4d79-4b71-b2cf-0ba2e9781ec5.png)

#### 3.4.2 二维码生成接口
![](https://cdn.nlark.com/yuque/0/2024/png/39213833/1732375697324-bf64f24a-bf1b-4c23-9a21-7fb7b7f8cbeb.png)


