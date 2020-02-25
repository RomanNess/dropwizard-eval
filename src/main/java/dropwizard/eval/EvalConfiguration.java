package dropwizard.eval;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class EvalConfiguration extends Configuration {

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @Valid
    @NotNull
    private HelloConfiguration helloConfiguration;

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory factory) {
        this.database = factory;
    }

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    @JsonProperty("hello")
    public HelloConfiguration getHelloConfiguration() {
        return helloConfiguration;
    }

    @JsonProperty("hello")
    public void setHelloConfiguration(HelloConfiguration helloConfiguration) {
        this.helloConfiguration = helloConfiguration;
    }
}
