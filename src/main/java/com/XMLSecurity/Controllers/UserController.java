package com.XMLSecurity.Controllers;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.XMLSecurity.Repositories.AuthRepo;
import com.XMLSecurity.Repositories.UserRepo;
import com.XMLSecurity.Models.*;

@RestController
@RequestMapping(value="/users",produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private AuthRepo authRepo;
	
	
	@CrossOrigin
	@PostMapping("/getInactive")
	public ResponseEntity<List<User>> getInactive(){
		List<User> lista = userRepo.findAllByActive(false);
		return new ResponseEntity<List<User>>(lista,HttpStatus.OK);
	}
	
	@CrossOrigin
	@PostMapping("/activate/{email}")
	public ResponseEntity<Boolean> activateUser(@PathVariable("email") String email){
		User user = userRepo.findByEmail(email);
		if(user!=null) {
			user.setActive(true);
			userRepo.save(user);
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);
	}
	
	@CrossOrigin
	@PostMapping("/isSessionValid/{session}")
	public ResponseEntity<Boolean> isSessionvalid(@PathVariable("session") String session){
		if(session.length()<5) {
			return new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);
		}
		if(userRepo.findByActiveSession(session)!=null) {
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);
	}
	@CrossOrigin
	@PostMapping("/isSessionValidAndAdmin/{session}")
	public ResponseEntity<Boolean> isSessionValidAndAdmin(@PathVariable("session") String session){
		if(session.length()<5) {
			return new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);
		}
		User user = userRepo.findByActiveSession(session);
		if(user!=null) {
			if(user.getAuthority().getName().equals("Administrator")) {
				return new ResponseEntity<Boolean>(true,HttpStatus.OK);
			}
		}
		return new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);
	}
	
	@CrossOrigin
	@PostMapping("/login/{email}/{password}")
	public ResponseEntity<String> login(@PathVariable("email") String email, @PathVariable("password") String password) throws NoSuchAlgorithmException{

		password = password.hashCode()+"";
		User user = userRepo.findByEmailAndPassword(email, password);
		if(user!=null) {
			if(!user.getActive()) {
				return new ResponseEntity<String>("",HttpStatus.BAD_REQUEST);
			}
			String uuid = UUID.randomUUID().toString();
			user.setActiveSession(uuid);
			userRepo.save(user);
			return new ResponseEntity<String>(uuid,HttpStatus.OK);
		}
		return new ResponseEntity<String>("",HttpStatus.BAD_REQUEST);
	}
	@CrossOrigin
	@PostMapping("/register/{email}/{pass}")
	public ResponseEntity<String> register(@PathVariable("email") String email,@PathVariable("pass") String password){
		if(userRepo.findByEmail(email)!=null) { 
			return new ResponseEntity<String>("That email already exists.",HttpStatus.NOT_ACCEPTABLE);
		}
		User user = new User();
		user.setEmail(email); 
		user.setPassword(password.hashCode()+"");
		user.setActive(false);
		Authority auth = authRepo.findByName("Korisnik");
		user.setAuthority(auth);
		if(email.length()>5 && password.length()>5) {
			userRepo.save(user);
			return new ResponseEntity<String>("Wait for admin to aprove you.",HttpStatus.OK);
		}
		return new ResponseEntity<String>("Bad credencials.",HttpStatus.BAD_REQUEST);
	}
	
	
	@CrossOrigin
	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> lista = userRepo.findAllByActive(true);
		if(lista!=null && lista.size()>=0) {
			return new ResponseEntity<List<User>>(lista,HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/getAuthorities")
	public ResponseEntity<List<Authority>> getAuthorities(){
		ArrayList<Authority> lista = new ArrayList<Authority>();
		Authority ath1 = new Authority(1, "Administrator");
		Authority ath2 = new Authority(2, "Moderator");
		System.out.println(ath1.getName());
		lista.add(ath1);
		lista.add(ath2);
//		User user = new User(1, "as", "as", "as", true, ath1);
		return new ResponseEntity<List<Authority>>(lista,HttpStatus.OK);
	}
	
	@GetMapping("/test")
	public ResponseEntity<String> test() {
		return new ResponseEntity<String>("JESTE",HttpStatus.OK);
	}
}
