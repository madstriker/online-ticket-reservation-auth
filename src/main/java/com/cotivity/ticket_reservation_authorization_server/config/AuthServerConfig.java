package com.cotivity.ticket_reservation_authorization_server.config;

import com.cotivity.ticket_reservation_authorization_server.model.CustomUserDetail;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final DataSource dataSource;
    private final JwtAccessTokenConverter accessTokenConverter;

    public AuthServerConfig(PasswordEncoder passwordEncoder, DataSource dataSource, JwtAccessTokenConverter accessTokenConverter, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.dataSource = dataSource;
        this.accessTokenConverter = accessTokenConverter;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security)  {
        security.passwordEncoder(passwordEncoder);
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain chain = new TokenEnhancerChain();
        chain.setTokenEnhancers(List.of(tokenEnhancer(), accessTokenConverter));
        endpoints.tokenEnhancer(chain).authenticationManager(authenticationManager).exceptionTranslator(new MyWebResponseExceptionTranslator());
    }

    private TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetail) {
                CustomUserDetail authUser = (CustomUserDetail) authentication.getPrincipal();
                Map<String, Object> additionalInfo = new HashMap<>();
                additionalInfo.put("user_id", authUser.getUser().getId());
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            }
            return accessToken;
        };
    }
}
