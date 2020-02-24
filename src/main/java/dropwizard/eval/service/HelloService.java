package dropwizard.eval.service;

import dropwizard.eval.model.HelloResponse;

public class HelloService {

    private final String template = "Hello %s";

    public HelloResponse hello(String name) {
        final String message = String.format(template, name);
        return new HelloResponse(message);
    }
}
