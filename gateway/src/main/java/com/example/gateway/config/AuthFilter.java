package com.example.gateway.config;

import com.example.gateway.client.security.client.SecurityClient;
import com.example.gateway.client.security.exception.AuthException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

  private static final String MISSING_AUTH = "Missing auth information";
  private static final String INCORRECT_AUTH_STRUCTURE = "Incorrect auth structure";

  @Autowired
  ObjectProvider<SecurityClient> securityClient;

  public AuthFilter() {
    super(Config.class);
  }

  @Override
  public GatewayFilter apply(Config config) {
    //Auth Pre Filter. Suppose we can extract JWT and perform Authentication
    return (exchange, chain) -> {
      if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
        throw new AuthException(MISSING_AUTH);
      }

      String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

      String [] parts = authHeader.split(" ");

      if (parts.length != 2 || !"Bearer".equals(parts[0])) {
        throw new AuthException(INCORRECT_AUTH_STRUCTURE);
      }

      try {
        securityClient.getObject().validate(authHeader);
      } catch (Exception e) {
        throw new AuthException(e.getMessage());
      }
      return chain.filter(exchange);
    };
  }

  public static class Config {

  }
}
