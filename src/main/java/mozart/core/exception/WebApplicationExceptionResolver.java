package mozart.core.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

@Provider
@Component
public class WebApplicationExceptionResolver implements ExceptionMapper<WebApplicationException> {

	public Response toResponse(WebApplicationException ex) {
		return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
	}

}
