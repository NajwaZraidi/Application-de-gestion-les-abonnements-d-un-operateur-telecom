package ma.enset.contole.security;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//classe de configuration
@Configuration
//activation de security web
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig {

        private PasswordEncoder passwordEncoder;

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        //noop=> no password encoder
        return new InMemoryUserDetailsManager(
                User.withUsername("Najwa").password(passwordEncoder.encode("Awjan1234")).roles("USER").build(),
                User.withUsername("Hania").password(passwordEncoder.encode("Islam2014")).roles("USER").build(),
                User.withUsername("admin").password(passwordEncoder.encode("AwjanOE")).roles("ADMIN","USER").build()
                );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
       //httpSecurity.formLogin();
        httpSecurity.formLogin().loginPage("/login").permitAll();
        //httpSecurity.rememberMe();
        // httpSecurity.authorizeHttpRequests().requestMatchers("webjars/**","h2-console/*").permitAll();
       //Toutes les requetes necessite une authentification
        //httpSecurity.authorizeHttpRequests().requestMatchers("/user/**").hasRole("USER");
        //httpSecurity.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN");
        httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
        httpSecurity.exceptionHandling().accessDeniedPage("/notAuthorized");
        return httpSecurity.build();
    }

}