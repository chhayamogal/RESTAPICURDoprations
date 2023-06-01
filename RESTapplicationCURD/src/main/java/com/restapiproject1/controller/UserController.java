package com.restapiproject1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapiproject1.entity.User;
import com.restapiproject1.repo.UserRepo;

@RestController
@RequestMapping("api/users")
public class UserController {
	@Autowired
	UserRepo userrepo;
	//http://localhost:8087/api/users
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
	User savedUser = userrepo.save(user);
	return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}
	// Build Get All Users REST API
	// http://localhost:8080/api/users
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(){
	List<User> users = userrepo.findAll();
	return new ResponseEntity<>(users, HttpStatus.OK);
	}
	// build get user by id REST API
	// http://localhost:8080/api/users/1
	@GetMapping("{id}")

	public ResponseEntity<User> getUserById(@PathVariable Long id){
	Optional<User> user = userrepo.findById(id);
	if(user.isPresent()) {
	return new ResponseEntity<>(user.get(), HttpStatus.OK);
	}
	else {
	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	}

	@PutMapping("{id}")
	// http://localhost:8080/api/users/1
	public ResponseEntity<User> updateUser(@PathVariable("id") Long id,

	@RequestBody User u){
	Optional<User> user = userrepo.findById(id);
	if(user.isPresent()) {
	user.get().setFirstName(u.getFirstName());
	user.get().setLastName(u.getLastName());
	user.get().setEmail(u.getEmail());
	return new ResponseEntity<>(userrepo.save(user.get()), HttpStatus.OK);
	}
	else {
	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	}
	@DeleteMapping("{id}")
	// http://localhost:8080/api/users/1
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id){
	Optional<User> user = userrepo.findById(id);
	if(user.isPresent()) {
	userrepo.deleteById(id);
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	else {

	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	}
}
