package com.robot.robots.controller;

import java.util.List;
import java.util.Collection;

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
import com.robot.robots.repository.RobotRepository;
import com.robot.robots.resource.RobotResourceAssembler;
import com.robot.robots.resource.RobotResource;

@CrossOrigin(origins = "*")
@RestController
@ExposesResourceFor(Robot.class)
@RequestMapping(value = "/robot", produces = "application/json")
public class RobotController {

    @Autowired
    private RobotRepository repository;

    @Autowired
    private RobotResourceAssembler assembler;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<RobotResource>> findAllRobots() {
        List<Robot> robots = Lists.newArrayList(repository.findAll(new Sort(Sort.Direction.ASC, "id")));
        return new ResponseEntity<>(assembler.toResourceCollection(robots), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<RobotResource> createRobot(@RequestBody Robot robot) {
        Robot createdRobot = repository.save(robot);
        return new ResponseEntity<>(assembler.toResource(createdRobot), HttpStatus.CREATED);

    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<RobotResource> findRobotById(@PathVariable Long id) {
        Robot robot = repository.findOne(id);
        if (robot != null) {
            return new ResponseEntity<>(assembler.toResource(robot), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteRobot(@PathVariable Long id) {
        
        Robot toDeleteRobot = repository.findOne(id);
        HttpStatus responseStatus;
        if (toDeleteRobot != null) {
        	repository.delete(id);
        	responseStatus = HttpStatus.NO_CONTENT;
        }
        else {
        	responseStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(responseStatus);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<RobotResource> updateRobot(@PathVariable Long id, @RequestBody Robot updatedRobot) {

    	Robot toUpdateRobot = repository.findOne(id);
    	toUpdateRobot.set(updatedRobot);
    	Robot newRobot = repository.save(toUpdateRobot);
        if (newRobot != null) {
        	return new ResponseEntity<>(assembler.toResource(newRobot), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
