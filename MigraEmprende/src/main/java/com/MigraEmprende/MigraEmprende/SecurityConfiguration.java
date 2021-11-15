package com.MigraEmprende.MigraEmprende;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.MigraEmprende.MigraEmprende.services.UsuarioService;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	public UsuarioService usuarioServicio;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth
			.userDetailsService(usuarioServicio)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http			
			.headers().frameOptions().sameOrigin().and()
			.authorizeRequests()
				.antMatchers("/resources/**") //Se añadieron los asteriscos, en otro caso, se puede poner solo "/resources/**"
				.permitAll()
			.and().formLogin()
				.loginPage("/user/login")
					.loginProcessingUrl("/logincheck")
					.usernameParameter("username")
					.passwordParameter("password")
					.defaultSuccessUrl("/inicio")
				.and().logout()
					.logoutUrl("/logout")
					.logoutSuccessUrl("/")
					.permitAll()	
			.and().csrf().disable()
	        .authorizeRequests()
            .anyRequest().permitAll();
	}
}
