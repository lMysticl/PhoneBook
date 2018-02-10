package com.mystic.config;

import com.mystic.security.TokenHelper;
import com.mystic.security.auth.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    private ResourceServerTokenServices tokenServices;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Value("${security.jwt.resource-ids}")
    private String resourceIds;

    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    TokenHelper tokenHelper;
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(resourceIds).tokenServices(tokenServices);
    }



//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        List<RequestMatcher> csrfMethods = new ArrayList<>();
//        Arrays.asList( "POST", "PUT", "PATCH", "DELETE" )
//                .forEach( method -> csrfMethods.add( new AntPathRequestMatcher( "/**", method ) ) );
//        http
//                .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS ).and()
//                .exceptionHandling().authenticationEntryPoint( restAuthenticationEntryPoint ).and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll()
//                .antMatchers(
//                        HttpMethod.GET,
//                        "/",
//                        "/webjars/**",
//                        "/*.html",
//                        "/favicon.ico",
//                        "/**/*.html",
//                        "/**/*.css",
//                        "/**/*.js"
//                ).permitAll()
//                .antMatchers("/auth/**").permitAll()
//                .anyRequest().authenticated().and()
//                .addFilterBefore(new TokenAuthenticationFilter(tokenHelper, jwtUserDetailsService), BasicAuthenticationFilter.class);
//
//        http.csrf().disable();
//    }
}
