package com.nighthawk.spring_portfolio.mvc.jwt;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nighthawk.spring_portfolio.mvc.person.Person;
import com.nighthawk.spring_portfolio.mvc.person.PersonDetailsService;

@RestController
@CrossOrigin
public class JwtApiController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private PersonDetailsService personDetailsService;

	@Autowired
	private PasswordEncoder encoder;

	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody Person authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
		System.out.println("authenticate");
		final UserDetails userDetails = personDetailsService
		.loadUserByUsername(authenticationRequest.getEmail());
		final String token = jwtTokenUtil.generateToken(userDetails);
		final ResponseCookie tokenCookie = ResponseCookie.from("jwt", token)
		.httpOnly(true)
		.secure(true)
		.path("/")
		.maxAge(3600)
		// .domain("example.com") // Set to backend domain
		.build();
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, tokenCookie.toString()).build();
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody final Map<String, String> map) {

		// check if user exists
		// if (personDetailsService.loadUserByUsername(map.get("email")) != null) {
		// 	throw new RuntimeException("User already exists");
		// }
		
		// extract term from RequestEntity
    String email = (String) map.get("email");
		String password = (String) map.get("password");
		String name = (String) map.get("name");
		String dob_str = (String) map.get("dob");

			Date dob = null;
		try {  // All data that converts formats could fail
      dob = new SimpleDateFormat("MM-dd-yyyy").parse(dob_str);
    } catch (Exception e) {
      // no actions as dob default is good enough
    }
		
		Person person = new Person(email, encoder.encode(password), name, dob);

		personDetailsService.save(person);
		return ResponseEntity.ok().build();
	}

	// @PostMapping("/register")
	// public ResponseEntity<?> registerUser(@RequestBody Person RegisterRequest) throws Exception {
	// 	// if (personDetailsService.loadUserByUsername(RegisterRequest.getEmail()) != null) {
	// 	// 	throw new Exception("User already exists");
	// 	// }
	// 	personDetailsService.save(RegisterRequest);
	// 	return ResponseEntity.ok().build();
	// }
}