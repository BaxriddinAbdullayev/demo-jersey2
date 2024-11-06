package uz.leeway.jersey.lesson01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/headers")
public class HeaderController {

    @GET
    @Path("/testsession")
    @Produces(MediaType.APPLICATION_JSON)
    public String getValues(@Context HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        Object username = session.getAttribute("username");
        if (username != null) {
            System.out.println(username);
            return "username: " + username;
        } else {
            session.setAttribute("username", "Bahriddin");
        }
        return "Bahriddin";
    }


    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public String getValuesWithHeader(@Context HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if(session==null){
            return "Session is null";
        }

        Object username = session.getAttribute("username");
        if(username==null){
            return "Session is null";
        }else {
            return "username: "+username;
        }

//
//        String lang = request.getHeader("langa");
//
//        if (lang.equals("uz")) {
//            return "O'zbekiston";
//        } else if (lang.equals("ru")) {
//            return "Russian";
//        } else {
//            return "Other lang";
//        }


    }

    @GET
    @Path("/read")
    @Produces(MediaType.TEXT_PLAIN)
    public String read(@Context HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session!=null){
            session.invalidate();
            return "Session uchirildi";
        }else {
            return "Session is null";
        }

    }


}
