package com.tscode.shop_clothes.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {
//    khai báo các đường dẫn để có thể dùng chung
//    private final String[] PUBLIC_URLS_GET = {"/login", "/product", "/home"};
//    private final String[] PUBLIC_URLS_POST = {"/login", "/product"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request -> request
                        .requestMatchers("/js/**","/home","/css/**", "/image/**", "/product/**", "/register").permitAll()
                        .requestMatchers("/cart/**").authenticated()
                        .requestMatchers("/dashboard/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .passwordParameter("password")
                        .usernameParameter("username")
                        .defaultSuccessUrl("/home", false)  /*fasle sẽ không quay lại trang trước nếu k có*/
                        .failureUrl("/login?login_failure")
                        .permitAll())
                .httpBasic(Customizer.withDefaults())
                /*gọi phương thức rememberMe có sẵn */
                .rememberMe(rememberMe -> rememberMe
                        .rememberMeParameter("remember-me")  /*đây là name của nút remember*/
                        .key("uniqueAndSecret")
                        .tokenValiditySeconds(86400))
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"))/*xóa cookie phiên tự động được tạo ra của web*/
                .exceptionHandling(ex -> ex.accessDeniedPage("/UnAuthorized"));
//                .exceptionHandling(ex -> ex.authenticationEntryPoint(new CustomAuthenticationEntryPoint())); // Thêm dòng này;
        http.csrf(AbstractHttpConfigurer::disable);/*đang tắt csrf*/
        http.cors(AbstractHttpConfigurer::disable);/*đang tắt cosd*/

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }


}