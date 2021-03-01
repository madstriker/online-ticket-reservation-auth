package com.cotivity.ticket_reservation_authorization_server.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Data
@Configuration
@PropertySource("classpath:keystore.properties")
@ConfigurationProperties("server")
@Service
public class SecurityProperties {

    private SSLProperties ppl;


    @Data
    public static class SSLProperties{

        private Resource keyStore;
        private String keyStorePassword;
        private String keyAlias;
    }
}
