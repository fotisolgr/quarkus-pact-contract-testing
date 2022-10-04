package com.example;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@ApplicationScoped
@RegisterRestClient
public interface PersonService {

    @GET
    @Path("/person")
    Response getPerson();

    @POST
    @Path("/person")
    Response addPerson();
}