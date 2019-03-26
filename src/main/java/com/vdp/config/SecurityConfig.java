package com.vdp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("userDetailsService")
	UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
				.antMatchers("/js/**")
				.antMatchers("/style/**")
				.antMatchers("/img/**")
				.antMatchers("/dbimages/**")
				.antMatchers("/libs");

	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {


		http.authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
				 .antMatchers("/user/**").access("hasRole('ROLE_USER')")
		         .antMatchers("/basket/**").access("hasRole('ROLE_USER')")
		          .antMatchers("/resources/**", "/**").permitAll()
				 .and().formLogin()
			.loginPage("/login").defaultSuccessUrl("/spread").failureUrl("/login?error")
				.usernameParameter("username")
				.passwordParameter("password");

				http.logout().permitAll().logoutUrl("/logout").logoutSuccessUrl("/index").invalidateHttpSession(true)
				.and().exceptionHandling().accessDeniedPage("/403");
				http.csrf().disable();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
}