package com.kevin.springboot.configuration;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

//@Configuration
public class AliyunConfiguration implements EnvironmentAware {

    private static Logger log = LoggerFactory.getLogger(AliyunConfiguration.class);

    private AliyunProperties properties;

    @Bean
    public AliyunProperties aliyunproperties() {
        return properties;
    }

    @Bean
    public OSSClient ossclient() {
        ClientConfiguration conf = new ClientConfiguration();
        // 设置HTTP最大连接数为10
        conf.setMaxConnections(1024);
        // 设置TCP连接超时为5000毫秒
        conf.setConnectionTimeout(5000);
        // 设置最大的重试次数为3
        conf.setMaxErrorRetry(3);
        // 设置Socket传输数据超时的时间为2000毫秒
        conf.setSocketTimeout(3000);

        log.info("初始化阿里云oss连接,endpoint - {},accessKeyId - {},accessKeySecret - {}", properties.getOssendpoint(), properties.getAccessKeyId(), properties.getAccessKeySecret());

        return new OSSClient(properties.getOssendpoint(), properties.getAccessKeyId(), properties.getAccessKeySecret(), conf);
    }

    @Bean
    public DefaultAcsClient defaultAcsClient() {
        return new DefaultAcsClient(DefaultProfile.getProfile(properties.getMts_region(), properties.getAccessKeyId(), properties.getAccessKeySecret()));

    }

    /**
     * 阿里云配置赋值
     * @param env 环境
     */
    @Override
    public void setEnvironment(Environment env) {
        properties = new AliyunProperties();
        properties.setAccessKeyId(env.getProperty("aliyun.accesskey.id"));
        properties.setAccessKeySecret(env.getProperty("aliyun.accesskey.secret"));
        properties.setOssendpoint(env.getProperty("aliyun.oss.endpoint"));
        properties.setOssdomain(env.getProperty("aliyun.oss.domain"));
        properties.setOssbucket(env.getProperty("aliyun.oss.bucket"));
        properties.setMts_region(env.getProperty("aliyun.mts_region"));
    }

}

