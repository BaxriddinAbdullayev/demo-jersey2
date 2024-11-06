package uz.leeway.jersey.lesson01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/session")
public class SessionController {

    @GET
    @Path("/set")
    @Produces(MediaType.APPLICATION_JSON)
    public String getValues(@Context HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        Object username = session.getAttribute("username");
        if (username != null) {
            return "Joriy session: " + username;
        } else {
            session.setAttribute("username", "Bahriddin");
            return "Yangi Bahriddin Session yasaldi!";
        }
    }

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public String getValuesWithHeader(@Context HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if(session==null){
            return "Session mavjud emas!";
        }else {
            Object username = session.getAttribute("username");
            return "Joriy session: "+username;
        }
    }

    @GET
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public String read(@Context HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session!=null){
            session.invalidate();
            return "Session uchirildi";
        }else {
            return "Session mavjud emas!";
        }

    }
}
