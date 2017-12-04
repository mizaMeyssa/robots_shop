package com.robot.robots.controller;

import java.util.Collection;
import java.util.List;

import jersey.repackaged.com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.robot.robots.domain.Robot;
import com.robot.robots.domain.User;
import com.robot.robots.repository.UserRepository;
import com.robot.robots.resource.RobotResource;
import com.robot.robots.resource.UserResource;
import com.robot.robots.resource.UserResourceAssembler;

@CrossOrigin(origins = "*")
@RestController
@ExposesResourceFor(User.class)
@RequestMapping(value = "/user", produces = "application/json")
public class UserController {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private UserResourceAssembler assembler;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<UserResource>> findAllUser() {
		List<User> users = Lists.newArrayList(repository.findAll(new Sort(Sort.Direction.ASC, "id")));
        return new ResponseEntity<>(assembler.toResourceCollection(users), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserResource> findUserById(@PathVariable Long id) {
		User user = repository.findOne(id);
        if (user != null) {
            return new ResponseEntity<>(assembler.toResource(user), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<UserResource> deleteUser(@PathVariable Long id) {
		User toDeleteUser = repository.findOne(id);
		HttpStatus responseStatus;
        if (toDeleteUser != null) {
        	repository.delete(id);
        	responseStatus = HttpStatus.NO_CONTENT;
        }
        else {
        	responseStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(responseStatus);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<UserResource> createUser( @RequestBody User user) {
		User createdUser = repository.save(user);
        return new ResponseEntity<>(assembler.toResource(createdUser), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<UserResource> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
    	User toUpdateUser = repository.findOne(id);
    	toUpdateUser.set(updatedUser);
    	User newUser = repository.save(toUpdateUser);
        if (newUser != null) {
        	return new ResponseEntity<>(assembler.toResource(newUser), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
