package mozart.core.api;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import mozart.core.api.config.ControllerConfig;
import mozart.core.exception.MozartException;

import org.springframework.stereotype.Component;

@Component
public abstract class Controller<T> {

	@GET
	@Path("")
	public Response loadAll(@Context HttpServletRequest request) throws MozartException {
		return Response.ok(toGenericEntity(getConfig().getService().loadAll())).build();
	}

	@GET
	@Path("/{id}")
	public Response loadById(@PathParam("id") String id, @Context HttpServletRequest request)
	        throws MozartException {
		return Response.ok(getConfig().getService().loadById(id)).build();
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") String id, @Context HttpServletRequest request)
	        throws MozartException {
		getConfig().getService().delete(id);
		return Response.ok().build();
	}

	@POST
	@Path("")
	public Response save(@Context HttpServletRequest request) throws MozartException {
		throw new MozartException("Unrecognized POST action for " + request.getPathInfo());
	}

	@PUT
	@Path("/{id}")
	public Response update(@PathParam("id") String id, @Context HttpServletRequest request)
	        throws MozartException {
		throw new MozartException("Unrecognized PUT action for " + request.getPathInfo());
	}

	private class MozartParameterizedType implements ParameterizedType {

		public Type[] getActualTypeArguments() {
			return new Type[] { getConfig().getType() };
		}

		public Type getRawType() {
			return List.class;
		}

		public Type getOwnerType() {
			return List.class;
		}

	}

	protected abstract ControllerConfig<T> getConfig();

	protected GenericEntity<List<T>> toGenericEntity(List<T> list) {
		GenericEntity<List<T>> ge = new GenericEntity<List<T>>(list, new MozartParameterizedType());
		return ge;
	}
}
