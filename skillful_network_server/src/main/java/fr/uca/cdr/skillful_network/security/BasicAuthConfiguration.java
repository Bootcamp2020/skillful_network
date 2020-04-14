package fr.uca.cdr.skillful_network.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import fr.uca.cdr.skillful_network.jwt.JwtAuthEntryPoint;
import fr.uca.cdr.skillful_network.jwt.JwtAuthTokenFilter;
import fr.uca.cdr.skillful_network.security.services.UserDetailsServiceImpl;

@Configuration
public class BasicAuthConfiguration {

	// ###########################################################################
	// WebSecurityConfigurerAdapter boolean for HTTP Pattern Matcher Security enabled or disabled
	// application.properties file :
	// # enabled :
	// api.security.httpPatternMatcher.disabled=false (or empty/null)
	// # disabled :
	// api.security.httpPatternMatcher.disabled=true
	// ###########################################################################

	@Value("${api.security.httpPatternMatcher.disabled:false}")
	private boolean httpPatternMatcherDisabled;

	@Autowired
    UserDetailsServiceImpl userDetailsService;

	@Autowired
    private JwtAuthEntryPoint unauthorizedHandler;


    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//	@Bean
//	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//		// on genère un InMemoryUserDetailsManager pour pouvoir enregistré une nouvelle
//		// paire
//		// utilisateur:code_temporaire à chaque nouvelle identification
//		final Properties users = new Properties();
//		users.put("user", "pass,USER,enabled"); // un utilisateur est enregistré par défault pour le développement.
//		return new InMemoryUserDetailsManager(users);
//	}

	// ###########################################################################
	// WebSecurityConfigurerAdapter bean with Role Authentication Security enabled
	// ###########################################################################

	@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
    @EnableWebSecurity
    @Profile("!roleAuthSecurityDisabled")
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    public class RoleSecurityEnabledConfig extends WebSecurityConfigurerAdapter {

		@Bean
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        }
		@Override
        protected void configure(HttpSecurity http) throws Exception {
			http.cors().and().csrf().disable()
					.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll();
			if ( ! httpPatternMatcherDisabled) { // http pattern matcher enabled
				http.authorizeRequests()
						.antMatchers("/home", "/login", "/token", "/login/v1", "/register")
						.permitAll()// les pages accessibles sans authentifications (pour pouvoir s'identifier).
						.anyRequest().authenticated(); // toutes les pages/requêtes nécessite une authentification
			} else { // http pattern matcher disabled
				http.authorizeRequests()
						.anyRequest().permitAll(); // toutes les pages/requêtes sont accessibles
			}
			http.authorizeRequests()
					.and().exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
					.and().sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
					.and().httpBasic();
			http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        }
    }

	// ###########################################################################
	// WebSecurityConfigurerAdapter bean with Role Authentication Security disabled
	// application.properties file :
	// + spring.profiles.include=roleAuthSecurityDisabled
	// ###########################################################################

	@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
    @EnableWebSecurity
    @Profile("roleAuthSecurityDisabled")
    //@EnableGlobalMethodSecurity(prePostEnabled = true)
    public class RoleSecurityDisabledConfig extends WebSecurityConfigurerAdapter {

		@Bean
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
			http.cors().and().csrf().disable()
					.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll();
			if ( ! httpPatternMatcherDisabled) { // http pattern matcher enabled
				http.authorizeRequests()
						.antMatchers("/home", "/login", "/token", "/login/v1", "/register")
						.permitAll()// les pages accessibles sans authentifications (pour pouvoir s'identifier).
						.anyRequest().authenticated(); // toutes les pages/requêtes nécessite une authentification
			} else { // http pattern matcher disabled
				http.authorizeRequests()
						.anyRequest().permitAll(); // toutes les pages/requêtes sont accessibles
			}
			http.authorizeRequests()
					.and().exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
					.and().sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
					.and().httpBasic();
			http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        }
    }
}