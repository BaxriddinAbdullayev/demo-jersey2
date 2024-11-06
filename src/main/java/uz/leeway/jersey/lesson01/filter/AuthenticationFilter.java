package uz.leeway.jersey.lesson01.filter;

import org.glassfish.jersey.server.ContainerResponse;
import uz.leeway.jersey.lesson01.annotation.Public;
import uz.leeway.jersey.lesson01.controller.PublicController;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Provider
@Priority(Priorities.AUTHENTICATION)  // needs to happen before authorization
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final String AUTHENTICATION_SCHEME = "Basic";

    @Context
    private HttpServletRequest servletRequest;


    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] hashBytes = md.digest(password.getBytes());

            // Hash natijani hex formatga o'tkazish
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void filter(ContainerRequestContext context) throws IOException {

        Public annotation = AuthenticationFilter.class.getAnnotation(Public.class);
        String value = annotation.value();

        String path = context.getUriInfo().getPath();
        if (path.startsWith("public")) {
            return;
        }

        HttpSession session = servletRequest.getSession(false);
        if (session == null) {
            abortWithUnauthorized(context);
            return;
        }
        Object sessionUsername = session.getAttribute("username");
        Object sessionPassword = session.getAttribute("password");
        if (sessionUsername == null || sessionPassword == null) {
            abortWithUnauthorized(context);
            return;
        }

        String authzHeader = context.getHeaderString(HttpHeaders.AUTHORIZATION);
        String base64Credentials = authzHeader.substring(AUTHENTICATION_SCHEME.length()).trim();
        String credentials = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);
        final String[] values = credentials.split(":", 2);

        String username = values[0];
        String password = values[1];

        if (sessionUsername.equals(username) && sessionPassword.equals(password)) {
            return;
        }
        abortWithUnauthorized(context);
    }


    private void abortWithUnauthorized(ContainerRequestContext requestContext) {
        requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .header(HttpHeaders.WWW_AUTHENTICATE, AUTHENTICATION_SCHEME + " realm=\"example\"")
                        .entity("User topimadi!")
                        .build());
    }


}
