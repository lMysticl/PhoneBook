package com.mystic.config;

import com.mystic.security.auth.RestAuthenticationEntryPoint;
import com.mystic.service.impl.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by nydiarra on 06/05/17.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${security.signing-key}")
	private String signingKey;

	@Value("${security.encoding-strength}")
	private Integer encodingStrength;

	@Value("${security.security-realm}")
	private String securityRealm;

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
		        .passwordEncoder(new ShaPasswordEncoder(encodingStrength));
	}



	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll()
				.antMatchers("/api/**").authenticated()
				.anyRequest().permitAll()
				.and().httpBasic()
				.realmName(securityRealm)
				.authenticationEntryPoint(apiAwareLoginUrlAuthenticationEntryPoint())
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}


	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(signingKey);
		return converter;
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	@Primary
    //Making this primary to avoid any accidental duplication with another token service instance of the same name
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		defaultTokenServices.setSupportRefreshToken(true);
		return defaultTokenServices;
	}





	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Autowired
	private AppUserDetailsService appUserDetailsService;

	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Autowired
	public void configureGlobal( AuthenticationManagerBuilder auth ) throws Exception {
		auth.userDetailsService( appUserDetailsService )
				.passwordEncoder( passwordEncoder() );
	}







	@Autowired
	private PasswordEncoder passwordEncoder;





	@Bean
	public ApiBasicAuthenticationEntryPoint apiAwareLoginUrlAuthenticationEntryPoint() {
		ApiBasicAuthenticationEntryPoint entryPoint = new ApiBasicAuthenticationEntryPoint();
		entryPoint.setRealmName("Api Server");
		return entryPoint;
	}

	public static class ApiBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

		@Override
		public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
			response.addHeader("WWW-Authenticate", "Basic realm=\"" + getRealmName() + "\"");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			//response.setContentType("");
//			PrintWriter writer = response.getWriter();
//			ObjectMapper mapper = new ObjectMapper();
//			mapper.writeValue(writer, ApiDataGenerator.buildResult(
//					ErrorCode.AUTHORIZATION_REQUIRED, "Authorization failed"));
		}



	}









	@Override
	public void configure(WebSecurity web) {
		// TokenAuthenticationFilter will ignore the below paths
		web.ignoring().antMatchers(
				HttpMethod.POST,
				"/auth/login"
		);
		web.ignoring().antMatchers(
				HttpMethod.GET,
				"/",
				"/webjars/**",
				"/*.html",
				"/favicon.ico",
				"/**/*.html",
				"/**/*.css",
				"/**/*.js"
		);

	}
}
