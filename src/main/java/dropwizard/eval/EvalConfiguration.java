package dropwizard.eval;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.constraints.NotNull;

public class EvalConfiguration extends Configuration {

    @NotNull
    private HelloConfiguration helloConfiguration;

    @JsonProperty("hello")
    public HelloConfiguration getHelloConfiguration() {
        return helloConfiguration;
    }

    @JsonProperty("hello")
    public void setHelloConfiguration(HelloConfiguration helloConfiguration) {
        this.helloConfiguration = helloConfiguration;
    }
}
