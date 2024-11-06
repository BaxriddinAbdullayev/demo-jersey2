package uz.leeway.jersey.lesson01;


import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("cookies")
public class CookiesController {

    @GET
    @Path("/set")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setCookies(@Context HttpHeaders headers) {

        Cookie currentCookie = headers.getCookies().get("username");

        if(currentCookie==null){
            return Response.ok("Yangi cookie yasaldi.")
                    .cookie(new NewCookie("username","Bahriddin"))
                    .build();
        }else {
            return Response.ok("Joriy cookie: "+currentCookie.getValue()).build();
        }
    }

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCookies(@Context HttpHeaders headers) {

        Cookie cookie = headers.getCookies().get("username");
        if(cookie!=null) {
            return Response.ok("result cookie: " + cookie.getValue()).build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).entity("Cookie topilmadi").build();
        }
    }

    @GET
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCookies(@Context HttpHeaders headers) {
        NewCookie cookie = new NewCookie("username", null, null, null, null, 0, false);
        return Response.ok("Cookie o'chirildi").cookie(cookie).build();
    }


}
