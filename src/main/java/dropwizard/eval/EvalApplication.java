package dropwizard.eval;

import dropwizard.eval.controller.HelloController;
import dropwizard.eval.service.HelloService;
import io.dropwizard.Application;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;

public class EvalApplication extends Application<EvalConfiguration> {

    public static void main(String[] args) throws Exception {
        new EvalApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<EvalConfiguration> bootstrap) {
        // nothing to do yet
    }

    public void run(EvalConfiguration evalConfiguration, Environment environment) {

        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, evalConfiguration.getDataSourceFactory(), "postgresql");
        environment.jersey().register(jdbi);

        HelloService helloService = new HelloService();

        final HelloController helloController = new HelloController(evalConfiguration.getHelloConfiguration().getDefaultName(), helloService);
        environment.jersey().register(helloController);

        final EvalHealthCheck evalHealthCheck = new EvalHealthCheck();
        environment.healthChecks().register("evalHealth", evalHealthCheck);
    }
}
