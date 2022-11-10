package org.acme;

import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;
import org.jboss.resteasy.reactive.server.ServerResponseFilter;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;
import static java.util.Optional.ofNullable;

@Provider
@Singleton
public class RequestLogger {
    @Inject
    Logger log;

    @ServerExceptionMapper
    public Response toResponse(Throwable throwable, ContainerRequestContext request) {
        var webApplicationException = throwable instanceof WebApplicationException ? (WebApplicationException) throwable : new InternalServerErrorException(throwable);
        var message = format("%s %s %s", request.getMethod(), request.getUriInfo().getPath(), webApplicationException.getLocalizedMessage());
        log.error(message, throwable);
        return webApplicationException.getResponse();

    }
    @ServerResponseFilter(priority = 2)
    public void filter(ContainerRequestContext reqCtx, ContainerResponseContext respCtx) {
        var requestPath = reqCtx.getUriInfo().getPath();
        var requestMethod = reqCtx.getMethod();
        var respTime = ofNullable(reqCtx.getProperty(this.getClass().getName())).map(start -> (currentTimeMillis() - (long) start) / 1000.0).orElse(-1.0);

        log.error(requestMethod + " " + requestPath + " " + respTime);
    }
    @ServerRequestFilter(priority = 2)
    public void filter(ContainerRequestContext containerRequestContext) {
        containerRequestContext.setProperty(this.getClass().getName(), currentTimeMillis());
    }
}
