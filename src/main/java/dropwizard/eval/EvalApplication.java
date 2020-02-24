package dropwizard.eval;

import dropwizard.eval.controller.HelloController;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class EvalApplication extends Application<EvalConfiguration> {

    public static void main(String[] args) throws Exception {
        new EvalApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<EvalConfiguration> bootstrap) {
        // nothing to do yet
    }

    public void run(EvalConfiguration evalConfiguration, Environment environment) throws Exception {
        final HelloController helloController = new HelloController();
        environment.jersey().register(helloController);

        final EvalHealthCheck evalHealthCheck = new EvalHealthCheck();
        environment.healthChecks().register("evalHealth", evalHealthCheck);
    }
}
