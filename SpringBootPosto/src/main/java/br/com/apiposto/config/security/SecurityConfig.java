package br.com.apiposto.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.apiposto.repository.UsuarioRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Autowired
	private GeraTokenService tokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	@Bean
	protected AuthenticationManager authenticationManager()throws Exception{
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
	//Estão permitidos por motivos de testes.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST,"/auth").permitAll()
		.antMatchers(HttpMethod.GET,"/usuario/all").permitAll()
		.antMatchers(HttpMethod.POST,"/usuario/save").permitAll()
		.antMatchers(HttpMethod.GET,"/usuario/find/{id}").permitAll()
		.antMatchers(HttpMethod.DELETE,"/usuario/delete/{id}").permitAll()
		.antMatchers(HttpMethod.POST,"/posto/save").permitAll()
		.antMatchers(HttpMethod.GET,"/posto/all").permitAll()
		.antMatchers(HttpMethod.GET,"/posto/find/{id}").permitAll()
		.antMatchers(HttpMethod.DELETE,"/posto/delete/{id}").permitAll()
		.antMatchers("/**").permitAll()
		.antMatchers("/swagger-ui.html").permitAll()
		.antMatchers("/swagger-resources/configuration/ui").permitAll()
	
		
		
	


		
		.anyRequest().authenticated()
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);

	}

	@Override
	public void configure(WebSecurity web) throws Exception {

	}
	
	
	
	
	
	
	
}
