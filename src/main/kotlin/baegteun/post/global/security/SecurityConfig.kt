package baegteun.post.global.security

import baegteun.post.global.security.filter.ExceptionFilter
import baegteun.post.global.security.filter.JwtTokenFilter
import baegteun.post.global.security.jwt.JwtTokenProvider
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsUtils

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtTokenProvider: JwtTokenProvider,
    private val objectMapper: ObjectMapper
) {
    @Bean
    fun passwordEncoder(): PasswordEncoder? = BCryptPasswordEncoder()

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf().disable()
            .formLogin().disable()
            .cors().disable()

            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            .and()
            .authorizeRequests()
            .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()

            // auth
            .antMatchers(HttpMethod.POST, "/auth/signup").permitAll()
            .antMatchers(HttpMethod.POST, "/auth/signin").permitAll()
            .antMatchers(HttpMethod.HEAD, "/auth/valid-id").permitAll()
            .antMatchers(HttpMethod.HEAD, "/auth/valid-name").permitAll()
            .antMatchers(HttpMethod.PUT, "/auth").permitAll()

            // feed
            .antMatchers(HttpMethod.GET, "/feed/list").permitAll()
            .antMatchers(HttpMethod.GET, "/feed/{id}").authenticated()

            // image
            .antMatchers(HttpMethod.POST, "/image").authenticated()

            .anyRequest().denyAll()

            .and()
            .exceptionHandling()
            .authenticationEntryPoint(CustomAuthenticationEntryPoint(objectMapper))

            .and()
            .addFilterBefore(ExceptionFilter(objectMapper), UsernamePasswordAuthenticationFilter::class.java)
            .addFilterAfter(JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }
}