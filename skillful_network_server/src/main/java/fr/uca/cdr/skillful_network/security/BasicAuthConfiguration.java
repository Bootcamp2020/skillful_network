package fr.uca.cdr.skillful_network.security;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Properties;

@Configuration
@EnableWebSecurity
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
public class BasicAuthConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        // on genère un InMemoryUserDetailsManager pour pouvoir enregistré une nouvelle paire
        // utilisateur:code_temporaire à chaque nouvelle identification
        final Properties users = new Properties();
        users.put("user","pass,USER,enabled"); // un utilisateur est enregistré par défault pour le développement.
        return new InMemoryUserDetailsManager(users);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(inMemoryUserDetailsManager());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable()
          .authorizeRequests()
          .antMatchers(HttpMethod.OPTIONS, "/**")
                .permitAll()
          .antMatchers("/home", "/login", "/token", "/login/v1" , "/register","/upload","/skills/*" , "/qualifications/*","/subscriptions/*") // les pages/requêtes /home, /login et /token sont accessibles sans authentifications (pour pouvoir s'identifier).
                .permitAll()
          .anyRequest()
          .authenticated() // toutes les qutess pages/requêtes nécessite une authentification pour pouvoir y accéder.
          .and()
          .httpBasic();
    }
}
