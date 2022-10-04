package com.example;

import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    private static final Logger LOGGER = Logger.getLogger(PersonResource.class);

    private final PersonService personService;

    @Inject
    public PersonResource(PersonService personService) {
        this.personService = personService;
    }

    @GET
    public Response getPerson(){
        final var retrievedPerson = personService.getPerson();

        LOGGER.infof("Retrieved %s", retrievedPerson);

        return Response.status(Status.OK).entity(retrievedPerson).build();
    }

    @POST
    public Response addPerson(){

        try {
            final var createdPerson = personService.addPerson();

            LOGGER.infof("Created %s", createdPerson);

            return Response.status(Status.CREATED).entity(createdPerson).build();


        } catch (Exception e) {
            LOGGER.error("ERROR happened", e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }


}