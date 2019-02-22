package SYSC4806;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;

/**
 * The following class is used to change the base path of the Repositories
 * This bean will override the configuration setting to the specified
 * base path for the repositories to "/api".
 */
@Component
public class CustomizedRestMvcConfiguration extends RepositoryRestConfigurerAdapter {
    /**
     * Override method to change the base path for REST calls
     * @param config
     */
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.setBasePath("/api");
    }
}

