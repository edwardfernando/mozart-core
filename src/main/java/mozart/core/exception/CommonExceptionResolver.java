package mozart.core.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

@Provider
@Component
public class CommonExceptionResolver implements ExceptionMapper<Throwable> {

	public Response toResponse(Throwable ex) {
		error.registerError(new Error(String.format(
		    "Unknown Exception Caught : %s",
		    ex.getMessage())));

		return Response.status(Status.BAD_REQUEST).entity(error.getErrors()).build();
	}

	private final ErrorWrapper error = new ErrorWrapper();

}
