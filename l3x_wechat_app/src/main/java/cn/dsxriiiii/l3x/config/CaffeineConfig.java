package cn.dsxriiiii.l3x.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @PackageName: cn.dsxriiiii.l3x.app.config
 * @Author: DSXRIIIII
 * @Email: 1870066109@qq.com
 * @Date: Created in  2024/11/23 14:44
 * @Description: caffeine cache init
 **/
@Configuration
public class CaffeineConfig {
    @Bean(name = "wechatAccessToken")
    public Cache<String,String> wechatAccessToken() {
        return Caffeine.newBuilder().expireAfterWrite(2, TimeUnit.HOURS).build();
    }

    @Bean(name = "openidToken")
    public Cache<String,String> openidToken() {
        return Caffeine.newBuilder().expireAfterWrite(1, TimeUnit.HOURS).build();
    }

}
