package com.robot.robots.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.robot.robots.domain.Robot;

@Repository
public interface RobotRepository extends JpaRepository<Robot, Long> {

}
