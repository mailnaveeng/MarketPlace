package com.marketplace.repository;

import com.marketplace.resource.Project;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;


public interface ProjectRepository  extends MongoRepository<Project, String> {

    Project findByProjectId(String projectId);

    @Query(value = "{'bidOpenDate': {'$lte': ?0} , 'bidCloseDate': {'$gte': ?0}}")
    List<Project> findAllOpenProjects(Date date, Pageable pageable);
}
