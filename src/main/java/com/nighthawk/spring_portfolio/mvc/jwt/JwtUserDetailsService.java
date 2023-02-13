package com.nighthawk.spring_portfolio.mvc.jwt;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nighthawk.spring_portfolio.mvc.person.*;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private PersonJpaRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = repository.findByEmail(username);
		if (person != null) {
			return new User(person.getEmail(), person.getPassword(),
					new ArrayList<>());
					// if ("admin".equals(username)) {
					// 	return new User("admin", "$2a$10$NLAdhN1Leq00pVjoP1Br3OHy3eyDXR.modOhp3drN634TQTNh4vkq",
					// 	new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}