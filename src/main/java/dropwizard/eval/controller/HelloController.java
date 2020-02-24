package dropwizard.eval.controller;

import com.codahale.metrics.annotation.Timed;
import dropwizard.eval.model.HelloResponse;
import dropwizard.eval.service.HelloService;

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
    private final HelloService helloService;

    public HelloController(String defaultValue, HelloService helloService) {
        this.defaultValue = defaultValue;
        this.helloService = helloService;
    }

    @GET
    @Timed
    public HelloResponse hello(@QueryParam("name") Optional<String> name) {
        return helloService.hello(name.orElse(defaultValue));
    }
}
