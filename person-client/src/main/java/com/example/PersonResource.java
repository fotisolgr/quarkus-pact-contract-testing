package com.example;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    @Inject
    @RestClient
    PersonService personService;

    @GET
    public Response getPerson(){
        return personService.getPerson();
    }

    @POST
    public Response addPerson(){
        return personService.addPerson();
    }
}