package kotlintraining.lesson5.init;

import kotlintraining.lesson5.MyResourceImpl;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        registerEndpoints();
    }
    private void registerEndpoints() {
        register(MyResourceImpl.class);
    }
}

