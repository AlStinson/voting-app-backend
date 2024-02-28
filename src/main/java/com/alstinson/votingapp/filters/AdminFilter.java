package com.alstinson.votingapp.filters;

import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;

import java.util.List;

public class AdminFilter {
    @ConfigProperty(name = "quarkus.admin.tokens")
    List<String> adminTokens;

    @ServerRequestFilter(preMatching = true)
    public void validateAdmin(ContainerRequestContext requestContext) {
        if (!requestContext.getUriInfo().getPath().startsWith("/admin")) return;
        if (adminTokens.contains(requestContext.getHeaderString("X-ADMIN"))) return;
        throw new ClientErrorException(Response.Status.UNAUTHORIZED);
    }
}
