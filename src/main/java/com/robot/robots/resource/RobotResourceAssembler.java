package com.robot.robots.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import com.robot.robots.domain.Robot;

@Component
public class RobotResourceAssembler extends ResourceAssembler<Robot, RobotResource> {

    @Autowired
    protected EntityLinks entityLinks;
    private static final String UPDATE_REL = "update";
    private static final String DELETE_REL = "delete";

    @Override
    public RobotResource toResource(Robot robot) {

        RobotResource resource = new RobotResource(robot);
        final Link selfLink = entityLinks.linkToSingleResource(robot);
        resource.add(selfLink.withSelfRel());
        resource.add(selfLink.withRel(UPDATE_REL));
        resource.add(selfLink.withRel(DELETE_REL));
        return resource;
    }

}
