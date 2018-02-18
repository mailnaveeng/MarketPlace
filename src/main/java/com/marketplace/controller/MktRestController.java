package com.marketplace.controller;

import com.marketplace.exception.InputValidationException;
import com.marketplace.exception.ResourceNotFoundException;
import com.marketplace.resource.Bid;
import com.marketplace.resource.Project;
import com.marketplace.service.MktService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.marketplace.MktConstants.BIDS_PATH;
import static com.marketplace.MktConstants.PROJECTS_PATH;
import static com.marketplace.MktConstants.ROOT_PATH;
import static com.marketplace.MktConstants.USERS_PATH;

@Component
@Path(ROOT_PATH)
public class MktRestController {

    @Autowired
    private MktService mktService;


    @POST
    @Path(USERS_PATH+"/{userId}"+PROJECTS_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProject(@PathParam("userId") String userId,
                                  Project project) throws ResourceNotFoundException, InputValidationException {
        return Response.status(Response.Status.CREATED).entity(mktService.createProject(project,userId)).build();
    }

    @GET
    @Path(PROJECTS_PATH+"/{projectId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProject(@PathParam("projectId") String projectId) throws ResourceNotFoundException {
        return Response.ok().entity(mktService.getProject(projectId)).build();
    }



    @POST
    @Path(USERS_PATH+"/{userId}"+PROJECTS_PATH+"/{projectId}"+BIDS_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBid(@PathParam("userId") String userId,
                              @PathParam("projectId") String projectId,
                              Bid bid) throws ResourceNotFoundException, InputValidationException {
        return Response.status(Response.Status.CREATED).entity(mktService.createBid(bid,userId,projectId)).build();
    }


    @GET
    @Path(PROJECTS_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOpenProjects(@QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit) {
        List<Project> allOpenProjects = mktService.findAllOpenProjects(offset, limit);

        Map<String,Object> responseObj = new LinkedHashMap<>();
        responseObj.put("projects",allOpenProjects);
        responseObj.put("records_found",allOpenProjects.size());
        responseObj.put("offset",offset);
        responseObj.put("limit",limit);
        return Response.ok().entity(responseObj).build();
    }
}
