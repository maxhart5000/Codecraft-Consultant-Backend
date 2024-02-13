/*
 * Configuration class for customizing Spring Data REST.
 */
package com.hartcode.application.config;

import com.hartcode.application.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    @Value("${allowed.origins}")
    private String[] theAllowedOrigins;

    private final EntityManager entityManager;

    /**
     * Constructor for MyDataRestConfig.
     *
     * @param entityManager Entity manager for accessing JPA metadata.
     */
    @Autowired
    public MyDataRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Configures repository REST configuration.
     *
     * @param config RepositoryRestConfiguration object.
     * @param cors   CorsRegistry object for configuring CORS.
     */
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        HttpMethod[] theUnsupportedActions = {HttpMethod.DELETE, HttpMethod.PUT, HttpMethod.POST, HttpMethod.PATCH};
        Class<?>[] theClasses = {Product.class, ProductCategory.class, Country.class, State.class, Order.class};

        // Disable HTTP methods for entities: PUT, POST, DELETE, PATCH
        for (Class<?> tempClass : theClasses) {
            disableHttpMethods(config, theUnsupportedActions, tempClass);
        }

        // Expose entity IDs
        exposeIds(config);

        // Configure CORS mapping
        cors.addMapping(config.getBasePath() + "/**").allowedOrigins(theAllowedOrigins);
    }

    /**
     * Disables specified HTTP methods for the given class.
     *
     * @param config              RepositoryRestConfiguration object.
     * @param theUnsupportedActions Array of HTTP methods to disable.
     * @param theClass            Class for which HTTP methods need to be disabled.
     */
    private static void disableHttpMethods(RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions, Class<?> theClass) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure(((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions)))
                .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
    }

    /**
     * Exposes entity IDs for all entity classes.
     *
     * @param config RepositoryRestConfiguration object.
     */
    private void exposeIds(RepositoryRestConfiguration config) {

        // Expose entity IDs

        // Get a list of all entity classes from the entity manager
        Set<EntityType<?>> entityTypes = entityManager.getMetamodel().getEntities();

        // Create a list of entity classes
        List<Class<?>> entityClasses = new ArrayList<>();

        // Get the entity types for the entities
        for (EntityType<?> tempEntityType : entityTypes) {
            entityClasses.add(tempEntityType.getJavaType());
        }

        // Expose the entity IDs for the array of entity/domain types
        Class<?>[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
