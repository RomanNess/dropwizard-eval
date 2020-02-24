package dropwizard.eval.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dropwizard.eval.EvalApplication;
import dropwizard.eval.EvalConfiguration;
import dropwizard.eval.model.HelloResponse;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(DropwizardExtensionsSupport.class)
class HelloControllerTest {

    public static final DropwizardAppExtension<EvalConfiguration> APP = new DropwizardAppExtension<>(EvalApplication.class);

    private static ObjectMapper mapper;
    private static int localPort;

    @BeforeAll
    static void initFields() {
        mapper = APP.getObjectMapper();
        localPort = APP.getLocalPort();
    }

    @Test
    void hello() throws IOException {
        assertThat(performGetRequest("/hello", HelloResponse.class).getMessage()).isEqualTo("Hello anonymous");
    }

    @Test
    void helloTesty() throws IOException {
        assertThat(performGetRequest("/hello?name=Testy", HelloResponse.class).getMessage()).isEqualTo("Hello Testy");
    }

    private <T> T performGetRequest(String path, Class<T> clazz) throws IOException {
        final URL url = new URL("http://localhost:" + localPort + path);
        return mapper.readValue(url.openStream(), clazz);
    }

}
