package com.example;

import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.gradle.internal.impldep.javax.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@QuarkusTest
@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "Provider")
class PersonServiceContractIT {

    @Inject
    @RestClient
    PersonService personService;

    //PACTS
    @Pact(provider = "Provider", consumer = "Consumer")
    public RequestResponsePact getPerson(PactDslWithProvider builder) {
        return builder
                .uponReceiving("Send GET request at /person")
                .path("/person")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(new PactDslJsonBody().stringValue("name", "Fotis").numberValue("age", 29))
                .toPact();
    }

    @Pact(provider = "Provider", consumer = "Consumer")
    public RequestResponsePact addPerson(PactDslWithProvider builder) {

        return builder
                .uponReceiving("Send POST request at /person")
                .path("/person")
                .method("POST")
                .willRespondWith()
                .status(201)
                .body(new PactDslJsonBody().stringValue("name", "Kostas").numberValue("age", 32))
                .toPact();
    }

    //TESTS
    @Test
    @PactTestFor(pactMethod = "getPerson", port = "8080")
    void testGetMethod() {
        final var retrievedResponse = personService.getPerson();
        Assertions.assertEquals(200, retrievedResponse.getStatus());
        Assertions.assertEquals("{\"age\":29,\"name\":\"Fotis\"}", retrievedResponse.readEntity(String.class));
    }

    @Test
    @PactTestFor(pactMethod = "addPerson", port = "8080")
    void testPostMethod() {
        final var retrievedResponse = personService.addPerson();
        Assertions.assertEquals(201, retrievedResponse.getStatus());
        Assertions.assertEquals("{\"age\":32,\"name\":\"Kostas\"}", retrievedResponse.readEntity(String.class));
    }

}
