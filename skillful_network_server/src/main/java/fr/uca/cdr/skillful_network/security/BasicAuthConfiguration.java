package fr.uca.cdr.skillful_network.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@EnableWebSecurity
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BasicAuthConfiguration extends WebSecurityConfigurerAdapter {

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

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.antMatchers("/home","/authentication/login","/authentication/whoami", "/authentication/token", "/authentication/login/v1", "/authentication/register", "/skills/*",
						"/qualifications/*", "/subscriptions/*", "users/usersbyId/{id}", "/users", "/users/{id}/skills",
						"/users/{userId}/skills/{skillId}", "/users/{id}/Qualifications", "/users/{id}/Subscription",
						"/users/**", "/users/{id}","/users/image/{id:.+}","/joboffer/getOne/{id}", "/trainings/{id}", "/simulations/{id}/answer","/applications/jobs/user/{userId}"
						, "/users/uploadImage","/joboffer/**","/user/{userId}/joboffer/{jobOfferId}", // les pages/requêtes /home, /login et /token sont accessibles sans

						"/applications/jobs", "/applications/jobs/{id}/joboffer", "/applications/jobs/user/{userId}", 
						"/applications/jobs/bonjour", "/users/**", "/users/{id}", 
						"/joboffer", "/joboffer/**", "/joboffer/getOne/{id}", "/image/{id:.+}",
						"/offers","/offers/", "/offers/*", "/offers/", "/trainings/{id}", "/simulations/{id}/answer",
						"/simulations/user/startSimulation", "/trainings/**", "/trainings/page", "/trainings/page/*", 

						"/trainings","/user/{userId}/joboffer/{jobOfferId}","/simulations/user", "/users/uploadImage","/users/**" , "/simulations/untruclongaecrire") // les pages/requêtes /home, /login et /token sont accessibles sans

						 

				// les pages/requêtes /home, /login et /token sont accessibles sans
				// authentifications (pour pouvoir s'identifier).

				.permitAll().anyRequest().authenticated() // toutes les qutess pages/requêtes nécessite une
															// authentification pour pouvoir y accéder.
				.and().exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
				.and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().httpBasic();
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
