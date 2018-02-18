package com.marketplace.util;

import com.marketplace.exception.InputValidationException;
import com.marketplace.exception.MktErrorMessage;
import com.marketplace.exception.MktErrorType;
import com.marketplace.resource.Bid;
import com.marketplace.resource.Project;
import com.marketplace.resource.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MktValidator {

    private static Logger logger = LoggerFactory.getLogger(MktValidator.class);

    public static final Pattern EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    /**
     * Validates the User Information.
     * @param user
     * @throws InputValidationException
     */
    public static void validateUser(User user) throws InputValidationException {
        logger.info("Entered Validate User for user : {}", user);

        if (user == null) {
            logger.warn("User Details are null");
            throw new InputValidationException("User details null",null,
                    Collections.singletonList( new MktErrorMessage(MktErrorType.INVALID_ENTITY,User.class.getSimpleName())));
        }
        List<MktErrorMessage> errorMessages =  new ArrayList<MktErrorMessage>();

        if (StringUtils.isEmpty(user.getFirstName()) || StringUtils.isEmpty(user.getLastName())) {
            logger.warn("First Name or Last Name missing in User");
            errorMessages.add( new MktErrorMessage(MktErrorType.INVALID_ENTITY,"First Name/Last Name Empty"));

        }
        if (StringUtils.isEmpty(user.getEmailId())) {
            logger.warn("Email Id is missing in User");
            errorMessages.add( new MktErrorMessage(MktErrorType.INVALID_ENTITY,"Email Empty"));
        }

        Matcher matcher = EMAIL_ADDRESS_REGEX .matcher(user.getEmailId());

        if (!StringUtils.isEmpty(user.getEmailId()) && !matcher.find() ) {
            logger.warn("Email Id not valid in User");
            errorMessages.add( new MktErrorMessage(MktErrorType.INVALID_ENTITY,"Invalid Email"));
        }

        if (!CollectionUtils.isEmpty(errorMessages)) {
            logger.warn("Users Details invalid Throwing Input Validation Exception");
            throw new InputValidationException("User Invalid",null,errorMessages);
        }
        logger.info("User Details are Valid");

    }

    /**
     * Performs Validation on Project Details.
     * @param project
     * @throws InputValidationException
     */

    public static void validateProject(Project project) throws InputValidationException {
        logger.info("Entered Validate Project for project : {}", project);

        if (project == null) {
            logger.warn("Project Details are null");
            throw new InputValidationException("Project details null",null,
                    Collections.singletonList( new MktErrorMessage(MktErrorType.INVALID_ENTITY,Project.class.getSimpleName())));
        }

        List<MktErrorMessage> errorMessages =  new ArrayList<MktErrorMessage>();
        if (StringUtils.isEmpty(project.getName()) || StringUtils.isEmpty(project.getRequirements())) {
            logger.warn("Project Name or  Requirements are not valid");
            errorMessages.add( new MktErrorMessage(MktErrorType.INVALID_ENTITY,"Project Name/Requirements Empty"));
        }

        Date now = new Date();
        if (project.getBidOpenDate() == null || now.compareTo(project.getBidOpenDate()) > 0) {
            logger.warn("Bid Open Date not valid. It has to be after the Current Date");
            errorMessages.add( new MktErrorMessage(MktErrorType.INVALID_ENTITY,"Bid open Date has to be after Current Date"));
        }

        if (project.getBidCloseDate() == null || now.compareTo(project.getBidCloseDate()) > 0) {
            logger.warn("Bid Close Date not valid. It has to be greater than the Current Date");
            errorMessages.add( new MktErrorMessage(MktErrorType.INVALID_ENTITY,"Bid Close Date has to be after Current Date"));
        }

        if(project.getBidCloseDate() != null && project.getBidOpenDate() !=null
                &&  project.getBidCloseDate().compareTo(project.getBidOpenDate()) < 0) {
            logger.warn("Bid Close Date needs to be after Bid Open Date ");
            errorMessages.add( new MktErrorMessage(MktErrorType.INVALID_ENTITY,"Bid Close Date " +
                    "must be greater than Bid Open Date "));
        }

        if (project.getExpectedDelivery() == null || now.compareTo(project.getExpectedDelivery()) > 0) {
            logger.warn("Bid Expected Delivery not valid. It has to be greater than the Current Date");
            errorMessages.add( new MktErrorMessage(MktErrorType.INVALID_ENTITY,"Expected Delivery Date has to be" +
                    " after Current Date"));
        }

        if(project.getBidCloseDate() != null && project.getExpectedDelivery() !=null
                && project.getExpectedDelivery().compareTo(project.getBidCloseDate()) < 0) {
            logger.warn("Bid Expected Delivery must be after Bid Close Date ");
            errorMessages.add( new MktErrorMessage(MktErrorType.INVALID_ENTITY,"Expected Deliver Date " +
                    "must be after  Bid Close Date "));
        }

        if (project.getBudget() == null || project.getBudget() < 0) {
            logger.warn("Invalid Project Budget ");
            errorMessages.add( new MktErrorMessage(MktErrorType.INVALID_ENTITY,"Budget has to be greater than 0"));
        }

        if (!CollectionUtils.isEmpty(errorMessages)) {
            logger.warn("Invalid Project Details Throwing Input Validation Exception ");
            throw new InputValidationException("Project Invalid",null,errorMessages);
        }
        logger.info("Project Details are Valid");

    }


    /**
     * Performs Validation on Bid Details
     * @param bid
     * @param project
     * @throws InputValidationException
     */
    public static void validateBid(Bid bid, Project project) throws InputValidationException {
        logger.info("Entered Validate Bid for bid : {}", bid);
        if (bid == null && project == null) {
            logger.warn("Bid Details are null");
            throw new InputValidationException("Bid/Project details null",null,
                    Collections.singletonList( new MktErrorMessage(MktErrorType.INVALID_ENTITY,Bid.class.getSimpleName())));
        }


        List<MktErrorMessage> errorMessages =  new ArrayList<MktErrorMessage>();
        if(bid.getBidPrice() == null
                || bid.getBidPrice() <0 || bid.getBidPrice().compareTo(project.getBudget()) > 0 ) {
            logger.warn("Bid Price should be greater than 0 and less Project Budget");
            errorMessages.add( new MktErrorMessage(MktErrorType.INVALID_ENTITY,"Bid Price should be greater than 0 " +
                    "and less than Project Budget"));
        }

        Date now = new Date();
        if (now.compareTo(project.getBidOpenDate()) < 0 || now.compareTo(project.getBidCloseDate()) > 0 ) {
            logger.warn("Project not Open or is Closed for bidding");
            errorMessages.add( new MktErrorMessage(MktErrorType.INVALID_ENTITY,"Project not Open or is Closed for bidding"));
        }

        if (!CollectionUtils.isEmpty(errorMessages)) {
            throw new InputValidationException("Bid Invalid",null,errorMessages);
        }

        logger.info("Bid Details are Valid");

    }
}
