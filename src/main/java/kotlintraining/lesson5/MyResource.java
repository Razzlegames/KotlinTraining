package kotlintraining.lesson5;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("myresource")
public interface MyResource {

    @GET
    @Path("{id}")
    int getValue(@PathParam("id") int id);
}
