package dropwizard.eval.controller;

import com.codahale.metrics.annotation.Timed;
import dropwizard.eval.model.HelloResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
public class HelloController {

    private final String defaultValue;
    private final String template = "Hello %s";

    public HelloController(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @GET
    @Timed
    public HelloResponse hello(@QueryParam("name") Optional<String> name) {
        final String message = String.format(template, name.orElse(defaultValue));
        return new HelloResponse(message);
    }
}
