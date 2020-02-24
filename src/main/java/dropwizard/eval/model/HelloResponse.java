package dropwizard.eval.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HelloResponse {

    private final String message;

    @JsonCreator
    public HelloResponse(@JsonProperty("message") String message) {
        this.message = message;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }
}
