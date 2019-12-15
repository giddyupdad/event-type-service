package com.raminus.ems.event.app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.raminus.ems.event.error.ErrorCatalog;
import com.raminus.ems.event.error.EventTypeServiceException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This configuration helps to generate common error response and enables logging the additional error data in order to support alerting and monitoring.
 * Enabled if {@link Resource} classpath:error-catalog.json exists.
 * Default resource can be overridden using application property event-type.errorCatalogResource.
 */
@Configuration
public class ErrorCatlogConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(ErrorCatlogConfiguration.class);

    @Value("${event-type.errorCatalogResource:classpath*:error-catalog.json}")
    private Resource[] catalogResources;

    private static final String ERROR_NODE_NAME = "errors";
    private Map<String, ErrorCatalog> catalogErrorDetails;

    @PostConstruct
    public void loadErrorCatalog() {
        Assert.notEmpty(catalogResources, "Error catalog resource must be available");
        catalogErrorDetails = getErrorCatalog(Arrays.asList(catalogResources));
    }

    public ErrorCatalog getError(String errorId) {
        return catalogErrorDetails.get(errorId);
    }

    public Collection<ErrorCatalog> getAll() {
        return catalogErrorDetails.values();
    }

    private Map<String, ErrorCatalog> getErrorCatalog(List<Resource> errorCatalogResources) {
        List<ErrorCatalog> errorList = new ArrayList<>();
        for (Resource errorCatalogResource : errorCatalogResources) {
            List<ErrorCatalog> errorCatalogList = getErrorList(errorCatalogResource);
            errorList.addAll(errorCatalogList);
        }
        return errorList.stream().collect(Collectors.toMap(ErrorCatalog::getErrorId, e -> e));
    }

    private List<ErrorCatalog> getErrorList(Resource errorCatalogResource) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(errorCatalogResource.getInputStream());
            JsonNode errorNode = rootNode.get(ERROR_NODE_NAME);
            return objectMapper.readValue(errorNode.toString(),
                TypeFactory.defaultInstance().constructCollectionType(List.class, ErrorCatalog.class));
        } catch (Exception exception) {
            LOG.error("Exception occurred while loading error catalog. Error Message:{}", ExceptionUtils.getStackTrace(exception));
            throw new EventTypeServiceException("103", exception, "Exception occured while loading error catalog");
        }
    }

}