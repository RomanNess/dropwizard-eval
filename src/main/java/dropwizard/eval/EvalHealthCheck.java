package dropwizard.eval;

import com.codahale.metrics.health.HealthCheck;

public class EvalHealthCheck extends HealthCheck {
    @Override
    protected Result check() {
        return Result.healthy("I'm good, thanks.");
    }
}
