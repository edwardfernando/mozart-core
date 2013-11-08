package mozart.core.exception;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

@Provider
@Component
public class NotFoundExceptionResolver implements ExceptionMapper<NotFoundException> {

	public Response toResponse(NotFoundException ex) {

		error.registerError(new Error(String.format(
		    "Unknown Exception Caught : %s",
		    ex.getMessage())));

		return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
	}

	private final ErrorWrapper error = new ErrorWrapper();

}
