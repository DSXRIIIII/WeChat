package cn.dsxriiiii.l3x.config;

import cn.dsxriiiii.l3x.infrastructure.gateway.IWeChatApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


/**
 * @PackageName: cn.dsxriiiii.l3x.config
 * @Author: DSXRIIIII
 * @Email: 1870066109@qq.com
 * @Date: Created in  2024/11/23 15:49
 * @Description:
 **/
@Slf4j
@Configuration
public class Retrofit2Config {

    private static final String BASE_URL = "https://api.weixin.qq.com/";

    @Bean
    public Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create()).build();
    }

    @Bean
    public IWeChatApiService wechatApiService(Retrofit retrofit) {
        return retrofit.create(IWeChatApiService.class);
    }

}
