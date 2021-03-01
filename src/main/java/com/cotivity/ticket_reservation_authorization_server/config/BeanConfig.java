package com.cotivity.ticket_reservation_authorization_server.config;

import com.cotivity.ticket_reservation_authorization_server.props.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;

@Configuration
public class BeanConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public JwtAccessTokenConverter accessTokenConverter(SecurityProperties securityProperties) {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(keyPair(securityProperties.getPpl()));
        return jwtAccessTokenConverter;
    }

    private KeyPair keyPair(SecurityProperties.SSLProperties sslProperties) {
        KeyStoreKeyFactory keyStoreKeyFactory = keyStoreKeyFactory(sslProperties);
        return keyStoreKeyFactory.getKeyPair(sslProperties.getKeyAlias(), sslProperties.getKeyStorePassword().toCharArray());

    }

    private KeyStoreKeyFactory keyStoreKeyFactory(SecurityProperties.SSLProperties sslProperties){
        return new KeyStoreKeyFactory(sslProperties.getKeyStore(), sslProperties.getKeyStorePassword().toCharArray());
    }

}
