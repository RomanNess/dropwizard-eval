package dropwizard.eval.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dropwizard.eval.EvalApplication;
import dropwizard.eval.EvalConfiguration;
import dropwizard.eval.model.HelloResponse;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(DropwizardExtensionsSupport.class)
class HelloControllerTest {

    public static final DropwizardAppExtension<EvalConfiguration> APP =
            new DropwizardAppExtension<>(EvalApplication.class, ResourceHelpers.resourceFilePath("config-test.yml"));

    private static ObjectMapper mapper;
    private static int localPort;

    @BeforeAll
    static void initFields() {
        mapper = APP.getObjectMapper();
        localPort = APP.getLocalPort();
    }

    @Test
    void hello() throws IOException, InterruptedException {
        HelloResponse helloResponse = getResponseDeserialized("/hello", HelloResponse.class);
        assertThat(helloResponse.getMessage()).isEqualTo("Hello TestDefault");
    }

    @Test
    void helloTesty() throws Exception {
        assertThat(getResponseDeserialized("/hello?name=Testy", HelloResponse.class).getMessage()).isEqualTo("Hello Testy");
    }

    @Test
    void healthOnAppPort() throws Exception {
        String path = "/admin/healthcheck";
        HttpResponse<String> response = getResponse(path);
        assertThat(response.statusCode()).isEqualTo(200);
    }

    private <T> T getResponseDeserialized(String path, Class<T> clazz) throws IOException, InterruptedException {
        HttpResponse<String> response = getResponse(path);
        return mapper.readValue(response.body(), clazz);
    }

    private HttpResponse<String> getResponse(String path) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + localPort + path))
                .GET()
                .build();

        HttpClient httpClient = HttpClient.newBuilder()
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

}
