package parkingspot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import parkingspot.service.CustomerService;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomerService customerService = new CustomerService();

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .logout()
                .permitAll()
                .and()
                .csrf().disable()  // for testing purposes
                .httpBasic();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        List<UserDetails> registeredCustomers = new ArrayList<>();
        customerService.getCustomers().forEach(customer -> registeredCustomers.add(
                User.builder()
                        .username(customer.getLogin())
                        .password(encoder.encode(customer.getPassword()))
                        .roles("USER")
                        .build()));

        UserDetails admin =
                User.builder()
                        .username("admin")
                        .password(encoder.encode("admin"))
                        .roles("ADMIN")
                        .build();

        registeredCustomers.add(admin);

        return new InMemoryUserDetailsManager(registeredCustomers);
    }
}
