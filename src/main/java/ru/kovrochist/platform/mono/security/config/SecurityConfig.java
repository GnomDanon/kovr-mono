package ru.kovrochist.platform.mono.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import ru.kovrochist.platform.mono.api.AuthApi;
import ru.kovrochist.platform.mono.security.code.CodeGenerator;
import ru.kovrochist.platform.mono.security.code.MockCodeGenerator;
import ru.kovrochist.platform.mono.security.jwt.JwtFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private static final int PASSWORD_ENCODER_STRENGTH = 4;

	private static final String SWAGGER_UI_ENTRY_POINT = "/swagger-ui/**";
	private static final String SWAGGER_RESOURCES_ENTRY_POINT = "/swagger-resources/*";
	private static final String API_DOCS_ENTRY_POINT = "/v3/api-docs/**";
	private static final String LOGIN_ENTRY_POINT = AuthApi.AUTH_PATH + AuthApi.LOGIN_ENTRY_POINT;
	private static final String CONFIRM_ENTRY_POINT = AuthApi.AUTH_PATH + AuthApi.CONFIRM_ENTRY_POINT;
	private static final String REFRESH_ENTRY_POINT = AuthApi.AUTH_PATH + AuthApi.REFRESH_ENTRY_POINT;

	private final JwtFilter jwtFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.httpBasic(AbstractHttpConfigurer::disable)
				.csrf(AbstractHttpConfigurer::disable)
				.cors(cors -> cors.configurationSource(request -> {
					CorsConfiguration corsConfiguration = new CorsConfiguration();
					corsConfiguration.setAllowedOriginPatterns(List.of("*"));
					corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
					corsConfiguration.setAllowedHeaders(List.of("*"));
					corsConfiguration.setAllowCredentials(true);
					return corsConfiguration;
				}))
				.authorizeHttpRequests(auth -> auth
						.requestMatchers(SWAGGER_UI_ENTRY_POINT, SWAGGER_RESOURCES_ENTRY_POINT, API_DOCS_ENTRY_POINT, LOGIN_ENTRY_POINT, CONFIRM_ENTRY_POINT, REFRESH_ENTRY_POINT).permitAll()
						.anyRequest().authenticated()
				)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder(PASSWORD_ENCODER_STRENGTH);
	}

	@Bean
	public CodeGenerator getCodeGenerator() {
		return new MockCodeGenerator();
	}
}
