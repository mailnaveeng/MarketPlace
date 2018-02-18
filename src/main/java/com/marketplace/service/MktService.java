package com.marketplace.service;

import com.marketplace.MktConstants;
import com.marketplace.exception.InputValidationException;
import com.marketplace.exception.MktErrorMessage;
import com.marketplace.exception.MktErrorType;
import com.marketplace.exception.ResourceNotFoundException;
import com.marketplace.repository.BidRepository;
import com.marketplace.repository.ProjectRepository;
import com.marketplace.repository.UserRepository;
import com.marketplace.resource.Bid;
import com.marketplace.resource.Project;
import com.marketplace.resource.User;
import com.marketplace.util.MktValidator;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class MktService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BidRepository bidRepository;

    private static Logger logger = LoggerFactory.getLogger(MktService.class);



    @PostConstruct
    void buildUserBase() {

        long userCount = userRepository.count();
        logger.info("Entered Building User Base for Application, Current User Count: {}",userCount);
        if(userCount == 0) {
            logger.info("Entered Building User Base for Application since not users exists");
            List<User> users = new ArrayList<User>();
            users.add(new User("Bruce","Wayne","bruce.wayne@mail.com"));
            users.add(new User("Tony","Stark","tony.stark@mail.com"));
            users.add(new User("Lex","Luther","lex.luther@mail.com"));
            users.add(new User("Harley","Quinn","harley.quinn@mail.com"));
            userRepository.save(users);
        }
    }


    /**
     * Creates a Project in the Market Place System for a User
     * @param project
     * @param userId
     * @return
     * @throws ResourceNotFoundException
     * @throws InputValidationException
     */
    public Project createProject(Project project, String userId) throws ResourceNotFoundException, InputValidationException {

        logger.info("Entered Create Project for userId:{}, Project Details: {}",userId, project);

        User user = userRepository.findByUserId(userId);
        if (user == null ) {
            logger.warn("No User found with  userId: {}, Throwing Resource Not Found",userId, project);
            throw new ResourceNotFoundException("Resource not Found", null, Collections.singletonList(new MktErrorMessage(MktErrorType.RESOURCE_NOT_FOUND, userId)));
        }
        //Validate Project Details
        MktValidator.validateProject(project);
        project.setUserId(user.getUserId());
        project.setProjectId(MktConstants.PROJECT_PREFIX+ RandomStringUtils.randomAlphanumeric(6).toUpperCase());
        project.setCreatedTimeStamp(new Date());
        project.setLastModifiedTimeStamp(new Date());

        //Persist Project to Repository
        project = projectRepository.save(project);

        logger.info("Created Project for userId:{}, Project Id: {}",userId, project.getProjectId());
        return project;

    }


    /**
     * Gets Project Details based on Project Id, Also returns best bid for the project.
     * @param projectId
     * @return
     * @throws ResourceNotFoundException
     */
    public Project getProject(String projectId) throws ResourceNotFoundException {

        logger.info("Entered Get Project based on projectId:{}",projectId);

        Project project = projectRepository.findByProjectId(projectId);
        if (project == null ) {
            throw new ResourceNotFoundException("Resource not Found", null, Collections.singletonList(new MktErrorMessage(MktErrorType.RESOURCE_NOT_FOUND, projectId)));
        }

        Bid bid = bidRepository.findFirstByProjectIdOrderByBidPriceAsc(projectId);
        if (bid != null) {
            project.setBestBid(bid);
        }
        logger.info("Fetched Project Details based on projectId:{}, project :{}",projectId,project);
        return project;

    }


    /**
     * Fetches all Projects that are open for Bidding
     * @return
     * @throws ResourceNotFoundException
     */
    public List<Project> findAllOpenProjects(Integer pageNumber, Integer pageSize) {
        logger.info("Entered findAllOpenProjects");
        if(pageNumber == null) pageNumber = 0;
        if(pageSize == null) pageSize = MktConstants.DFL_PAGE_SIZE;
        List<Project> allOpenProject = projectRepository.findAllOpenProjects(new Date(), new PageRequest(pageNumber,pageSize));
        logger.info("No of projects Fetched : {}",allOpenProject.size());
        return allOpenProject;
    }


    /**
     * Creates a Bid for a project
     * @param bid
     * @param userId
     * @param projectId
     * @return
     * @throws ResourceNotFoundException
     * @throws InputValidationException
     */
    public Bid createBid(Bid bid,String userId, String projectId) throws ResourceNotFoundException, InputValidationException {

        logger.info("Entered Create Bid for userId:{}, Project Id: {}, Bid : {}",userId, projectId,bid);
        User user = userRepository.findByUserId(userId);
        if (user == null ) {
            throw new ResourceNotFoundException("Resource not Found", null,
                    Collections.singletonList(new MktErrorMessage(MktErrorType.RESOURCE_NOT_FOUND, userId)));
        }

        Project project = projectRepository.findByProjectId(projectId);
        if (project == null ) {
            throw new ResourceNotFoundException("Resource not Found", null,
                    Collections.singletonList(new MktErrorMessage(MktErrorType.RESOURCE_NOT_FOUND, projectId)));
        }

        MktValidator.validateBid(bid,project);

        bid.setUserId(userId);
        bid.setBidId(MktConstants.BID_PREFIX+RandomStringUtils.randomAlphanumeric(6).toUpperCase());
        bid.setProjectId(projectId);
        bid.setCreatedTimeStamp(new Date());
        bid.setLastModifiedTimeStamp(new Date());

        bid = bidRepository.save(bid);

        logger.info("Created bid for userId:{}, Project Id: {}, Bid Id: {} ",userId, projectId,bid.getBidId());
        return bid;


    }





}
